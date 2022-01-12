package hello.core.member;

public class MemberServiceImpl implements MemberService {

    /*
     * DIP 원칙 위반
     * MemberServiceImpl은 MemberRepository 인터페이스 뿐만 아니라,
     * MemberRepository 구현체에도 의존한다
     */
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
