package br.com.zup.movieflix.domain.usecase

import android.app.Application
import br.com.zup.movieflix.data.datasource.local.MovieDatabase
import br.com.zup.movieflix.data.model.MovieResult
import br.com.zup.movieflix.domain.repository.MovieRepository
import br.com.zup.movieflix.ui.viewstate.ViewState

class MovieUseCase(application: Application) {
    private val movieDao = MovieDatabase.getDatabase(application).movieDao()
    private val movieRepository = MovieRepository(movieDao)

    suspend fun getAllMovies(): ViewState<List<MovieResult>> {
        return try {
            val movies = movieRepository.getAllMovies()
            ViewState.Success(movies)
        } catch (ex: Exception) {
            ViewState.Error(Exception("Não foi possível carregar a lista de filmes do Banco de Dados!"))
        }
    }

    suspend fun getAllMoviesFavorited(): ViewState<List<MovieResult>>{
        return try {
            val movies = movieRepository.getAllMoviesFavorited()
            ViewState.Success(movies)
        }catch (ex: Exception){
            ViewState.Error(Exception("Não foi possível carregar a lista de filmes favoritos!"))
        }
    }

    suspend fun updateMovieFavorite(movie: MovieResult): ViewState<MovieResult>{
        return try {
            movieRepository.updateMovieFavorited(movie)
            ViewState.Success(movie)
        }catch (ex: Exception){
            ViewState.Error(Exception("Não foi possível atualizar o filme!"))
        }
    }

    suspend fun insertMovie(movie: MovieResult): ViewState<MovieResult> {
        return try {
            movieRepository.insertMovie(movie)
            ViewState.Success(movie)
        } catch (ex: Exception) {
            ViewState.Error(Exception("Não foi possível cadastrar o filme!"))
        }
    }

    suspend fun getAllMoviesNetwork(): ViewState<List<MovieResult>>{
        return try {
            val response = movieRepository.geAllMoviesNetwork("pt-BR")
            movieRepository.insertAllMoviesDB(response.movieResults)
            ViewState.Success(response.movieResults)
        }catch (ex: Exception){
            getAllMovies()
        }
    }
}