package fast.campus.netplix.repository.audit;

import fast.campus.netplix.audit.UserAuditHistoryPort;
import fast.campus.netplix.entity.user.history.UserHistoryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class UserHistoryRepository implements UserAuditHistoryPort {

    private final UserHistoryJpaRepository userHistoryJpaRepository;

    @Override
    @Transactional
    public void create(String userId, String userRole, String clientIp, String reqMethod, String reqUrl, String reqHeader, String reqPayload) {
        userHistoryJpaRepository.save(
                new UserHistoryEntity(
                        userId, userRole, clientIp, reqMethod, reqUrl, reqHeader, reqPayload)
        );
    }
}
