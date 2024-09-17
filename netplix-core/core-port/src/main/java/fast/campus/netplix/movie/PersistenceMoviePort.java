package fast.campus.netplix.movie;

import java.util.List;

public interface PersistenceMoviePort {
    List<NetplixMovie> fetchBy(int page);

    String insert(NetplixMovie netplixMovie);
}
