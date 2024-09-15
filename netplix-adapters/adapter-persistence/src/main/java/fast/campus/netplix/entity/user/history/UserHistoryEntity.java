package fast.campus.netplix.entity.user.history;

import fast.campus.netplix.entity.audit.MutableBaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "user_histories")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserHistoryEntity extends MutableBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_HISTORY_ID")
    private Long userHistoryId;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "USER_ROLE")
    private String userRole;

    @Column(name = "REQ_IP")
    private String clientIp;

    @Column(name = "REQ_METHOD")
    private String reqMethod;

    @Column(name = "REQ_URL")
    private String reqUrl;

    @Column(name = "REQ_HEADER")
    private String reqHeader;

    @Column(name = "REQ_PAYLOAD")
    private String reqPayload;

    public UserHistoryEntity(
            String userId,
            String userRole,
            String clientIp,
            String reqMethod,
            String reqUrl,
            String reqHeader,
            String reqPayload
    ) {
        this.userId = userId;
        this.userRole = userRole;
        this.clientIp = clientIp;
        this.reqMethod = reqMethod;
        this.reqUrl = reqUrl;
        this.reqHeader = reqHeader;
        this.reqPayload = reqPayload;
    }
}
