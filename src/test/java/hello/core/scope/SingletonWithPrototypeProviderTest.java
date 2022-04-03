package hello.core.scope;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

public class SingletonWithPrototypeProviderTest {

    @Test
    @DisplayName("싱글톤 빈 로직을 호출할 때마다 프로토타입 빈 인스턴스가 다시 주입된다.")
    public void 싱글톤_빈에_컨텍스트_주입() {

        // given
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class, ClientBean.class);

        // when
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();

        // then
        Assertions.assertThat(count1).isEqualTo(1);
        Assertions.assertThat(count2).isEqualTo(1);
    }

    @Test
    @DisplayName("ObjectProvider로 프로토타입 빈 인스턴스를 새로 주입한다.")
    public void ObjectProvider_테스트() {
        // given
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class, ClientBean2.class);

        // when
        ClientBean2 clientBean1 = ac.getBean(ClientBean2.class);
        int count1 = clientBean1.logic();
        ClientBean2 clientBean2 = ac.getBean(ClientBean2.class);
        int count2 = clientBean2.logic();

        // then
        Assertions.assertThat(count1).isEqualTo(1);
        Assertions.assertThat(count2).isEqualTo(1);
    }

    @Test
    @DisplayName("Provider로 프로토타입 빈 인스턴스를 새로 주입한다.")
    public void Provider_테스트() {
        // given
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class, ClientBean3.class);

        // when
        ClientBean3 clientBean1 = ac.getBean(ClientBean3.class);
        int count1 = clientBean1.logic();
        ClientBean3 clientBean2 = ac.getBean(ClientBean3.class);
        int count2 = clientBean2.logic();

        // then
        Assertions.assertThat(count1).isEqualTo(1);
        Assertions.assertThat(count2).isEqualTo(1);
    }

    @Scope("prototype")
    @Getter
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init" + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy" + this);
        }
    }

    @Scope("singleton")
    static class ClientBean {

        @Autowired
        private ApplicationContext ac;

        public int logic() {
            System.out.println("ClientBean.logic");
            PrototypeBean prototypeBean = ac.getBean(PrototypeBean.class); // 로직 호출 시 스프링 컨테이너에 프로토타입 빈 주입 요구
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("singleton")
    @RequiredArgsConstructor
    static class ClientBean2 {
        private final PrototypeBean prototypeBean;

        @Autowired
        private ObjectProvider<PrototypeBean> prototypeBeanObjectProvider; // provider 의존관계 필드 주입

        public int logic() {
            System.out.println("ClientBean2.logic");
            PrototypeBean prototypeBean = prototypeBeanObjectProvider.getObject(); // 새로운 프로토타입 빈 인스턴스 생성 후 반환
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("singleton")
    @RequiredArgsConstructor
    static class ClientBean3 {
        private final PrototypeBean prototypeBean;

        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;

        public int logic() {
            System.out.println("ClientBean3.logic");
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }
}
