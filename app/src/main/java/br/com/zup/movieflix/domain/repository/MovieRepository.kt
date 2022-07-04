package br.com.zup.movieflix.domain.repository

import br.com.zup.movieflix.data.datasource.local.dao.MovieDAO
import br.com.zup.movieflix.data.datasource.network.RetrofitService
import br.com.zup.movieflix.data.model.MovieResponse
import br.com.zup.movieflix.domain.model.Movie

class MovieRepository(private val movieDAO: MovieDAO) {

    fun getAllMoviesDB(): List<Movie> = movieDAO.getAllMovies()

    fun insertMovieDB(movie: Movie) {
        movieDAO.insertMovie(movie)
    }

    suspend fun geAllMoviesNetwork(
        language: String?,
    ): MovieResponse {

        return RetrofitService.apiService.getMovies(
            language = language,
        )
    }
}