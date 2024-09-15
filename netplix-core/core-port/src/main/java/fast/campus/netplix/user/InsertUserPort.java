package fast.campus.netplix.user;

import fast.campus.netplix.auth.NetplixUser;

public interface InsertUserPort {
    NetplixUser create(CreateUser create);
    NetplixUser createSocialUser(String username, String provider, String providerId);
}
