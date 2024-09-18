package fast.campus.netplix.movie;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieLikeService implements LikeMovieUseCase {

    private final LikeMoviePort likeMoviePort;

    @Override
    public Boolean like(String userId, String movieId) {
        Optional<UserMovieLike> byUserIdAndMovieId = likeMoviePort.findByUserIdAndMovieId(userId, movieId);

        UserMovieLike userMovieLike = byUserIdAndMovieId
                .orElseGet(() -> likeMoviePort.save(UserMovieLike.newLike(userId, movieId)));

        userMovieLike.like();
        likeMoviePort.save(userMovieLike);
        return true;
    }

    @Override
    public Boolean unlike(String userId, String movieId) {
        Optional<UserMovieLike> byUserIdAndMovieId = likeMoviePort.findByUserIdAndMovieId(userId, movieId);

        UserMovieLike userMovieLike = byUserIdAndMovieId
                .orElseGet(() -> likeMoviePort.save(UserMovieLike.newLike(userId, movieId)));

        userMovieLike.unlike();
        likeMoviePort.save(userMovieLike);
        return true;
    }
}
