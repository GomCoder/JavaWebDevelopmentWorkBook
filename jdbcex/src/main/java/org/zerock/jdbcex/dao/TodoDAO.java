package org.zerock.jdbcex.dao;

import lombok.Cleanup;
import org.zerock.jdbcex.domain.TodoVO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 실제 SQL 처리를 담당하는 DAO 클래스
 */
public class TodoDAO {

    /**
     * DB에서 현재 시간을 가져옴
     * @return
     */
    public String getTime() {
        String now = null;

        try(
                Connection connection = ConnectionUtil.INSTANCE.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("select now()");
                ResultSet resultSet = preparedStatement.executeQuery();
        )  {
            resultSet.next();

            now = resultSet.getString(1);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }


    /**
     * DB에서 현재 시간을 가져옴
     * Lombok의 @Cleanup을 사용하는 경우 -> 최소한의 코드로 close()가 보장됨
     * @return
     * @throws Exception
     */
    public String getTime2() throws Exception {

        @Cleanup
        Connection connection = ConnectionUtil.INSTANCE.getConnection();

        @Cleanup
        PreparedStatement preparedStatement = connection.prepareStatement("select now()");

        @Cleanup
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        String now = resultSet.getString(1);

        return now;
    }

    /**
     * 등록 기능 구현
     * TodoVO 객체를 DB에 추가b기능(INSERT)
     * @param vo
     * @throws Exception
     */
    public void insert(TodoVO vo) throws Exception {

        String sql = "INSERT INTO tbl_todo (title, dueDate, finished) VALUES (?, ?, ?)";

        @Cleanup
        Connection connection = ConnectionUtil.INSTANCE.getConnection();

        @Cleanup
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, vo.getTitle());
        preparedStatement.setDate(2, Date.valueOf(vo.getDueDate()));
        preparedStatement.setBoolean(3, vo.isFinished());

        preparedStatement.executeUpdate();
    }

    /**
     * 목록 기능 구현
     * TodoDAO를 이용하여 tbl_todo 내의 모든 데이터를 가져오는 기능(SELECT)
     * @return TodoVO의 List
     * @throws Exception
     */
    public List<TodoVO> selectAll() throws Exception {

        String sql = "SELECT * FROM tbl_todo";

        @Cleanup
        Connection connection = ConnectionUtil.INSTANCE.getConnection();

        @Cleanup
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        @Cleanup
        ResultSet resultSet = preparedStatement.executeQuery();

        List<TodoVO> list = new ArrayList<>();

        while(resultSet.next()) {
            TodoVO vo = TodoVO.builder()
                    .tno(resultSet.getLong("tno"))
                    .title(resultSet.getString("title"))
                    .dueDate(resultSet.getDate("dueDate").toLocalDate())
                    .finished(resultSet.getBoolean("finished"))
                    .build();
            list.add(vo);
        }

        return list;
    }

    /**
     * 조회 기능 구현
     * 특정 번호의 데이터만 가져오는 기능(SELECT ~ WHERE)
     * @param tno
     * @return
     * @throws Exception
     */
    public TodoVO selectOne(Long tno) throws Exception {

        String sql = "SELECT * FROM tbl_todo WHERE tno = ?";

        @Cleanup
        Connection connection = ConnectionUtil.INSTANCE.getConnection();

        @Cleanup
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setLong(1, tno);

        @Cleanup
        ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();

        TodoVO vo = TodoVO.builder()
                .tno(resultSet.getLong("tno"))
                .title(resultSet.getString("title"))
                .dueDate(resultSet.getDate("dueDate").toLocalDate())
                .finished(resultSet.getBoolean("finished"))
                .build();

        return vo;
    }

    /**
     * 삭제 기능 구현
     * 특정 번호의 데이터를 삭제하는 기능(DELETE)
     * @param tno
     * @throws Exception
     */
    public void deleteOne(Long tno) throws Exception {

        String sql = "DELETE FROM tbl_todo WHERE tno = ?";

        @Cleanup
        Connection connection = ConnectionUtil.INSTANCE.getConnection();

        @Cleanup
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setLong(1, tno);
        preparedStatement.executeUpdate();
    }

    /**
     * 수정 기능 구현
     * 특정 번호의 데이터의 제목, 만료일, 완료 여부를 수정(UPDATE)
     * @param todoVO
     * @throws Exception
     */
    public void updateOne(TodoVO todoVO) throws Exception {
        String sql = "UPDATE tbl_todo SET title = ?, dueDate = ?, finished = ? WHERE tno = ?";

        @Cleanup
        Connection connection = ConnectionUtil.INSTANCE.getConnection();

        @Cleanup
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, todoVO.getTitle());
        preparedStatement.setDate(2, Date.valueOf(todoVO.getDueDate()));
        preparedStatement.setBoolean(3, todoVO.isFinished());
        preparedStatement.setLong(4, todoVO.getTno());

        preparedStatement.executeUpdate();
    }
}
