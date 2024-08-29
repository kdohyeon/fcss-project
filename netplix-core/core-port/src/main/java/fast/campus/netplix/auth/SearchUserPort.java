package fast.campus.netplix.auth;

import java.util.Optional;

public interface SearchUserPort {
    Optional<NetplixUser> findByEmail(String email);
    NetplixUser getByEmail(String email);

    NetplixUser create();
}
