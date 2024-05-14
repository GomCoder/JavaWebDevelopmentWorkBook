package org.zerock.b01.service;

import org.zerock.b01.dto.MemberJoinDTO;

/**
 * 회원 가입 서비스 계층 처리 인터페이스
 */
public interface MemberService {
    /**
     * 같은 아이디가 존재하면 예외 발생하기
     */
    static class MidExistException extends Exception {

    }

    void join(MemberJoinDTO memberJoinDTO)throws MidExistException;
}
