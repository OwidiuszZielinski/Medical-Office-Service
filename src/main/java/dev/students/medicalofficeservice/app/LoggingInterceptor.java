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
        //Dopisac switch case ktory bedzie logował dane metody
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            log.info("Event has been logged GET Request URL: " + request.getRequestURL().toString());
        }
        return true;
    }

}
