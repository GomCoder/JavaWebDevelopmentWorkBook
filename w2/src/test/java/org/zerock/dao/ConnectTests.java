package org.zerock.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * DB 연결 테스트
 */
public class ConnectTests {
    @Test
    public void test1() {
        int v1 = 10;
        int v2 = 10;

        Assertions.assertEquals(v1, v2);
    }

    /**
     * Java 코드를 이용해서 MariaDB와 연결을 확인하는 용도
     * @throws Exception
     */
    @Test
    public void testConnection() throws Exception {
        Class.forName("org.mariadb.jdbc.Driver"); //JDBD 드라이버를 메모리상으로 로드

        //데이터베이스와 네트워크 연결 -> DB내 여러 정보(url, user, password)를 통해 특정한 DB에 연결 시도
        Connection connection = DriverManager.getConnection(
                "jdbc:mariadb://localhost:3308/webdb",
                "webuser",
                "webroot"
        );

        Assertions.assertNotNull(connection); //정상적으로 연결 -> null이 아님
        connection.close(); //DB와 연결을 종료함
    }

    /**
     * HikariCP ConnectionPool 사용 테스트
     * @throws Exception
     */
    @Test
    public void testHikariCP() throws Exception {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.mariadb.jdbc.Driver");
        config.setJdbcUrl("jdbc:mariadb://localhost:3308/webdb");
        config.setUsername("webuser");
        config.setPassword("webroot");
        config.addDataSourceProperty("cachePreStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        HikariDataSource ds = new HikariDataSource(config);
        Connection connection = ds.getConnection();

        System.out.println(connection);

        connection.close();
    }
}
