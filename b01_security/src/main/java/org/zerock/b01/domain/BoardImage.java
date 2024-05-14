package org.zerock.b01.domain;

import lombok.*;

/**
 * 게시물의 첨부파일을 관리를 위한 엔티티
 */
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "board")
public class BoardImage implements Comparable<BoardImage> {

    @Id
    private String uuid;
    private String fileName;
    private int ord;
    @ManyToOne
    private Board board;

    @Override
    public int compareTo(BoardImage other) { //순번에 맞게 정렬하기 위함
        return this.ord - other.ord;
    }

    public void changeBoard(Board board) {
        this.board = board;
    }
}
