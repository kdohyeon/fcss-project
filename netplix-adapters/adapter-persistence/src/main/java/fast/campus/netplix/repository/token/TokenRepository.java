package fast.campus.netplix.repository.user;

import fast.campus.netplix.auth.CreateUser;
import fast.campus.netplix.auth.InsertUserPort;
import fast.campus.netplix.auth.NetplixUser;
import fast.campus.netplix.auth.SearchUserPort;
import fast.campus.netplix.entity.user.UserEntity;
import fast.campus.netplix.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository implements SearchUserPort, InsertUserPort {

    private final UserJpaRepository userJpaRepository;

    @Override
    public Optional<NetplixUser> findByEmail(String email) {
        return userJpaRepository.findByEmail(email)
                .map(UserEntity::toDomain);
    }

    @Override
    public NetplixUser getByEmail(String email) {
        Optional<NetplixUser> byEmail = findByEmail(email);
        if (byEmail.isEmpty()) {
            throw new UserException.UserDoesNotExistException();
        }

        return byEmail.get();
    }

    @Override
    public NetplixUser create(CreateUser create) {
        UserEntity user = UserEntity.toEntity(create);
        return userJpaRepository.save(user)
                .toDomain();
    }
}
