package fast.campus.netplix.movie.download;

import org.springframework.stereotype.Component;

@Component
public class UserMovieDownloadFreeValidator implements UserMovieDownloadRoleValidator {
    @Override
    public boolean validate(long count) {
        return count <= 1;
    }

    @Override
    public boolean isTarget(String role) {
        return "ROLE_FREE".equals(role);
    }
}
