package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 현재까지의 DI 컨테이너 방식으로는 요청이 올 때마다 bean instance가 생성되었다
 * - 스프링 없는 순수한 DI 컨테이너: AppConfig
 * - MemberService 인스턴스가 JVM 메모리에 계속 올라감
 */
public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    public void 순수_컨테이너() {
        AppConfig appConfig = new AppConfig();

        // 1. 조회 호출할 때마다 객체 생성: service, repository 2개
        MemberService memberService1 = appConfig.memberService();

        // 2. 조회: 호출할 때마다 객체 생성: service, repository 2개
        MemberService memberService2 = appConfig.memberService();

        // 참조값이 다른 것을 확인: 총 4개의 서로 다른 인스턴스 생성
        System.out.println(memberService1);
        System.out.println(memberService2);
        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("Singleton 패턴을 적용한 객체 사용")
    public void 싱글톤_서비스() {
        // 1. 조회
        SingletonService singletonService1 = SingletonService.getInstance();

        // 2. 조회
        SingletonService singletonService2 = SingletonService.getInstance();

        // 참조값이 같은 것을 확인
        System.out.println(singletonService1);
        System.out.println(singletonService2);
        assertThat(singletonService1).isSameAs(singletonService2);

    }

}
