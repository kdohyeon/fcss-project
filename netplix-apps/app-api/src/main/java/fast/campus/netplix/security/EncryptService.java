package fast.campus.netplix.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EncryptService {

    private final PasswordEncoder passwordEncoder;

    public String encrypt(String before) {
        return passwordEncoder.encode(before);
    }
}
