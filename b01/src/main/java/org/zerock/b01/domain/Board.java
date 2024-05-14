package org.zerock.b01.domain;

/**
 * Board Entity Class
 */

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    @Column(length = 500, nullable = false)//컬럼의 길이와 null 허용 여부 설정
    private String title;
    @Column(length=2000, nullable = false)
    private String content;
    @Column(length = 50, nullable = false)
    private String writer;

    /**
     * 제목과 내용을 수정
     * @param title
     * @param content
     */
    public void change(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
