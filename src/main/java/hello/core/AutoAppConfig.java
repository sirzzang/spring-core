package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * 컴포넌트 스캔을 통해 스프링 빈 등록
 * - 강의 구성 상, AppConfig 기존 파일을 남겨 두었기 때문에, 
 * - AppConfig(Configuration 클래스이며, 이것도 @Component)는 스캔하지 않도록 excludeFilters 추가
 * 컴포넌트 스캔 시,
 * - 각 구현체에 @Component를 통해 컴포넌트 등록 필요
 * --- 스프링 빈 이름은 클래스명에서 제일 앞 글자만 소문자로 변경한 것
 * - @Autowired를 통해 각 구현체에서 의존관계 주입 필요
 * --- 생성자에 @Autowired 지정 시, 스프링 컨테이너가 타입이 같은 빈을 찾아 의존관계 주입
 */
@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes=Configuration.class)
)
public class AutoAppConfig {
    
}
