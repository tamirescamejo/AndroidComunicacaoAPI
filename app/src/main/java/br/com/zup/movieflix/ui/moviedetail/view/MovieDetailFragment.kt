package br.com.zup.movieflix.ui.moviedetail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import br.com.zup.movieflix.MOVIE_KEY
import br.com.zup.movieflix.R
import br.com.zup.movieflix.URL_BASE_IMAGE
import br.com.zup.movieflix.data.model.MovieResult
import br.com.zup.movieflix.databinding.FragmentMovieDetailBinding
import br.com.zup.movieflix.domain.model.Movie
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
        val movie = arguments?.getParcelable<MovieResult>(MOVIE_KEY)

        movie?.let {
            Picasso.get().load(URL_BASE_IMAGE + it.posterPath)
                .into(binding.imageView)

            binding.tvMovieTitle.text = it.title

            binding.tvMovieSinopse.text = it.overview

            binding.ivFavoriteDetail.setImageDrawable(
                ContextCompat.getDrawable(
                    binding.root.context,
                    if (it.isFavorite)
                        R.drawable.ic_favorite
                    else
                        R.drawable.ic_disfavor
                )
            )

            (activity as HomeActivity).supportActionBar?.title = it.title
        }
    }
}