package fast.campus.netplix.repository.user;

import fast.campus.netplix.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, String>, UserCustomRepository {

}
