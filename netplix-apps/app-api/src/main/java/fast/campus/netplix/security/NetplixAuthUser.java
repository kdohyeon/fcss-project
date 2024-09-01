package fast.campus.netplix.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class NetplixAuthUser extends User {

    private final String userId;
    private final String username;
    private final String password;
    private final String email;
    private final String phone;

    public NetplixAuthUser(String userId, String username, String password,
                           String email, String phone, Collection<? extends GrantedAuthority> authorities) {
        super(email, password, authorities);
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }
}
