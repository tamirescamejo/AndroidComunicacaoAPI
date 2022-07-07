package br.com.zup.movieflix.data.datasource.local.dao

import androidx.room.*
import br.com.zup.movieflix.data.model.MovieResult
import br.com.zup.movieflix.domain.model.Movie

@Dao
interface MovieDAO {
    @Query("SELECT * FROM movies ORDER BY title ASC")
    fun getAllMovies(): List<MovieResult>

    @Query("SELECT * FROM movies WHERE title =:titleMovie")
    fun getMovieTitle(titleMovie: String): MovieResult

    @Query("SELECT * FROM movies WHERE isFavorite = 1")
    fun getAllMoviesFavorited(): List<MovieResult>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovie(movie: MovieResult)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllMovies(listMovies: List<MovieResult>)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateMovieFavorite(movie: MovieResult)
}