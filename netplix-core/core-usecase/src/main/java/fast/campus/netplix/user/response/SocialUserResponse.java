package fast.campus.netplix.user.response;

public record SocialUserResponse(
        String name,
        String provider,
        String providerId
) {
}
