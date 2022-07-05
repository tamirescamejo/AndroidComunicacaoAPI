package br.com.zup.movieflix.data.datasource.remote

import br.com.zup.movieflix.data.datasource.remote.RetrofitService.Companion.API_KEY
import br.com.zup.movieflix.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    @GET("movie/popular")
    suspend fun getAllMoviesNetwork(
        @Query("api_key")
        apiKey: String? = API_KEY,
        @Query("language")
        language: String?
    ): MovieResponse
}