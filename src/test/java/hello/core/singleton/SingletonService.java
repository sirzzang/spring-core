package hello.core.singleton;

/**
 * Singleton 패턴을 적용
 * 1. private static final
 *  - 자기 자신 인스턴스를 static 변수의 참조값으로 가지고 있음. 클래스 레벨에서 반드시 1개
 *  - 인스턴스 getter를 static 메소드로 선언
 *  - 새로운 객체 생성을 막기 위해 생성자 private으로 막아 둠
 */
public class SingletonService {

    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    private SingletonService() {};

    public void logic() {
        System.out.println("singleton 로직 호출");
    }

    // private으로 선언해도 자기 자신은 만들 수 있음
//    public static void main(String[] args) {
//        SingletonService singletonService = new SingletonService();
//    }


}
