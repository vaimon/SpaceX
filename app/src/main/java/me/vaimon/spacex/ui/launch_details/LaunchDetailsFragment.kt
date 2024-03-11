package me.vaimon.spacex.ui.launch_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import me.vaimon.spacex.databinding.FragmentLaunchDetailsBinding

@AndroidEntryPoint
class LaunchDetailsFragment : Fragment() {
    private lateinit var binding: FragmentLaunchDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLaunchDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }
}