package br.com.zup.movieflix.domain.usecase

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.zup.movieflix.*
import br.com.zup.movieflix.data.datasource.local.MovieDatabase
import br.com.zup.movieflix.data.model.MovieResponse
import br.com.zup.movieflix.data.model.Result
import br.com.zup.movieflix.domain.model.Director
import br.com.zup.movieflix.domain.model.Movie
import br.com.zup.movieflix.domain.repository.MovieRepository
import br.com.zup.movieflix.ui.viewstate.ViewState

class MovieUseCase(application: Application) {
    private val movieDao = MovieDatabase.getDatabase(application).movieDao()
    private val movieRepository = MovieRepository(movieDao)

    suspend fun getAllMoviesDB(): ViewState<List<Movie>> {
        return try {
            val movies = movieRepository.getAllMoviesDB()
            ViewState.Success(movies)
        } catch (ex: Exception) {
            ViewState.Error(Exception("Não foi possível carregar a lista de filmes!"))
        }
    }

    suspend fun insertMovieDB(movie: Movie): ViewState<Movie> {
        return try {
            movieRepository.insertMovieDB(movie)
            ViewState.Success(movie)
        } catch (ex: Exception) {
            ViewState.Error(Exception("Não foi possível cadastrar o filme!"))
        }
    }

    suspend fun getAllMoviesNetwork(): ViewState<List<Result>> {
        return try {
            val movies = movieRepository.geAllMoviesNetwork("pt-BR")
            ViewState.Success(movies.results)
        } catch (ex: Exception) {
            ViewState.Error(Exception("Não foi possível carregar a lista de filmes!"))
        }
    }
}