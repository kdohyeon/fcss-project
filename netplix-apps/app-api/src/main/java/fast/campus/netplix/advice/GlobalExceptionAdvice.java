package fast.campus.netplix.advice;

import fast.campus.netplix.controller.NetplixApiResponse;
import fast.campus.netplix.exception.ErrorCode;
import fast.campus.netplix.exception.NetplixException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(NetplixException.class)
    protected NetplixApiResponse<?> handleSecurityException(NetplixException e) {
        log.error("error={}", e.getMessage(), e);
        return NetplixApiResponse.fail(e.getErrorCode(), e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    protected NetplixApiResponse<?> handleRuntimeException(RuntimeException e) {
        log.error("error={}", e.getMessage(), e);
        return NetplixApiResponse.fail(ErrorCode.DEFAULT_ERROR, e.getMessage());
    }
}
