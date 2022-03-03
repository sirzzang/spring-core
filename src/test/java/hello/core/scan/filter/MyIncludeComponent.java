package hello.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE) // Class 레벨(클래스 타입)에 붙는 어노테이션
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {

}
