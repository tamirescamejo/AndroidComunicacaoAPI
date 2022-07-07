package br.com.zup.movieflix.domain.repository

import br.com.zup.movieflix.data.datasource.local.dao.MovieDAO
import br.com.zup.movieflix.data.datasource.remote.RetrofitService
import br.com.zup.movieflix.data.model.MovieResponse
import br.com.zup.movieflix.data.model.MovieResult

class MovieRepository(private val movieDAO: MovieDAO) {

    suspend fun getAllMovies(): List<MovieResult> = movieDAO.getAllMovies()

    suspend fun insertMovie(movie: MovieResult) {
        movieDAO.insertMovie(movie)
    }

    suspend fun insertAllMoviesDB(listMovies: List<MovieResult>) {
        movieDAO.insertAllMovies(listMovies)
    }

    suspend fun getAllMoviesFavorited(): List<MovieResult> = movieDAO.getAllMoviesFavorited()

    suspend fun updateMovieFavorited(movie: MovieResult){
        movieDAO.updateMovieFavorite(movie)
    }

    suspend fun geAllMoviesNetwork(language: String?): MovieResponse {
        return RetrofitService.apiService.getAllMoviesNetwork(
            language = language
        )
    }
}