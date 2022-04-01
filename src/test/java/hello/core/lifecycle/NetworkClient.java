package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient implements InitializingBean, DisposableBean {

    private String url;

    // 연결 객체 생성 및 연결, 호출 초기화
    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
        // connect(); // 객체 생성 시 연결
        // call("초기화 연결 메시지"); // 서버 호출
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작 시 연결 메서드
    public void connect() {
        System.out.println("url = " + url);
    }

    // 연결한 서버에 호출 메서드
    public void call(String message) {
        System.out.println("call: " + url + ", message = " + message);
    }

    // 서비스 종료 시 연결 종료 메서드
    public void disconnect() {
        System.out.println("disconnect 호출, close " + url);
    }

    // 의존관계 주입 후 호출할 메서드: 기존의 connect, call
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");
    }

    // 빈 소멸 직전 호출할 메서드
    @Override
    public void destroy() throws Exception {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
}
