package hello.core.scope;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest {

    @Test
    @DisplayName("프로토타입 빈 호출 시, 서로 다른 빈 인스턴스가 사용된다.")
    public void 프로토타입_빈_테스트() {

        // given
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        // when
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println(prototypeBean1 + " addCount");
        prototypeBean1.addCount();
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        System.out.println(prototypeBean2 + " addCount");
        prototypeBean2.addCount();

        // then
        assertThat(prototypeBean1.getCount()).isEqualTo(1);
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("싱글톤 빈이 프로토타입 빈 사용 시, 처음 주입된 프로토타입 빈 인스턴스가 계속해서 사용된다.")
    public void 싱글톤_빈에서_프로토타입_빈_사용_테스트() {

        // given
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class, ClientBean.class);

        // when
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();

        // then
        assertThat(count1).isEqualTo(1);
        assertThat(count2).isEqualTo(2);
    }

    @Test
    @DisplayName("서로 다른 싱글톤 빈은 서로 다른 프로토타입 빈을 주입 받는다.")
    public void 서로_다른_싱글톤_빈에서의_프로토타입_빈_사용() {
        // given
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class, ClientBean.class, ClientBean2.class);

        // when
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();

        ClientBean2 clientBean2 = ac.getBean(ClientBean2.class);
        int count2 = clientBean2.logic();

        // then
        assertThat(count1).isEqualTo(1);
        assertThat(count2).isEqualTo(1);
    }

    @Scope("prototype")
    @Getter
    static class PrototypeBean {

        private int count = 0;

        public void addCount() {
            System.out.println("PrototypeBean.addCount " + this);
            count++;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy " + this);
        }
    }

    @Scope("singleton")
    @RequiredArgsConstructor
    static class ClientBean {
        private final PrototypeBean prototypeBean; // 생성 시점에 의존관계 주입

        /** 롬복 @RequiredArgsConstructor
        @Autowired
        public ClientBean(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }
        */

        public int logic() {
            System.out.println("ClientBean.logic");
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("singleton")
    @RequiredArgsConstructor
    static class ClientBean2 {
        private final PrototypeBean prototypeBean;

        public int logic() {
            System.out.println("ClientBean2.logic");
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }
}
