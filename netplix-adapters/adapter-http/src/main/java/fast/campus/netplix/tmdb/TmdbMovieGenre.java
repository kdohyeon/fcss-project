package fast.campus.netplix.tmdb;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// ref. https://www.themoviedb.org/talk/5daf6eb0ae36680011d7e6ee
@Getter
public enum TmdbMovieGenre {
    UNKNOWN("Unknown", 0),
    ACTION("Action", 28),
    ADVENTURE("Adventure", 12),
    ANIMATION("Animation", 16),
    COMEDY("Comedy", 35),
    CRIME("Crime", 80),
    DOCUMENTARY("Documentary", 99),
    DRAMA("Drama", 18),
    FAMILY("Family", 10751),
    FANTASY("Fantasy", 14),
    HISTORY("History", 36),
    HORROR("Horror", 27),
    MUSIC("Music", 10402),
    MYSTERY("Mystery", 9648),
    ROMANCE("Romance", 10749),
    SCIENCE_FICTION("Science Fiction", 878),
    TV_MOVIE("TV Movie", 10770),
    THRILLER("Thriller", 53),
    WAR("War", 10752),
    WESTERN("Western", 37),
    ;

    private String name;
    private Integer genreId;

    TmdbMovieGenre(String name, Integer genreId) {
        this.name = name;
        this.genreId = genreId;
    }

    public static String getGenreNamesByIds(List<String> genreIds) {
        return genreIds.stream()
                .map(genreId -> TmdbMovieGenre.getById(genreId).name)
                .collect(Collectors.joining(","));
    }

    public static TmdbMovieGenre getById(String id) {
        return Arrays.stream(TmdbMovieGenre.values())
                .filter(each -> each.genreId.toString().equals(id))
                .findFirst()
                .orElse(TmdbMovieGenre.UNKNOWN);
    }
}
