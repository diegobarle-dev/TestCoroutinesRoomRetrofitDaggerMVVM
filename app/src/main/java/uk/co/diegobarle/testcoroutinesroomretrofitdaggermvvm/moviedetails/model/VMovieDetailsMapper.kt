package uk.co.diegobarle.testcoroutinesroomretrofitdaggermvvm.moviedetails.model

import uk.co.diegobarle.core.mappers.Mapper
import uk.co.diegobarle.core.model.entity.Movie
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VMovieDetailsMapper @Inject constructor(): Mapper<Movie, VMovieDetails> {
    override fun map(from: Movie): VMovieDetails {
        return VMovieDetails(
            from.id,
            from.posterPath,
            from.overview,
            from.releaseDate,
            from.title,
            from.popularity,
            from.voteCount,
            from.voteAverage.toFloat()/2)
    }
}