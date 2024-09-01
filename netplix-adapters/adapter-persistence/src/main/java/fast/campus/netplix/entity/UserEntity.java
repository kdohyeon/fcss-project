package fast.campus.netplix.entity;

import fast.campus.netplix.auth.CreateUser;
import fast.campus.netplix.auth.NetplixUser;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity extends MutableBaseEntity {
    @Id
    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "USER_NAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE")
    private String phone;

    public UserEntity(String username, String password, String email, String phone) {
        this.userId = UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    public NetplixUser toDomain() {
        return NetplixUser.builder()
                .userId(this.userId)
                .username(this.username)
                .encryptedPassword(this.password)
                .email(this.email)
                .phone(this.phone)
                .build();
    }

    public static UserEntity toEntity(CreateUser createUser) {
        return new UserEntity(
                createUser.getUsername(),
                createUser.getEncryptedPassword(),
                createUser.getEmail(),
                createUser.getPhone()
        );
    }
}
