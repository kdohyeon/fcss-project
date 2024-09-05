package fast.campus.netplix.repository.user;

import com.querydsl.jpa.impl.JPAQueryFactory;
import fast.campus.netplix.entity.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static fast.campus.netplix.entity.QUserEntity.userEntity;

@Repository
@RequiredArgsConstructor
public class UserCustomRepositoryImpl implements UserCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return jpaQueryFactory.selectFrom(userEntity)
                .where(userEntity.email.eq(email))
                .fetch()
                .stream().findFirst();
    }
}
