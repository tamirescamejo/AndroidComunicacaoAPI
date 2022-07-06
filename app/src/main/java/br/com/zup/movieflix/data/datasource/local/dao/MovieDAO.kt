package br.com.zup.movieflix.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.zup.movieflix.data.model.MovieResult
import br.com.zup.movieflix.domain.model.Movie

@Dao
interface MovieDAO {
    @Query("SELECT * FROM movies ORDER BY title ASC")
    fun getAllMovies(): List<MovieResult>

    @Query("SELECT * FROM movies WHERE title =:titleMovie")
    fun getMovieTitle(titleMovie: String): MovieResult

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: MovieResult)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMovies(listMovies: List<MovieResult>)
}