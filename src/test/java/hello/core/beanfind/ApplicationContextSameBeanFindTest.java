package hello.core.beanfind;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 같은 타입의 빈이 여러 개 있을 때 타입으로 조회하면 오류 발생
 * 따라서, 같은 타입의 빈을 조회하기 위해서는
 * - 빈 이름을 지정하거나,
 * - ac.getBeansOfType()을 사용해 같은 타입의 빈 모두 조회
 */
public class ApplicationContextSameBeanFindTest {

    // 중복 빈을 만들기 위한 static class
    @Configuration
    static class SameBeanConfig {

        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입으로 빈 조회 시 같은 타입이 둘 이상 있으면, 중복 오류가 발생한다.")
    public void 중복_타입_빈_조회() {
        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("타입으로 빈 조회 시, 같은 타입이 둘 이상 있으면, 빈 이름을 지정한다.")
    public void 중복_타입_빈_조회_이름지정() {
        MemberRepository memoryMemberRepository = ac.getBean("memberRepository1", MemberRepository.class);
        assertThat(memoryMemberRepository).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입의 빈을 모두 조회한다. Map 형태로 반환한다.")
    public void 중복_타입_빈_모두_조회() {
         Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
         for (String key : beansOfType.keySet()) {
             System.out.println("key = " + key + ", value = " + beansOfType.get(key));
         }
         System.out.println("beansOfType = " + beansOfType);
         assertThat(beansOfType.size()).isEqualTo(2);
    }

}
