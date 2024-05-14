package org.zerock.b01.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.b01.domain.Member;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {

    /**
     * 직업 로그인 할 때, 소셜 서비스를 통해 회원가입된 회원들이 같은 패스워드를 가지므로 일반 회원들만 가져오도록 함
     */
    @EntityGraph(attributePaths = "roleSet")
    @Query("select m from Member m where m.mid = :mid and m.social = false")
    Optional<Member> getWithRoles(String mid);

    /**
     * 이메일을 이용하여 회원정보를 찾을 수 있도록 함
     * @param email
     * @return
     */
    @EntityGraph(attributePaths = "roleSet")
    Optional<Member> findByEmail(String email);

    /**
     * 사용자의 패스워드 수정
     */
    @Modifying
    @Transactional
    @Query("update Member m set m.mpw = :mpw where m.mid = :mid")
    void updatePassword(@Param("mpw") String password, @Param("mid") String mid);
}
