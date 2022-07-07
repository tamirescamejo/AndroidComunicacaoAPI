package br.com.zup.movieflix.ui.movielist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.com.zup.movieflix.data.model.MovieResult
import br.com.zup.movieflix.domain.model.SingleLiveEvent
import br.com.zup.movieflix.domain.usecase.MovieUseCase
import br.com.zup.movieflix.ui.viewstate.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieViewModel(application: Application) : AndroidViewModel(application) {
    private val movieUseCase = MovieUseCase(application)
    val movieListState = SingleLiveEvent<ViewState<List<MovieResult>>>()
    val movieFavoritedState = SingleLiveEvent<ViewState<MovieResult>>()
    val loading = SingleLiveEvent<ViewState<Boolean>>()

    fun getAllMovies() {
        loading.value = ViewState.Loading(true)
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    movieUseCase.getAllMoviesNetwork()
                }
                movieListState.value = response
            } catch (ex: Exception) {
                movieListState.value =
                    ViewState.Error(Throwable("Não foi possível carregar a lista vinda da internet!"))
            } finally {
                loading.value = ViewState.Loading(false)
            }
        }
    }

    fun updateMovieFavorited(movieResult: MovieResult) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    movieUseCase.updateMovieFavorite(movieResult)
                }
                movieFavoritedState.value = response
            } catch (ex: Exception) {
                movieFavoritedState.value =
                    ViewState.Error(Throwable("Não foi possível atualizar o filme!"))
            }
        }
    }
}