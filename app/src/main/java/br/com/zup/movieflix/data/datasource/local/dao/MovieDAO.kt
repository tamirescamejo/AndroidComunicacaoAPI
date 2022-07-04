package br.com.zup.movieflix.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.zup.movieflix.data.model.Result

@Dao
interface MovieDAO {
    @Query("SELECT * FROM movies ORDER BY title ASC")
    fun getAllMovies(): List<Result>

    @Query("SELECT * FROM movies WHERE title =:titleMovie")
    fun getMovieTitle(titleMovie: String): Result

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(result: Result)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMovies(movies: List<Result>)
}