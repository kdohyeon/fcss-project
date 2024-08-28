package fast.campus.netplix.authentication;

import fast.campus.netplix.interceptor.RequestedByInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
@EnableWebMvc
public class RequestedByMvcConfigurer implements WebMvcConfigurer {
    private final RequestedByInterceptor requestedByInterceptor;

    public RequestedByMvcConfigurer(RequestedByInterceptor requestedByInterceptor) {
        this.requestedByInterceptor = requestedByInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addWebRequestInterceptor(requestedByInterceptor);
    }
}
