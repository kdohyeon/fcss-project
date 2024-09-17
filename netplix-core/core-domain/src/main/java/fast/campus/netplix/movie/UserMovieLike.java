package fast.campus.netplix.movie;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class UserMovieLike {
    private final String userMovieLikeId;
    private final String userId;
    private final String movieId;
    private Boolean likeYn;

    public void like() {
        this.likeYn = true;
    }

    public void unlike() {
        this.likeYn = false;
    }

    public static UserMovieLike newLike(String userId, String movieId) {
        return UserMovieLike.builder()
                .userMovieLikeId(UUID.randomUUID().toString())
                .userId(userId)
                .movieId(movieId)
                .likeYn(true)
                .build();
    }
}
