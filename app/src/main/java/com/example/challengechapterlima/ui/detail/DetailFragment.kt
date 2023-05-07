package com.example.challengechapterlima.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.challengechapterlima.R
import com.example.challengechapterlima.databinding.FragmentDetailBinding
import com.example.challengechapterlima.databinding.FragmentHomeBinding
import com.example.challengechapterlima.ui.home.HomeAdapter
import com.example.challengechapterlima.ui.home.HomeViewModel

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getDetailMovie(arguments?.getInt("MOVIE_ID")!!)
        bind()
    }

    private fun bind() {
        viewModel.loadingState.observe(viewLifecycleOwner) {
            binding.pb.isVisible = it
            binding.name.isVisible = !it
            binding.photo.isVisible = !it
            binding.backdrop.isVisible = !it
            binding.desc.isVisible = !it
        }
        viewModel.movie.observe(viewLifecycleOwner) {
            val imageUrl = "https://image.tmdb.org/t/p/w500"
            Glide.with(requireContext()).load(imageUrl + it.backdropPath).into(binding.backdrop)
            Glide.with(requireContext()).load(imageUrl + it.posterPath).into(binding.photo)
            binding.name.text = it.title.toString()
            binding.topAppBar.title = it.title.toString()
            binding.desc.text = it.overview.toString()
        }
        binding.topAppBar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_detailFragment_to_homeFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}