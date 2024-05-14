package org.zerock.b01.domain;

/**
 * 회원 Entity
 */

import lombok.*;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "roleSet")
public class Member extends BaseEntity {

    @Id
    private String mid; //회원 아이디

    private String mpw; //패스워드
    private String email; //이메일
    private boolean del; //탈퇴여부
    private boolean social; //소셜 로그인 자동 회원 가입 여부

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();

    /**
     * 패스워드 변경
     * @param mpw
     */
    public void changePassword(String mpw) {
        this.mpw = mpw;
    }

    /**
     * 이메일 변경
     * @param email
     */
    public void changerEmail(String email) {
        this.email = email;
    }

    /**
     * 탈퇴 여부 변경
     * @param del
     */
    public void changeDel(boolean del) {
        this.del = del;
    }

    /**
     * 회원 권한 추가
     * @param memberRole
     */
    public void addRole(MemberRole memberRole) {
        this.roleSet.add(memberRole);
    }

    /**
     * 회원 권한 삭제
     */
    public void clearRoles() {
        this.roleSet.clear();
    }

    /**
     * 소셜 로그인 자동 가입 변경
     * @param social
     */
    public void changeSocial(boolean social) {
        this.social = social;
    }
}
