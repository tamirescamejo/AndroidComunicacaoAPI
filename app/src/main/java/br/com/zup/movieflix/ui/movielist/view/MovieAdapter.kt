package br.com.zup.movieflix.ui.movielist.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.movieflix.URL_BASE_IMAGE
import br.com.zup.movieflix.data.model.MovieResult
import br.com.zup.movieflix.databinding.MovieItemBinding
import br.com.zup.movieflix.domain.model.Movie
import com.squareup.picasso.Picasso

class MovieAdapter(
    private var movieList: MutableList<MovieResult>,
    private val clickMovie: (movieResult: MovieResult) -> Unit
) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movieList[position]
        holder.showMovieInfo(movie)
        holder.binding.cvItemLista.setOnClickListener {
            clickMovie(movie)
        }
        holder.showMovieInfo(movie)
    }

    override fun getItemCount() = movieList.size

    fun updateMovieList(newList: MutableList<MovieResult>) {
        if (movieList.size == 0) {
            movieList = newList
        } else {
            movieList.addAll(newList)
        }
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun showMovieInfo(movieResult: MovieResult) {
            binding.tvMovieName.text = movieResult.title
            Picasso.get().load(URL_BASE_IMAGE + movieResult.posterPath)
                .into(binding.ivMoviePoster)
        }
    }
}