package br.com.zup.movieflix.data.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "movies")
data class MovieResult(
    @SerializedName("id")
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0,

    @SerializedName("adult")
    var adult: Boolean = false,
    @SerializedName("backdrop_path")
    var backdropPath: String = "",
    @SerializedName("genre_ids")
    var genreIds: List<Int> = listOf(),
    @SerializedName("original_language")
    var originalLanguage: String = "",
    @SerializedName("original_title")
    var originalTitle: String = "",
    @SerializedName("overview")
    var overview: String = "",
    @SerializedName("popularity")
    var popularity: Double = 0.0,
    @SerializedName("poster_path")
    var posterPath: String = "",
    @SerializedName("release_date")
    var releaseDate: String = "",
    @SerializedName("title")
    var title: String = "",
    @SerializedName("video")
    var video: Boolean = false,
    @SerializedName("vote_average")
    var voteAverage: Double = 0.0,
    @SerializedName("vote_count")
    var voteCount: Int = 0,

    var isFavorite: Boolean
): Parcelable