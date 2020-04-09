package uk.co.diegobarle.core.mappers

import uk.co.diegobarle.core.model.entity.IdList
import uk.co.diegobarle.core.model.entity.Movie
import uk.co.diegobarle.core.network.servermodel.movie.SMovie
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieMapper @Inject constructor(): MapperFromServer<Movie, SMovie> {
    override fun fromServer(from: SMovie): Movie {
        return Movie(
            from.id,
            from.posterPath,
            from.overview,
            from.releaseDate,
            IdList(from.genresIds?: listOf()),
            from.originalTitle,
            from.originalLanguage,
            from.title,
            from.backdropPath,
            from.popularity?:0.0,
            from.voteCount?:0,
            from.video?:false,
            from.voteAverage?:0.0)
    }
}