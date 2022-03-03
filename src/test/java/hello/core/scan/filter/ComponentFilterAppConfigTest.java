package hello.core.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.assertThat;

public class ComponentFilterAppConfigTest {

    @Test
    @DisplayName("IncludeComponent 필터가 붙은 BeanA만 컴포넌트로 스캔된다")
    void 필터_스캔() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);

        // BeanA 조회 가능
        BeanA beanA = ac.getBean("beanA", BeanA.class);
        assertThat(beanA).isNotNull();

        // BeanB 조회 불가능
        org.junit.jupiter.api.Assertions.assertThrows(
                NoSuchBeanDefinitionException.class,
                () -> ac.getBean("beanB", BeanB.class)
        );
    }

    /**
     * FilterType으로 사용할 수 있는 5가지 옵션
     * 1. ANNOTATION: 기본값, 애노테이션을 인식해서 동작
     * 2. ASSIGNABLE_TYPE: 지정한 타입과 그 자식 타입을 인식해서 동작
     *  - 예를 들어서, BeanA 타입을 컴포넌트 스캔에서 제외하고 싶다면,
     *  - excludeFilters(@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = BeanA.class))
     * 3. ASPECTJ: AspectJ 패턴 사용
     * 4. REGEX: 정규표현식
     * 5. CUSTOM: TypeFilter 인터페이스 구현해서 처리
     */

    @Configuration
    @ComponentScan(
            includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
    )
    static class ComponentFilterAppConfig {

    }
}
