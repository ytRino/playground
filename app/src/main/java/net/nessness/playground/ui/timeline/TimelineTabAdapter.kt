package net.nessness.playground.ui.timeline

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class TimelineTabAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val categories = mutableListOf<String>()

    override fun getItemCount(): Int = categories.size

    override fun createFragment(position: Int): Fragment {
        return TimelineFragment.newInstance(categories[position])
    }

    fun update(categories: List<String>) {
        this.categories.clear()
        this.categories.addAll(categories)
        notifyDataSetChanged()
    }

    fun getTabName(position: Int): String {
        return categories[position]
    }
}
