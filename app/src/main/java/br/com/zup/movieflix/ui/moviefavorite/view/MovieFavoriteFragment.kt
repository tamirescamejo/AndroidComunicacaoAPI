package br.com.zup.movieflix.ui.moviefavorite.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import br.com.zup.movieflix.databinding.FragmentMovieFavoriteBinding
import br.com.zup.movieflix.ui.home.view.HomeActivity
import br.com.zup.movieflix.ui.moviefavorite.viewmodel.MovieFavoriteViewModel

class MovieFavoriteFragment : Fragment() {
    private lateinit var binding: FragmentMovieFavoriteBinding

    private val viewModel: MovieFavoriteViewModel by lazy {
        ViewModelProvider(this)[MovieFavoriteViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as HomeActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}


