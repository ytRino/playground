package net.nessness.playground.ui.timeline

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import net.nessness.playground.R
import net.nessness.playground.common.GlideApp
import net.nessness.playground.databinding.ItemTimelineBinding
import net.nessness.playground.usecase.timeline.model.SalesStatus
import net.nessness.playground.usecase.timeline.model.TimelineItem

class TimelineItemContainer(
    private val item: TimelineItem
) : BindableItem<ItemTimelineBinding>() {

    override fun getLayout(): Int = R.layout.item_timeline

    override fun initializeViewBinding(view: View) = ItemTimelineBinding.bind(view)

    override fun bind(binding: ItemTimelineBinding, position: Int) {
        binding.apply {
            GlideApp.with(image).load(item.photo)
                .into(image)
            name.text = item.name
            likes.text = item.numLikes.toString()
            comments.text = item.numComments.toString()
            price.text = price.resources.getString(R.string.price_format, item.price)
        }
    }
}
