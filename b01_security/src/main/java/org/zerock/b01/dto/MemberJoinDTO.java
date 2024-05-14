package org.zerock.b01.dto;

/**
 * 회원 가입에 사용할 DTO
 */

import lombok.Data;

@Data
public class MemberJoinDTO {
    private String mid;
    private String mpw;
    private String email;
    private boolean del;
    private boolean social;
}
