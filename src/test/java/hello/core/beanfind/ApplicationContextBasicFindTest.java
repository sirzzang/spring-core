package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 빈 조회 기본 방법: getBean
 * - 이름으로 조회
 * - 이름 없이 타입으로 조회: 스프링 컨테이너에 등록된 구현체 타입을 보고 결정
 * -- 인터페이스 타입 조회
 * -- 구현체 타입 조회: 가능하나 권장되지 않음. 역할에만 의존해야 함
 */
public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    public void 빈_이름_조회() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class); // 등록된 빈이 등록해 놓은 것인지 확인
    }

    @Test
    @DisplayName("빈 타입으로 조회")
    public void 빈_타입_조회() {
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구(현)체 타입으로 조회")
    public void 빈_구현체_타입_조회() {
        MemberService memberService = ac.getBean(MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회 - 실패")
    public void 빈_이름_조회x() {
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("eraser", MemberService.class));
    }


}
