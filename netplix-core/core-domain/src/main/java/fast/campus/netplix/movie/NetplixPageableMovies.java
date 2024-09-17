package fast.campus.netplix.movie;

import lombok.Getter;

import java.util.List;

@Getter
public class NetplixPageableMovies {
    private final List<NetplixMovie> netplixMovies;

    private final int page;
    private final boolean hasNext;

    public NetplixPageableMovies(List<NetplixMovie> netplixMovies, int page, boolean hasNext) {
        this.netplixMovies = netplixMovies;
        this.page = page;
        this.hasNext = hasNext;
    }
}
