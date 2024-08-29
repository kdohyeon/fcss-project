package fast.campus.netplix.repository.auth;

import fast.campus.netplix.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserJpaRepository {
    public Optional<UserEntity> findByEmail(String email) {
        return null;
    }
}
