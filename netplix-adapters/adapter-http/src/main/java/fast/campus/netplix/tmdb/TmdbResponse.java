package fast.campus.netplix.tmdb;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import java.util.List;

@Getter
public class TmdbResponse {
    private TmdbDateResponse dates;
    private String page;
    private String total_pages;
    private String total_results;
    private List<TmdbMovieNowPlaying> results;
}
