package fast.campus.netplix.auth.command;

import lombok.Builder;

@Builder
public record UserRegistrationCommand(String username, String encryptedPassword, String email, String phone) {
}
