package fast.campus.netplix.movie.download;

import org.springframework.stereotype.Component;

@Component
public class UserMovieDownloadSilverValidator implements UserMovieDownloadRoleValidator {
    @Override
    public boolean validate(long count) {
        return count <= 10;
    }

    @Override
    public boolean isTarget(String role) {
        return "ROLE_SILVER".equals(role);
    }
}
