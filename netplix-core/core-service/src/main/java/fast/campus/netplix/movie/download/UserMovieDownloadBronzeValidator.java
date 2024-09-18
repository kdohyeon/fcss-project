package fast.campus.netplix.movie.download;

import org.springframework.stereotype.Component;

@Component
public class UserMovieDownloadBronzeValidator implements UserMovieDownloadRoleValidator {
    @Override
    public boolean validate(long count) {
        return count <= 5;
    }

    @Override
    public boolean isTarget(String role) {
        return "ROLE_BRONZE".equals(role);
    }
}
