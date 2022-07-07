package br.com.zup.movieflix.ui.moviefavorite.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.movieflix.R
import br.com.zup.movieflix.URL_BASE_IMAGE
import br.com.zup.movieflix.data.model.MovieResult
import br.com.zup.movieflix.databinding.MovieItemBinding
import com.squareup.picasso.Picasso

class MovieFavoriteAdapter(
    private var movieList: MutableList<MovieResult>,
    private val clickMovie: (movieResult: MovieResult) -> Unit,
    private val clickDisfavor: (movieResult: MovieResult) -> Unit
) :
    RecyclerView.Adapter<MovieFavoriteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movieList[position]
        holder.showMovieInfo(movie)
        holder.binding.ivMoviePoster.setOnClickListener {
            clickMovie(movie)
        }
        holder.binding.ivFavorite.setOnClickListener {
            movie.isFavorite = !movie.isFavorite
            clickDisfavor(movie)
            movieList.remove(movie)
            notifyItemRemoved(position)
        }
    }

    override fun getItemCount() = movieList.size

    fun updateMovieList(newList: MutableList<MovieResult>) {
        movieList = newList
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun showMovieInfo(movieResult: MovieResult) {
            binding.tvMovieName.text = movieResult.title
            Picasso.get().load(URL_BASE_IMAGE + movieResult.posterPath)
                .into(binding.ivMoviePoster)

            binding.ivFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    binding.root.context,
                    if (movieResult.isFavorite)
                        R.drawable.ic_favorite
                    else
                        R.drawable.ic_disfavor
                )
            )
        }
    }
}