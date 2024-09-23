package com.example.alextimofeev_hw3.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.alextimofeev_hw3.databinding.FragmentChoosenPhotoBinding


class ChoosenPhotoFragment : Fragment() {
    private var url: String? = null
    private var sol: String? = null
    private var date: String? = null
    private var rover: String? = null
    private var camera: String? = null


    private var _binding: FragmentChoosenPhotoBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            url = it.getString(PARAM_URL)
            sol = it.getString(PARAM_SOL)
            date = it.getString(PARAM_DATE)
            rover = it.getString(PARAM_ROVER)
            camera = it.getString(PARAM_CAMERA)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChoosenPhotoBinding.inflate(inflater)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        url?.let {
            Glide
                .with(this)
                .load(url!!)
                .into(binding.imagePhoto)
        }

        binding.cameraName.text = "Camera: $camera"
        binding.earthDate.text = "Date: $date"
        binding.solDate.text = "Sol: $sol"
        binding.roverName.text = "Rover: $rover"

    }
    companion object {
        const val PARAM_URL = "PARAM_URL"
        const val PARAM_CAMERA = "PARAM_CAMERA"
        const val PARAM_DATE = "PARAM_DATE"
        const val PARAM_ROVER = "PARAM_ROVER"
        const val PARAM_SOL = "PARAM_SOL"
    }

}