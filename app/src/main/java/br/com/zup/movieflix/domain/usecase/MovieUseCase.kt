package br.com.zup.movieflix.domain.usecase

import android.app.Application
import br.com.zup.movieflix.data.datasource.local.MovieDatabase
import br.com.zup.movieflix.data.model.MovieResult
import br.com.zup.movieflix.domain.model.Movie
import br.com.zup.movieflix.domain.repository.MovieRepository
import br.com.zup.movieflix.ui.viewstate.ViewState

class MovieUseCase(application: Application) {
    private val movieDao = MovieDatabase.getDatabase(application).movieDao()
    private val movieRepository = MovieRepository(movieDao)

    suspend fun getAllMovies(): ViewState<List<Movie>> {
        return try {
            val movies = movieRepository.getAllMovies()
            ViewState.Success(movies)
        } catch (ex: Exception) {
            ViewState.Error(Exception("Não foi possível carregar a lista de filmes!"))
        }
    }

    suspend fun insertMovie(movie: Movie): ViewState<Movie> {
        return try {
            movieRepository.insertMovie(movie)
            ViewState.Success(movie)
        } catch (ex: Exception) {
            ViewState.Error(Exception("Não foi possível cadastrar o filme!"))
        }
    }

    suspend fun getAllMoviesNetwork(): ViewState<List<MovieResult>>{
        return try {
            val movies = movieRepository.geAllMoviesNetwork("pt-BR")
            ViewState.Success(movies.movieResults)
        }catch (ex: Exception){
            ViewState.Error(Exception("Não foi possível carregar a lista de filmes da internet!"))
        }
    }
}