package br.com.zup.movieflix.ui.moviefavorite.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.zup.movieflix.MOVIE_KEY
import br.com.zup.movieflix.R
import br.com.zup.movieflix.data.model.MovieResult
import br.com.zup.movieflix.databinding.FragmentMovieFavoriteBinding
import br.com.zup.movieflix.ui.home.view.HomeActivity
import br.com.zup.movieflix.ui.moviefavorite.viewmodel.MovieFavoriteViewModel
import br.com.zup.movieflix.ui.viewstate.ViewState

class MovieFavoriteFragment : Fragment() {
    private lateinit var binding: FragmentMovieFavoriteBinding

    private val viewModel: MovieFavoriteViewModel by lazy {
        ViewModelProvider(this)[MovieFavoriteViewModel::class.java]
    }

    private val adapter: MovieFavoriteAdapter by lazy {
        MovieFavoriteAdapter(arrayListOf(), this::goToMovieDetail, this::disfavorMovie)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllMoviesFavorited()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as HomeActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
        setUpRvMovieList()
    }

    private fun initObserver() {
        viewModel.movieListFavoriteState.observe(this.viewLifecycleOwner) {

            when (it) {
                is ViewState.Success -> {
                    adapter.updateMovieList(it.data.toMutableList())
                }
                is ViewState.Error -> {
                    Toast.makeText(
                        context,
                        "${it.throwable.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
                else -> {}
            }
        }

        viewModel.movieDisfavorState.observe(this.viewLifecycleOwner) {
            when (it) {
                is ViewState.Success -> {
                    Toast.makeText(
                        context,
                        "Filme ${it.data.title} foi desfavoritado!",
                        Toast.LENGTH_LONG
                    ).show()
                }
                is ViewState.Error -> {
                    Toast.makeText(
                        context,
                        "${it.throwable.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
                else -> {}
            }
        }
    }

    private fun setUpRvMovieList() {
        binding.rvMovieFavoriteList.adapter = adapter
        binding.rvMovieFavoriteList.layoutManager = LinearLayoutManager(context)
    }

    private fun goToMovieDetail(movie: MovieResult) {
        val bundle = bundleOf(MOVIE_KEY to movie)

        NavHostFragment.findNavController(this).navigate(
            R.id.action_movieFavoriteFragment_to_movieDetailFragment, bundle
        )
    }

    private fun disfavorMovie(movie: MovieResult) {
        viewModel.disfavorMovie(movie)
    }
}


