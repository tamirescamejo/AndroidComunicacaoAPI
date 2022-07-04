package br.com.zup.movieflix.domain.repository

import br.com.zup.movieflix.data.datasource.local.dao.MovieDAO
import br.com.zup.movieflix.data.datasource.network.RetrofitService
import br.com.zup.movieflix.data.model.MovieResponse
import br.com.zup.movieflix.data.model.Result

class MovieRepository(private val movieDAO: MovieDAO) {

    fun getAllMoviesDB(): List<Result> = movieDAO.getAllMovies()

    fun insertMovieDB(result: Result) {
        movieDAO.insertMovie(result)
    }

    fun insertAllMoviesDB(movies: List<Result>) {
        movieDAO.insertAllMovies(movies)
    }

    suspend fun geAllMoviesNetwork(
        language: String?,
    ): MovieResponse {

        return RetrofitService.apiService.getMovies(
            language = language,
        )
    }
}