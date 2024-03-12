package me.vaimon.spacex.ui.launch_details

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import coil.load
import me.vaimon.spacex.R
import me.vaimon.spacex.SpaceXApp
import me.vaimon.spacex.databinding.FragmentLaunchDetailsBinding
import me.vaimon.spacex.ui.MainActivity
import me.vaimon.spacex.ui.models.DetailedLaunch
import javax.inject.Inject


class LaunchDetailsFragment : Fragment() {
    private lateinit var binding: FragmentLaunchDetailsBinding

    private val navigationArgs: LaunchDetailsFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: LaunchDetailsViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity().application as SpaceXApp).appComponent.launchDetailsComponent().create()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLaunchDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        viewModel.startObserving(navigationArgs.launchId)
    }

    private fun setupObservers() {
        viewModel.eventBus.observe(viewLifecycleOwner) {
            when (it) {
                is LaunchDetailsViewModel.Event.Error -> showError(it.message)
            }
        }

        viewModel.launchDetails.observe(viewLifecycleOwner) {
            setupTitle(it.name)
            setupData(it)
        }
    }

    private fun showError(message: String?) {
        Toast.makeText(context, message ?: getString(R.string.unknown_error), Toast.LENGTH_SHORT)
            .show()
    }

    private fun setupTitle(title: String) = (requireActivity() as MainActivity).setTitle(title)

    private fun setupData(launch: DetailedLaunch) = with(binding) {
        ivBadge.load(launch.patchUrl) {
            crossfade(true)
            placeholder(R.drawable.logo)
        }
        tvDetails.text = launch.details
        tvYear.text = getString(R.string.launched_in, launch.year)
        tvSuccess.text = if (launch.success)
            getString(R.string.successful)
        else
            getString(R.string.failed)
        launch.youtubeUrl?.let { url ->
            btnWatchOnYoutube.isVisible = true
            btnWatchOnYoutube.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            }
        }
    }

}