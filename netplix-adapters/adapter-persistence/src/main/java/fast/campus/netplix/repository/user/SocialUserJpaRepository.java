package fast.campus.netplix.repository.user;

import fast.campus.netplix.entity.user.SocialUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialUserJpaRepository extends JpaRepository<SocialUserEntity, String>, SocialUserCustomRepository {

}
