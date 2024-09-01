package fast.campus.netplix.auth;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateUser {
    private final String username;
    private final String encryptedPassword;
    private final String email;
    private final String phone;

    public CreateUser(String username, String encryptedPassword, String email, String phone) {
        this.username = username;
        this.encryptedPassword = encryptedPassword;
        this.email = email;
        this.phone = phone;
    }
}
