package br.com.zup.movieflix.ui.movieadd.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.zup.movieflix.domain.model.Director
import br.com.zup.movieflix.domain.model.Movie
import br.com.zup.movieflix.domain.usecase.MovieUseCase
import br.com.zup.movieflix.ui.viewstate.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieAddViewModel(application: Application) : AndroidViewModel(application) {
    private val movieUseCase = MovieUseCase(application)
    val movieAddState = MutableLiveData<ViewState<Movie>>()

    private fun insertMovie(movie: Movie) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    movieUseCase.insertMovie(movie)
                }
                movieAddState.value = response
            } catch (ex: Exception) {
                movieAddState.value =
                    ViewState.Error(Throwable("Não foi possível inserir o filme!"))
            }
        }
    }

    fun verificationMovie(
        tile: String,
        sinopse: String,
        nameDirector: String,
        infoDirector: String
    ) {
        if (tile.isNotEmpty() && sinopse.isNotEmpty()
            && nameDirector.isNotEmpty() && infoDirector.isNotEmpty()
        ) {
            insertMovie(
                Movie(
                    title = tile,
                    sinopse = sinopse,
                    director = Director(
                        name = nameDirector,
                        info = infoDirector
                    )
                )
            )
        } else {
            movieAddState.value =
                ViewState.Error(Throwable("Por favor preencha os campos corretamente!"))
        }
    }

}