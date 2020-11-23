package net.nessness.playground.ui.timeline

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import net.nessness.playground.databinding.FragmentTimelineTabsBinding
import net.nessness.playground.R

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class TimelineTabsFragment : Fragment(R.layout.fragment_timeline_tabs) {

    private val viewModel by viewModels<TimelineTabsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentTimelineTabsBinding.bind(view)

        val adapter = TimelineTabAdapter(this).also {
            // Preset existing data before setting up adapter to prevent view states resetting for config change.
            viewModel.uiModel.value?._data?.let { data ->
                it.update(data.categories)
            }
        }
        binding.pager.also {
            it.adapter = adapter
            it.offscreenPageLimit = 2
        }

        // Combine tabs with viewpager
        TabLayoutMediator(binding.tabs, binding.pager) { tab, position ->
            tab.text = adapter.getTabName(position)
        }.attach()

        viewModel.uiModel.observe(viewLifecycleOwner) {
            when {
                it.loading -> {
                    binding.loading.visibility = View.VISIBLE
                    binding.error.visibility = View.GONE
                    binding.pager.visibility = View.GONE
                }
                it.error -> {
                    binding.loading.visibility = View.GONE
                    binding.error.visibility = View.VISIBLE
                    binding.pager.visibility = View.GONE
                }
                else -> {
                    binding.loading.visibility = View.GONE
                    binding.error.visibility = View.GONE
                    binding.pager.visibility = View.VISIBLE
                    // success
                    adapter.update(it.data.categories)
                }
            }
        }
    }
}
