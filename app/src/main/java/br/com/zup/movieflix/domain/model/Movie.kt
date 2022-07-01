package br.com.zup.movieflix.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    var title: String,
    var sinopse: String,
    var image: Int,
    var director: Director,
    var isFavorite: Boolean = false
) : Parcelable