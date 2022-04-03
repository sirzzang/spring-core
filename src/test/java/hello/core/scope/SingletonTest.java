package hello.core.scope;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    @Test
    @DisplayName("싱글톤 빈 요청 시 같은 인스턴스가 반환된다.")
    public void 싱글톤_빈_테스트() {

        // given
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class); // applicationcontext도 컴포넌트 스캔의 대상

        // when
        System.out.println("SingletonBean 요청 1");
        SingletonBean singletonBean1 = ac.getBean(SingletonBean.class);
        System.out.println("SingletonBean 요청 2");
        SingletonBean singletonBean2 = ac.getBean(SingletonBean.class);

        // then
        assertThat(singletonBean1).isSameAs(singletonBean2);
        System.out.println("singletonBean1 = " + singletonBean1);
        System.out.println("singletonBean2 = " + singletonBean2);

        ac.close();
    }

    @Scope("singleton")
    static class SingletonBean {
        @PostConstruct
        public void init() {
            System.out.println("SingletonBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("SingletonBean.destroy");
        }
    }
}
