package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    public void 상태유지_서비스() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // Thread1: 사용자1이 10000원 주문
        statefulService1.order("user1", 10000);

        // Thread2: 사용자2가 20000원 주문
        statefulService2.order("user2", 20000);

        // Thread1: 사용자1이 주문 금액 조회
        int price = statefulService1.getPrice();
        System.out.println("user1 order: " + price);

        // 검증: 의도했던 바가 아님
        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000); // Thread2에서 변경
    }

    @Configuration
    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}