package hello.core.web;

import hello.core.common.RequestLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DemoLogService {

//    private final ObjectProvider<RequestLogger> requestLoggerProvider;
    private final RequestLogger requestLogger;

    public void logic(String id) {

//        RequestLogger requestLogger = requestLoggerProvider.getObject();
        requestLogger.log(id + " service log");
    }
}
