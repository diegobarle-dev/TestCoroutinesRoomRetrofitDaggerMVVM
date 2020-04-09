package uk.co.diegobarle.testcoroutinesroomretrofitdaggermvvm.movieslist.model

import uk.co.diegobarle.core.mappers.Mapper
import uk.co.diegobarle.core.model.entity.Movie
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VMovieMapper @Inject constructor(): Mapper<List<Movie>, List<VMovie>> {
    override fun map(from: List<Movie>): List<VMovie> {
        return from.map {
            VMovie(it.id,
                it.posterPath,
                it.releaseDate,
                it.title,
                it.voteAverage.toFloat()/2, //voteAverage goes up to 10 but we have a 5 stars rating
                it.voteCount)
        }
    }
}