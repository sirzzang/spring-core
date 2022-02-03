package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

    /*
     * DIP 원칙 위반
     * MemberServiceImpl은 MemberRepository 인터페이스 뿐만 아니라,
     * MemberRepository 구현체에도 의존한다
     */
//    private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 회원: DIP 원칙을 지키도록 수정
    private final MemberRepository memberRepository;

    // 자동 의존관계 주입: @Autowired
    @Autowired // ac.getBean(MemberRepository.class)
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
