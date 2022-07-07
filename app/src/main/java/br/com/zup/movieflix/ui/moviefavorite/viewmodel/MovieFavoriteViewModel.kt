package br.com.zup.movieflix.ui.moviefavorite.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.zup.movieflix.data.model.MovieResult
import br.com.zup.movieflix.domain.model.Director
import br.com.zup.movieflix.domain.model.Movie
import br.com.zup.movieflix.domain.model.SingleLiveEvent
import br.com.zup.movieflix.domain.usecase.MovieUseCase
import br.com.zup.movieflix.ui.viewstate.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieFavoriteViewModel(application: Application) : AndroidViewModel(application) {
    private val movieUseCase = MovieUseCase(application)
    val movieListFavoriteState = SingleLiveEvent<ViewState<List<MovieResult>>>()
    val movieDisfavorState = SingleLiveEvent<ViewState<MovieResult>>()

    fun disfavorMovie(movie: MovieResult) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    movieUseCase.updateMovieFavorite(movie)
                }
                movieDisfavorState.value = response
            } catch (ex: Exception) {
                movieDisfavorState.value =
                    ViewState.Error(Throwable("Não foi desfavoritar o filme!"))
            }
        }
    }

    fun getAllMoviesFavorited() {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    movieUseCase.getAllMoviesFavorited()
                }
                movieListFavoriteState.value = response
            } catch (ex: Exception) {
                movieListFavoriteState.value =
                    ViewState.Error(Throwable("Não foi carregar a lista de filmes favoritos!"))
            }
        }
    }
}

