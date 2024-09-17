package fast.campus.netplix.movie.response;

import lombok.Getter;

@Getter
public class MovieResponse {
    private final String movieName;

    public MovieResponse(String movieName) {
        this.movieName = movieName;
    }
}
