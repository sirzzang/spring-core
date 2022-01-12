package hello.core;

import hello.core.member.*;

public class MemberApp {

    public static void main(String[] args) {
        // 회원 가입
        MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L, "eraser", Grade.VIP);
        memberService.join(member);

        // 회원 가입이 되었는지 확인
        Member findMember = memberService.findMember(1L);
        System.out.println("newMember: " + member);
        System.out.println("findMember: " + findMember);
    }

}
