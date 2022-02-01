package hello.core.beandefinition;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * 스프링 컨테이너는 빈 설정 메타 정보를 담고 있는 BeanDefinition에만 의존한다.
 * - ApplicationXontext에서 각각의 빈 설정 파일에 대한 정보를 읽어(Reader),
 * - BeanDefinition을 만든다.
 * -- beanDefinitionName, beanDefinition
 * -- bean, scope(없으면 singleton), abstract, lazyInit(사용 시점 빈 초기화 여부), autowiremode, ...
 * BeanDefinition의 메타 정보를 기반으로 직접 빈을 생성해서 스프링 컨테이너에 등록할 수도 있다. (거의 없음)
 */
public class BeanDefinitionTest {

    // getBeanDefinition은 ApplicationContext 인터페이스에서 지원하지 않는 기능
    AnnotationConfigApplicationContext aac = new AnnotationConfigApplicationContext(AppConfig.class);
    GenericXmlApplicationContext gxac = new GenericXmlApplicationContext("appConfig.xml");

    @Test
    @DisplayName("자바 코드로 설정한 빈 설정 메타 정보를 확인한다")
    public void 자바코드_빈_메타정보_확인() {
        String[] beanDefinitionNames = aac.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            BeanDefinition beanDefinition = aac.getBeanDefinition(name);

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("beanDefinitionName = " + name + ", beanDefinition = " + beanDefinition);
            }
        }
    }

    @Test
    @DisplayName("xml로 설정한 빈 설정 메타 정보를 확인한다")
    public void xml_빈_메타정보_확인() {
        String[] beanDefinitionNames = gxac.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            BeanDefinition beanDefinition = gxac.getBeanDefinition(name);

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("beanDefinitionName = " + name + ", beanDefinition = " + beanDefinition);
            }
        }
    }
}
