package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    // 회원, 할인 정책
    /* 문제
     - OCP, DIP를 지킨 것 같지만, 클라이언트(Service)가 구현 클래스에도 의존
     - 할인 정책을 변경하려면, DiscountPolicy를 변경하려면 ServiceImpl의 코드를 고쳐야 함
     */
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    // 회원, 할인 정책: DIP 원칙을 지키도록 수정
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    // lombok @RequiredArgsConstructor 사용 시 아래 생성자 코드 부분 필요 없음
    // @MainDiscountPolicy 애노테이션을 만들 수도 있음
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    /** 수정자 주입을 통한 의존관계 주입
     * private MemberRepository memberRepository;
     * private DiscountPolicy discountPolicy;
     *
     * @Autowired
     * public void setMemberRepository(MemberRepository memberRepository) {
     *     this.memberRepository = memberRepository;
     * }
     *
     * @Autowired
     * public void setDiscountPolicy(DiscountPolicy discountPolicy) {
     *     this.discountPolicy = discountPolicy;
     * }
     */


    /* 문제
     - 인터페이스에만 의존하도록 변경하면 아래와 같이 코드를 짜면
     - 구현 객체가 없어 nullPointerException 발생
     */
//    private DiscountPolicy discountPolicy; // 인터페이스에만 의존


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
