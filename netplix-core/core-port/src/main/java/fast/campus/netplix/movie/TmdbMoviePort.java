package fast.campus.netplix.movie;

public interface TmdbMoviePort {
    NetplixPageableMovies fetchPageable(int page);
}
