package fast.campus.netplix.user;

import fast.campus.netplix.auth.NetplixUser;

import java.util.Optional;

public interface SearchUserPort {
    Optional<NetplixUser> findByEmail(String email);
    NetplixUser getByEmail(String email);
    Optional<NetplixUser> findByProviderId(String providerId);
}
