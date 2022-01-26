package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

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

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


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
}
