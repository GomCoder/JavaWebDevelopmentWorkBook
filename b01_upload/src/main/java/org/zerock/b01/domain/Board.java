package org.zerock.b01.domain;

/**
 * Board Entity Class
 */

import lombok.*;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "imageSet")
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    @Column(length = 500, nullable = false)//컬럼의 길이와 null 허용 여부 설정
    private String title;
    @Column(length = 2000, nullable = false)
    private String content;
    @Column(length = 50, nullable = false)
    private String writer;

    //BoardImage와 연관 관계 부여
    @OneToMany(mappedBy = "board",
                cascade = {CascadeType.ALL},
                fetch = FetchType.LAZY,
                orphanRemoval = true) //BoardImage의 board 변수
    @Builder.Default
    @BatchSize(size=20)
    private Set<BoardImage> imageSet = new HashSet<>();

    /**
     * 게시물에 이미지 추가하기
     * @param uuid
     * @param fileName
     */
    public void addImage(String uuid, String fileName) {
        BoardImage boardImage = BoardImage.builder()
                .uuid(uuid)
                .fileName(fileName)
                .board(this) //내부적으로 BoardImage 객체 내부의 Board에 대한 참조
                .ord(imageSet.size())
                .build();
        imageSet.add(boardImage);
    }

    /**
     * 게시물의 이미지 삭제(첨부파일들으 모두 삭제함)
     * BoardImage 객체 내부의 Board에 대한 참조를 null로 변경
     */
    public void clearImages() {
        imageSet.forEach(boardImage -> boardImage.changeBoard(null));
        this.imageSet.clear();
    }

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
