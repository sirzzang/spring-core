package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * AppContext, MemberService, OrderService 각각 호출할 때,
 * MemoryMemberRepository를 new를 통해 인스턴스 생성하는 부분도 각각 호출되는데,
 * 전부 다 같은 인스턴스이다
 *
 * - AppConfig에서 출력 순서를 확인해 보면?
 * - AppConfig도 빈이므로, 이것을 확인해 보자.
 */
public class ConfigurationSingletonTest {

    @Test
    public void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // 테스트 용도의 메소드를 사용하려면 인터페이스가 아니라 구현체로 꺼내야 함
        // 테스트 용도일 뿐, 실무에서는 권장되지 않음
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        // 검증
        System.out.println("memberService -> memberRepository = " + memberRepository1);
        System.out.println("orderService -> memberRepository = " + memberRepository2);
        System.out.println("memberRepository in app context = " + memberRepository);
        assertThat(memberRepository1).isSameAs(memberRepository2);
    }

    @Test
    public void configurationDeepTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        // bean = class hello.core.AppConfig$$EnhancerBySpringCGLIB$$ba32fa28
        System.out.println("bean = " + bean.getClass());
    }
}
