package fast.campus.netplix.movie;

import java.util.List;

public interface PersistenceMoviePort {
    List<NetplixMovie> fetchBy(int page, int size);

    NetplixMovie findBy(String movieName);

    String insert(NetplixMovie netplixMovie);
}
