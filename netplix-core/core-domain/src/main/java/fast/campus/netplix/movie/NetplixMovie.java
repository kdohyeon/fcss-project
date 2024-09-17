package fast.campus.netplix.movie;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NetplixMovie {
    private final String movieName;
    private final Boolean isAdult;
    private final String genre;
    private final String overview;
    private final String releasedAt;

    public NetplixMovie(String movieName, Boolean isAdult, String genre, String overview, String releasedAt) {
        this.movieName = movieName;
        this.isAdult = isAdult;
        this.genre = genre;
        this.overview = overview;
        this.releasedAt = releasedAt;
    }
}
