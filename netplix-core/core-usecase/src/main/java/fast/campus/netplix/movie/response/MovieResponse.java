package fast.campus.netplix.movie.response;

import lombok.Getter;

@Getter
public class MovieResponse {
    private final String movieName;
    private final long downloadCnt;

    public MovieResponse(String movieName, long downloadCnt) {
        this.movieName = movieName;
        this.downloadCnt = downloadCnt;
    }
}
