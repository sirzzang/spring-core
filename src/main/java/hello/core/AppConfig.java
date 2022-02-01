package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 출력 예상(순서 무관)
 * call AppConfig.memberService
 * call AppConfig.memberRepository
 * call AppConfig.memberRepository
 * call AppConfig.orderService
 * call AppConfig.memberRepository
 *
 * 실제 출력 결과
 * call AppConfig.memberRepository
 * call AppConfig.memberService
 * call AppConfig.orderService
 *
 * 스프링 컨테이너 자체가 싱글톤 레지스트리인데, 싱글톤을 보장해야 한다.
 * 그런데, 자바 코드 상에서 memberRepository는 3번 호출되는 것이 맞다.
 * @Configuration을 적용하면, 순수한 클래스(내가 만든 AppConfig)가 아니라 $$Enhancer 등이 붙은 클래스(Spring이 만든 AppConfig)가 출력된다.
 * 스프링에서 빈을 등록하는 과정에서 CGLIB을 이용해 AppConfig를 상속한 임의의 다른 클래스를 만들어 스프링 빈으로 등록한다.
 * 이 임의의 다른 클래스가 싱글톤 패턴을 보장하는 것이다.
 * - 이 클래스는 아마 AppConfig 내에 뭔가 등록되어 있는 게 있으면 찾아서 반환하고,
 * - 등록되어 있는 게 없으면 등록하는 방식으로?
 */

@Configuration
public class AppConfig {

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.MemberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

}
