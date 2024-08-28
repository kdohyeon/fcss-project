package fast.campus.netplix.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "users")
public class UserEntity extends MutableBaseEntity {
    @Id
    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "USER_NAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;
}
