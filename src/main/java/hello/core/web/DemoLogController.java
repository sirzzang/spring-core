package hello.core.web;

import hello.core.common.RequestLogger;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Request;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class DemoLogController {

//    private final ObjectProvider<RequestLogger> requestLoggerProvider;
    private final RequestLogger requestLogger;
    private final DemoLogService demoLogService;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) throws InterruptedException {

        String requestURL = request.getRequestURL().toString(); // request url 추출

//        RequestLogger requestLogger = requestLoggerProvider.getObject();
        System.out.println("requestLogger = " + requestLogger.getClass());
        requestLogger.setRequestURL(requestURL); // request url 주입

        requestLogger.log("controller log");
        Thread.sleep(2000); // add exception to method signature
        demoLogService.logic("sirzzang");

        return requestURL + "OK";
    }
}
