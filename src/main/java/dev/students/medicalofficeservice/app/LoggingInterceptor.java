package dev.students.medicalofficeservice.app;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class LoggingInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
        String method = request.getMethod();
        String requestURL = request.getRequestURL().toString();
        switch (method.toUpperCase()) {
            case "GET":
                log.info("GET Request URL: " + requestURL);
                break;
            case "POST":
                log.info("POST Request URL: " + requestURL);
                break;
            case "PUT":
                log.info("PUT Request URL: " + requestURL);
                break;
            case "DELETE":
                log.info("DELETE Request URL: " + requestURL);
                break;
            case "PATCH":
                log.info("PATCH Request URL: " + requestURL);
                break;
            case "HEAD":
                log.info("HEAD Request URL: " + requestURL);
                break;
            case "OPTIONS":
                log.info("OPTIONS Request URL: " + requestURL);
                break;
            default:
                log.info("Other HTTP Method Request URL: " + requestURL);
                break;
        }

        return true;
    }

}
