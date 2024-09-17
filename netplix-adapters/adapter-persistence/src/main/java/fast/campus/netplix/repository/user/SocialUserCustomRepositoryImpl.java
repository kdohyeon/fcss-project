package fast.campus.netplix.repository.user;

import com.querydsl.jpa.impl.JPAQueryFactory;
import fast.campus.netplix.entity.user.SocialUserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static fast.campus.netplix.entity.user.QSocialUserEntity.socialUserEntity;

@Repository
@RequiredArgsConstructor
public class SocialUserCustomRepositoryImpl implements SocialUserCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<SocialUserEntity> findByProviderId(String providerId) {
        return jpaQueryFactory.selectFrom(socialUserEntity)
                .where(socialUserEntity.providerId.eq(providerId))
                .fetch()
                .stream().findFirst();
    }
}
