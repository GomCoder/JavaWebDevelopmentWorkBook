package org.zerock.b01.dto;

/**
 * 목록 화면에서 특정한 게시물에 속한 댓글의 숫자를 출력하기 위한 DTO
 */
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardListReplyCountDTO {
    private Long bno;
    private String title;
    private String writer;
    private LocalDateTime regDate;
    private Long replyCount;
}
