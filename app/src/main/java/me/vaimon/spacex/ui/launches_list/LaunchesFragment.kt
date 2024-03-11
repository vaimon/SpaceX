package me.vaimon.spacex.ui.launches_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import me.vaimon.spacex.R
import me.vaimon.spacex.databinding.FragmentLaunchesBinding
import me.vaimon.spacex.ui.LaunchesViewModel
import me.vaimon.spacex.ui.launches_list.adapters.LaunchRecyclerViewAdapter
import me.vaimon.spacex.ui.models.Launch

@AndroidEntryPoint
class LaunchesFragment : Fragment() {

    private lateinit var binding: FragmentLaunchesBinding
    private val characterListAdapter: LaunchRecyclerViewAdapter by lazy {
        LaunchRecyclerViewAdapter(
            object : LaunchRecyclerViewAdapter.OnItemClickListener {
                override fun onLaunchClick(item: Launch) {
                    viewModel.onLaunchClick(item)
                }
            }
        )
    }

    private val viewModel: LaunchesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLaunchesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        binding.rvLaunchesList.apply {
            adapter = characterListAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun setupObservers() {
        viewModel.launches.observe(viewLifecycleOwner) {
            characterListAdapter.replaceWithNewLaunches(it)
            toggleProgressBar(false)
        }

        viewModel.eventBus.observe(viewLifecycleOwner) {
            when (it) {
                is LaunchesViewModel.Event.DetailsNavigationRequired ->
                    navigateToDetailsScreen(it.targetId)

                is LaunchesViewModel.Event.Error -> {
                    showError(it.message)
                    toggleProgressBar(false)
                }
            }
        }
    }
    private fun toggleProgressBar(shouldShow: Boolean) = with(binding) {
        pbLoadingIndicator.isVisible = shouldShow
    }

    private fun showError(message: String?) {
        Toast.makeText(context, message ?: getString(R.string.unknown_error), Toast.LENGTH_SHORT)
            .show()
    }

    private fun navigateToDetailsScreen(launchId: Int) {
        val action = LaunchesFragmentDirections.actionLaunchesListToDetails(launchId)
        findNavController().navigate(action)
    }
}