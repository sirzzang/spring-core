package hello.core.xml;

import hello.core.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Xml을 이용해 스프링 컨테이너에 설정 정보를 주입할 수 있다.
 * GeneicXmlApplicationContext 사용: GenericXmlApplicationContext -> ApplicationContext
 * appConfig.xml은 자바 코드 기반의 AppConfig 파일과 문서 형식만 다를 뿐.
 */
public class XmlAppContext {
    @Test
    void xmlAppContext() {
        GenericXmlApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
