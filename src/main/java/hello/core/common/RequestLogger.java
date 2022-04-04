package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS) // 프록시 모드
public class RequestLogger {

    private String uuid;
    private String RequestURL;

    public void setRequestURL(String requestURL) {
        this.RequestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + RequestURL + "] " + message);
    }

    // 의존관계 주입 후 빈 uuid 속성 값 설정
    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean created: " + this);
    }

    // 소멸
    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] request scope bean destroyed: " + this);
    }
}
