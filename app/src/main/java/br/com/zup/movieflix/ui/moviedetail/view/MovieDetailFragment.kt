package br.com.zup.movieflix.ui.moviedetail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.zup.movieflix.MOVIE_KEY
import br.com.zup.movieflix.data.model.Result
import br.com.zup.movieflix.databinding.FragmentMovieDetailBinding
import br.com.zup.movieflix.ui.home.view.HomeActivity
import com.squareup.picasso.Picasso

class MovieDetailFragment : Fragment() {
    private lateinit var binding: FragmentMovieDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getPassedData()
    }

    private fun getPassedData() {
        val movie = arguments?.getParcelable<Result>(MOVIE_KEY)
        movie?.let {
            Picasso.get().load("http://image.tmdb.org/t/p/w500/${it.posterPath}")
                .into(binding.imageView)
            binding.tvMovieTitle.text = it.title
            binding.tvMovieSinopse.text = it.overview
            (activity as HomeActivity).supportActionBar?.title = it.title
        }
    }
}