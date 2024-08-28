package fast.campus.netplix.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "user_histories")
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
}
