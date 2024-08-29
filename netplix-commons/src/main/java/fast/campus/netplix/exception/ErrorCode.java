package fast.campus.netplix.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    DEFAULT_ERROR("NPX0000", "에러가 발생했습니다."),

    PASSWORD_ENCRYPTION_FAILED("NPX1000", "비밀번호 암호화 중 에러가 발생했습니다."),
    ;

    private final String code;
    private final String desc;

    ErrorCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "[" + code + "] " + desc;
    }
}
