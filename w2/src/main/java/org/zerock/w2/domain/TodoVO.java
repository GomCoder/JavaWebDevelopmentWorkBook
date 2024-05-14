package org.zerock.w2.domain;

import lombok.*;

import java.time.LocalDate;

/**
 * DB에 만든 webdb 테이블의 데이터를 java 객체로 처리하기 위한 VO 클래스
 * tbl_todo 테이블의 컬럼들을 기준으로 속성을 만듦
 */
@Getter //주로 읽기 전용으로만 사용
@Builder //객체 생성시 Builder 패턴 사용
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TodoVO {
    private Long tno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;
}
