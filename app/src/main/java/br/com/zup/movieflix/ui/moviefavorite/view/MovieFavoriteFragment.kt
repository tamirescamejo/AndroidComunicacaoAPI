package br.com.zup.movieflix.ui.moviefavorite.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import br.com.zup.movieflix.databinding.FragmentMovieAddBinding
import br.com.zup.movieflix.ui.home.view.HomeActivity
import br.com.zup.movieflix.ui.moviefavorite.viewmodel.MovieFavoriteViewModel
import br.com.zup.movieflix.ui.viewstate.ViewState

class MovieFavoriteFragment : Fragment() {
    private lateinit var binding: FragmentMovieAddBinding
    private val viewModel: MovieFavoriteViewModel by lazy {
        ViewModelProvider(this)[MovieFavoriteViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as HomeActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bvSaveMovie.setOnClickListener {
            viewModel.verificationMovie(
                tile = binding.etMovieTitleAdd.text.toString(),
                sinopse = binding.etMovieSinopseAdd.text.toString(),
                nameDirector = binding.etMovieDirectorNameAdd.text.toString(),
                infoDirector = binding.etMovieDirectorInfoAdd.text.toString()
            )
        }
    }

    private fun initObserver() {
        viewModel.movieAddState.observe(this.viewLifecycleOwner) {
            when (it) {
                is ViewState.Success -> {
                    cleanEditText()
                    Toast.makeText(context, "Filme cadastrado com sucesso!", Toast.LENGTH_LONG)
                        .show()
                }
                is ViewState.Error -> {
                    Toast.makeText(context, "${it.throwable.message}", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }

    private fun cleanEditText() {
        binding.etMovieTitleAdd.text.clear()
        binding.etMovieSinopseAdd.text.clear()
        binding.etMovieDirectorInfoAdd.text.clear()
        binding.etMovieDirectorNameAdd.text.clear()
    }
}


