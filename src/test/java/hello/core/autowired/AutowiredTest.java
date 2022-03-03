package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    /** 옵션 처리를 위한 테스트 빈
     * - Member는 스프링이 관리하는 빈이 아님. 자바 객체
     * - @Autowired에 의해 연결될 수 있는 스프링 빈이 없음
     */
    static class TestBean {

        @Autowired(required = false)
        public void setNoBean1(Member noMember1) {
            System.out.println("noMember1 = " + noMember1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member noMember2) {
            System.out.println("noMember2 = " + noMember2);
        }

        @Autowired
        public void setNoBean3(Optional<Member> noMember3) {
            System.out.println("noMember3 = " + noMember3);
        }
    }
}
