package fast.campus.netplix.movie;

import java.util.List;

public interface PersistenceMoviePort {
    List<NetplixMovie> fetchBy(int page, int size);

    String insert(NetplixMovie netplixMovie);
}
