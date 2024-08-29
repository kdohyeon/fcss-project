package fast.campus.netplix.exception;

public class SecurityException extends NetplixException {

    public SecurityException(ErrorCode errorCode) {
        super(errorCode);
    }

    public static class PasswordEncryptionException extends SecurityException {
        public PasswordEncryptionException() {
            super(ErrorCode.PASSWORD_ENCRYPTION_FAILED);
        }
    }
}
