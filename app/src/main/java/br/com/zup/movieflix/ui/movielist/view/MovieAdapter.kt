package br.com.zup.movieflix.ui.movielist.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.movieflix.data.model.Result
import br.com.zup.movieflix.databinding.MovieItemBinding
import com.squareup.picasso.Picasso

class MovieAdapter(
    private var movieList: MutableList<Result>,
    private val clickMovie: (result: Result) -> Unit
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

    fun updateMovieList(newList: MutableList<Result>) {
        if (movieList.size == 0) {
            movieList = newList
        } else {
            movieList.addAll(newList)
        }
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun showMovieInfo(result: Result) {
            binding.tvMovieName.text = result.title
            Picasso.get().load("http://image.tmdb.org/t/p/w500/${result.posterPath}")
                .into(binding.ivMoviePoster)
        }
    }
}