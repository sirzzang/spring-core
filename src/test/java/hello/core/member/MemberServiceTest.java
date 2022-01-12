package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

    @Test
    void 회원가입() {
        // given
        Member member = new Member(2L, "sirzzang", Grade.BASIC);

        // when
        memberService.join(member);
        Member findMember = memberService.findMember(2L);

        // then
        Assertions.assertThat(member).isEqualTo(findMember);
    }


}
