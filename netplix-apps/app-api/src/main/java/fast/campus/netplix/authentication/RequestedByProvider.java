package fast.campus.netplix.authentication;

import java.util.Optional;

public interface RequestedByProvider {
    Optional<String> getRequestedBy();
}
