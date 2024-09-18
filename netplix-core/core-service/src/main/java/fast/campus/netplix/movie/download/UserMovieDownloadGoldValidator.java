package fast.campus.netplix.movie.download;

import org.springframework.stereotype.Component;

@Component
public class UserMovieDownloadGoldValidator implements UserMovieDownloadRoleValidator {
    @Override
    public boolean validate(long count) {
        // 골드는 제한 없음
        return true;
    }

    @Override
    public boolean isTarget(String role) {
        return "ROLE_GOLD".equals(role);
    }
}
