package net.nessness.playground.ui.timeline

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.viewbinding.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint
import net.nessness.playground.R
import net.nessness.playground.databinding.FragmentTimelineBinding
import net.nessness.playground.databinding.ItemTimelineBinding

/**
 * Timeline that list items.
 */
@AndroidEntryPoint
class TimelineFragment : Fragment(R.layout.fragment_timeline) {

//    private val viewModel by keyedViewModels<TimelineViewModel>(
//        ownerProducer = { requireParentFragment() },
//        keyProducer = { category }
//    )

    private val viewModel by viewModels<TimelineViewModel>()

    private lateinit var binding: FragmentTimelineBinding

    private val category: String
        get() = requireArguments().getString(KEY_CATEGORY) ?: ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentTimelineBinding.bind(view)

        val adapter = GroupAdapter<GroupieViewHolder<ItemTimelineBinding>>()
        binding.list.adapter = adapter

        viewModel.uiModel.observe(viewLifecycleOwner) {
            when {
                it.loading -> {
                    binding.loading.visibility = View.VISIBLE
                    binding.error.visibility = View.GONE
                }
                it.error -> {
                    binding.loading.visibility = View.GONE
                    binding.error.visibility = View.VISIBLE
                }
                else -> {
                    binding.loading.visibility = View.GONE
                    binding.error.visibility = View.GONE
                    // success
                    adapter.update(it.data.items.map { TimelineItemContainer(it) })
                }
            }
        }

        viewModel.setCategory(category)
    }

    companion object {
        private const val KEY_CATEGORY = "category"

        fun newInstance(category: String): TimelineFragment {
            return TimelineFragment().apply {
                arguments = bundleOf(KEY_CATEGORY to category)
            }
        }
    }
}
