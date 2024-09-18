package fast.campus.netplix.movie;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class UserMovieDownload {
    private final String userMovieLikeId;
    private final String userId;
    private final String movieId;

    public static UserMovieDownload newDownload(String userId, String movieId) {
        return UserMovieDownload.builder()
                .userMovieLikeId(UUID.randomUUID().toString())
                .userId(userId)
                .movieId(movieId)
                .build();
    }
}
