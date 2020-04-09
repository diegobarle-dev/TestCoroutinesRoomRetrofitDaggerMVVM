package uk.co.diegobarle.core.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import uk.co.diegobarle.core.model.entity.Movie

@Dao
interface MoviesDao: BaseEntityDao<Movie>{

    @Query("SELECT * FROM movies WHERE originalTitle LIKE '%' || :query || '%' OR title LIKE '%' || :query || '%' OR overview LIKE '%' || :query || '%'")
    fun getMoviesContaining(query: String): LiveData<List<Movie>>

    @Query("SELECT * FROM movies WHERE id = :id")
    fun getMovie(id: Int): LiveData<Movie>

    @Query("SELECT * FROM movies ORDER BY popularity DESC")
    fun getMostPopularMovies(): LiveData<List<Movie>>
}