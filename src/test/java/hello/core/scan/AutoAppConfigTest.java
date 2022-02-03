package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 로그의 ClassPathDefinitionScanner, singleton bean 주목
 */
public class AutoAppConfigTest {

    @Test
    public void 컴포넌트_스캔() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = ac.getBean(MemberService.class); // name: memberServiceImpl
        assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
