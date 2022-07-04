package br.com.zup.movieflix.ui.movielist.view

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
import br.com.zup.movieflix.data.model.Result
import br.com.zup.movieflix.databinding.FragmentMovieListBinding
import br.com.zup.movieflix.ui.home.view.HomeActivity
import br.com.zup.movieflix.ui.movielist.viewmodel.MovieViewModel
import br.com.zup.movieflix.ui.viewstate.ViewState

class MovieListFragment : Fragment() {
    private lateinit var binding: FragmentMovieListBinding

    private val viewModel: MovieViewModel by lazy {
        ViewModelProvider(this)[MovieViewModel::class.java]
    }

    private val adapter: MovieAdapter by lazy {
        MovieAdapter(arrayListOf(), this::goToMovieDetail)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as HomeActivity).supportActionBar?.title = getString(R.string.movie_title_menu)
        viewModel.getAllMovies()
        setUpRvMovieList()
        initObserver()
    }

    private fun initObserver() {
        viewModel.movieListState.observe(this.viewLifecycleOwner) {

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
    }

    private fun setUpRvMovieList() {
        binding.rvMovieList.adapter = adapter
        binding.rvMovieList.layoutManager = LinearLayoutManager(context)
    }

    private fun goToMovieDetail(result: Result) {
        val bundle = bundleOf(MOVIE_KEY to result)

        NavHostFragment.findNavController(this).navigate(
            R.id.action_movieListFragment_to_movieDetailFragment, bundle
        )
    }
}