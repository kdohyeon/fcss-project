package fast.campus.netplix.logging;

public record CreateAuditLog(
        String userId,
        String userRole,
        String clientIp,
        String reqMethod,
        String reqUrl,
        String reqHeader,
        String reqPayload
) {

}
