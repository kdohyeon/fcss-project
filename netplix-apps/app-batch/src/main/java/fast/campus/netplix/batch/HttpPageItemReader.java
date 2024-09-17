package fast.campus.netplix.batch;

import fast.campus.netplix.movie.FetchMovieUseCase;
import fast.campus.netplix.movie.NetplixMovie;
import fast.campus.netplix.movie.response.MoviePageableResponse;
import org.springframework.batch.item.support.AbstractItemCountingItemStreamItemReader;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class HttpPageItemReader extends AbstractItemCountingItemStreamItemReader<NetplixMovie> {

    private int page;
    private final List<NetplixMovie> contents = new LinkedList<>();

    private final FetchMovieUseCase fetchMovieUseCase;

    public HttpPageItemReader(int page, FetchMovieUseCase fetchMovieUseCase) {
        this.page = page;
        this.fetchMovieUseCase = fetchMovieUseCase;
    }

    @Override
    protected NetplixMovie doRead() {
        if (this.contents.isEmpty()) {
            readRow();
        }

        int size = contents.size();
        int index = size - 1;

        if (index < 0) {
            return null;
        }

        return contents.remove(contents.size() - 1);
    }

    @Override
    protected void doOpen() {
        setName(HttpPageItemReader.class.getName());
    }

    @Override
    protected void doClose() {

    }

    public void readRow() {
        MoviePageableResponse moviePageableResponse = fetchMovieUseCase.fetchFromClient(page);
        contents.addAll(moviePageableResponse.getMovies());
        page++;
    }
}
