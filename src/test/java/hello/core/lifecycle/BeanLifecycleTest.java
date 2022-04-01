package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifecycleTest {

    @Test
    public void 생명주기_테스트() {

        // ApplicationContext는 close가 없음
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(lifecycleConfig.class);

        // NetworkClient 생성
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();

    }

    @Configuration
    static class lifecycleConfig {
        @Bean
        public NetworkClient networkClient() {
            System.out.println("true = " + true);
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http;//sirzzang.github.io"); // 빈 의존관계 주입 시 url 설정
            return networkClient; // setUrl 후 결과물이 스프링 빈으로 등록됨
        }
    }
}
