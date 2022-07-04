package br.com.zup.movieflix.domain.usecase

import android.app.Application
import br.com.zup.movieflix.data.datasource.local.MovieDatabase
import br.com.zup.movieflix.data.model.Result
import br.com.zup.movieflix.domain.repository.MovieRepository
import br.com.zup.movieflix.ui.viewstate.ViewState

class MovieUseCase(application: Application) {
    private val movieDao = MovieDatabase.getDatabase(application).movieDao()
    private val movieRepository = MovieRepository(movieDao)

    suspend fun getAllMoviesDB(): ViewState<List<Result>> {
        return try {
            val movies = movieRepository.getAllMoviesDB()
            ViewState.Success(movies)
        } catch (ex: Exception) {
            ViewState.Error(Exception("Não foi possível carregar a lista de filmes DB!"))
        }
    }

    suspend fun insertMovieDB(movie: Result): ViewState<Result> {
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
            movieRepository.insertAllMoviesDB(movies.results)
            ViewState.Success(movies.results)
        } catch (ex: Exception) {
           getAllMoviesDB()
        }
    }
}