package hello.core.singleton;

/**
 * 싱글톤 방식을 사용할 때(싱글톤 패턴을 구현하든, 스프링 컨테이너든)는, 싱글톤 객체를 무상태(stateless)로 설계해야 한다.
 * 여러 클라이언트가 하나의 같은 객체 인스턴스를 공유하기 때문이다.
 * - 특정 클라이어트에 의존적인 필드가 있으면 안 된다.
 * - 특정 클라이언트가 값을 변경할 수 있는 필드가 있으면 안 된다.
 * - 가급적 읽기만 가능해야 한다. (가급적 값을 수정하면 안 된다)
 * - 필드 대신에 자바에서 공유되지 않는 지역변수, 파라미터, ThreadLocal 등을 사용해야 한다.
 */
public class StatefulService {

    private int price; // 상태를 유지하는 필드

    public void order(String name, int price) {
        System.out.println("name = " + name + ", price = " + price);
        this.price = price; // 문제가 되는 부분
    }

    public int getPrice() {
        return price;
    }
}
