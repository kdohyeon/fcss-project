package fast.campus.netplix.exception;

import lombok.Getter;

@Getter
public class NetplixException extends RuntimeException {
    private final ErrorCode errorCode;

    public NetplixException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
