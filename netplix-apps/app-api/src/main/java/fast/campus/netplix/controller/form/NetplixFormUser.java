package fast.campus.netplix.controller.form;

import fast.campus.netplix.annotation.PasswordEncryption;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter // setter 가 있어야 객체 맵핑이 됨
public class NetplixFormUser {
    private String username;

    private String password;

    @PasswordEncryption
    private String confirmedPassword;
    private String email;
    private String phone;
}
