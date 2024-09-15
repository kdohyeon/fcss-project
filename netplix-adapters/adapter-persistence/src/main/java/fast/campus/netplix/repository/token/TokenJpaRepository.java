package fast.campus.netplix.repository.token;

import fast.campus.netplix.entity.token.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenJpaRepository extends JpaRepository<TokenEntity, String>, TokenCustomRepository {

}
