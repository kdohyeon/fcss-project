package fast.campus.netplix.repository.audit;

import fast.campus.netplix.entity.user.history.UserHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserHistoryJpaRepository extends JpaRepository<UserHistoryEntity, String> {

}
