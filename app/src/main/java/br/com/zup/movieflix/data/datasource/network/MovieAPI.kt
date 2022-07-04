package br.com.zup.movieflix.data.datasource.network

import br.com.zup.movieflix.data.datasource.network.RetrofitService.Companion.API_KEY
import br.com.zup.movieflix.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {
    @GET("movie/popular")
    suspend fun getMovies(
        @Query("api_key") apiKey: String? = API_KEY,
        @Query("language") language: String?
    ): MovieResponse
}