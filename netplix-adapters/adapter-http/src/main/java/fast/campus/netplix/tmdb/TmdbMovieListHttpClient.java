package fast.campus.netplix.tmdb;

import fast.campus.netplix.client.TmdbHttpClient;
import fast.campus.netplix.util.ObjectMapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class TmdbMovieListHttpClient {
    @Value("${tmdb.api.movie-lists.now-playing}")
    private String nowPlaying;

    private final TmdbHttpClient tmdbHttpClient;

    public String getMovieListNowPlaying(int page) {
        String request = tmdbHttpClient.request(nowPlaying, HttpMethod.GET, CollectionUtils.toMultiValueMap(Map.of()), Map.of("page", page));
        TmdbResponse object = ObjectMapperUtil.toObject(request, TmdbResponse.class);
        return request;
    }
}
