package fast.campus.netplix.user.response;

public record SimpleUserResponse(
        String username,
        String email,
        String phone
) {
}
