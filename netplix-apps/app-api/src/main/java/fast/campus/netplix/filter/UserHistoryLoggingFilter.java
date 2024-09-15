package fast.campus.netplix.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fast.campus.netplix.logging.CreateAuditLog;
import fast.campus.netplix.logging.LogUserAuditHistoryUseCase;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserHistoryLoggingFilter implements Filter {

    private final LogUserAuditHistoryUseCase logUserAuditHistoryUseCase;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        log(authentication, httpServletRequest);

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Async
    public void log(Authentication authentication, HttpServletRequest httpServletRequest) {
        logUserAuditHistoryUseCase.log(
                new CreateAuditLog(
                        authentication.getName(),
                        authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(",")),
                        httpServletRequest.getRemoteAddr(),
                        httpServletRequest.getMethod(),
                        httpServletRequest.getRequestURI(),
                        getHeaders(httpServletRequest),
                        ""
                )
        );
    }

    private String getHeaders(HttpServletRequest request) {
        // 헤더를 저장할 Map 생성
        Map<String, String> headersMap = new HashMap<>();

        // 모든 헤더 추출
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                String headerValue = request.getHeader(headerName);
                headersMap.put(headerName, headerValue);
            }
        }

        // Map을 JSON 문자열로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(headersMap);
        } catch (JsonProcessingException e) {
            return "{}"; // 변환 실패 시 빈 JSON 반환
        }
    }
}
