package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * 컴포넌트 스캔을 통해 스프링 빈 등록
 * <p>
 * 컴포넌트 스캔 시, 각 구현체에 @Component를 통해 컴포넌트 등록 필요
 * 이 때, 등록되는 스프링 빈 이름은 클래스명에서 제일 앞 글자만 소문자로 변경한 것
 * 각 구현체에서는 @Autowired를 통해 각 구현체에서 의존관계 주입 필요
 * 각 구현체 생성자에 @Autowired 지정 시, 스프링 컨테이너가 타입이 같은 빈을 찾아 의존관계 주입
 * </p>
 * <p>
 * - excludeFilters: 컴포넌트 스캔 시 제외
 *      강의 구성 상, AppConfig 기존 파일을 남겨 두었기 때문에,
 *      AppConfig(Configuration 클래스이며, 이것도 @Component)는 스캔하지 않도록 excludeFilters 추가
 * </p>
 * <p>
 * - basePackage: 탐색 시작 위치. 해당 패키지부터 하위의 패키지를 탐색
 *      모든 프로젝트를 다 탐색하지 않고, 여러 라이브러리 중 특정 라이브러리만 탐색하고 싶을 때 유용
 *      여러 개 지정할 경우, {} 안에 패키지 입력
 * - basePackageClasses: 지정한 클래스의 패키지를 탐색 시작 위치로
 *      AutoAppConfig의 package는 hello.core이기 때문에, 해당 패키지부터 탐색
 * - 탐색 시작 위치 지정하지 않을 경우, @ComponentScan의 패키지부터(그 하위까지) 탐색
 * - 관례상, 패키지 위치를 지정하지 않고, 설정 정보(config) 클래스 위치를 프로젝트 최상단에 두어, 해당 패키지부터 탐색 시작하도록 함
 *      스프링 부트 사용 시, 스프링 부트의 대표 시작 정보인 @SpringBootApplication을 시작 위치 안에 두는 것이 관례이고,
 *      그 안에 @ComponentScan이 들어가게 되고, 따라서 시작점의 패키지부터 컴포넌트 스캔
 * </p>
 * <p>
 * 컴포넌트 스캔 대상: @Component, @Controller, @Service, @Repository, @Configuration
 * - @Controller: 스프링 MVC 컨트롤러로 인식
 * - @Repository: 스프링 데이터 접근 계층으로 인식하고, 데이터 게층의 예외를 스프링 예외로 변환
 * - @Configuration: 스프링 설정 정보로 인식하고,  스프링 빈이 싱글톤을 유지하도록 처리
 * - @Service: 스프링 자체에서 특별히 처리하는 것은 없고, 개발자들이 핵심 비즈니스 로직이 있다고 인식
 * 다만, 자바 언어 애노테이션은 상속 기능이 없기 때문에,
 *  애노테이션(@SpringBootApplication)이 다른 애노테이션(@ComponentScan)을 들고 있음을 알 수 있는 것은 스프링에서 지원하는 기능
 * </p>
 */

@Configuration
@ComponentScan(
//        basePackages = {"hello.core.member", "hello.core.discounts"},
//        basePackageClasses = AutoAppConfig.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes=Configuration.class)
)
public class AutoAppConfig {
    
}
