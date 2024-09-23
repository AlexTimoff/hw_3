package com.example.alextimofeev_hw3.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alextimofeev_hw3.R
import com.example.alextimofeev_hw3.databinding.FragmentPhotoListBinding
import com.example.alextimofeev_hw3.entity.PhotoItem
import com.example.alextimofeev_hw3.presentation.adapter.MarsPhotoAdapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class PhotoListFragment : Fragment() {
    private val viewModel: MarsPhotoListViewModel by viewModels()
    private var _binding: FragmentPhotoListBinding? = null
    private val binding get() = _binding!!
    private val photoAdapter = MarsPhotoAdapter { photoItem -> onItemClick(photoItem) }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPhotoListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = photoAdapter

        binding.recyclerView.layoutManager =
            GridLayoutManager(requireContext(), 3, RecyclerView.VERTICAL,false)

        viewModel.photos.onEach {
            photoAdapter.setData(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.isLoading.onEach { isLoading ->
            binding.progress.visibility = if (isLoading)
                View.VISIBLE
            else
                View.GONE
        }.launchIn(viewLifecycleOwner.lifecycleScope)

    }

    private fun onItemClick(item: PhotoItem) {
        parentFragmentManager.commit {
            val bundle = bundleOf(
                ChoosenPhotoFragment.PARAM_URL to item.url,
                ChoosenPhotoFragment.PARAM_CAMERA to item.camera.name,
                ChoosenPhotoFragment.PARAM_DATE to item.date,
                ChoosenPhotoFragment.PARAM_ROVER to item.rover.name,
                ChoosenPhotoFragment.PARAM_SOL to item.sol.toString()
            )
            replace<ChoosenPhotoFragment>(R.id.fragmentContainer, args = bundle)
            addToBackStack(ChoosenPhotoFragment::javaClass.name)
        }
    }

}