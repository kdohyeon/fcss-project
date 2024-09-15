package fast.campus.netplix.repository.token;

import com.querydsl.jpa.impl.JPAQueryFactory;
import fast.campus.netplix.entity.token.TokenEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static fast.campus.netplix.entity.token.QTokenEntity.tokenEntity;


@Repository
@RequiredArgsConstructor
public class TokenCustomRepositoryImpl implements TokenCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<TokenEntity> findByUserId(String userId) {
        return jpaQueryFactory.selectFrom(tokenEntity)
                .where(tokenEntity.userId.eq(userId))
                .fetch()
                .stream().findFirst();
    }
}
