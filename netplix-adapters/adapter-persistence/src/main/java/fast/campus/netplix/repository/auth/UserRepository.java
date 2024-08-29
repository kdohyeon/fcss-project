package fast.campus.netplix.repository.auth;

import com.google.common.collect.Lists;
import fast.campus.netplix.auth.NetplixUser;
import fast.campus.netplix.auth.SearchUserPort;
import fast.campus.netplix.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionOperations;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository implements SearchUserPort {

    private final UserJpaRepository userJpaRepository;

    @Override
    public Optional<NetplixUser> findByEmail(String email) {
        Optional<UserEntity> byEmail = userJpaRepository.findByEmail(email);
        return Optional.empty();
    }

    @Override
    public NetplixUser getByEmail(String email) {
        return null;
    }

    @Override
    public NetplixUser create() {
        return null;
    }
}
