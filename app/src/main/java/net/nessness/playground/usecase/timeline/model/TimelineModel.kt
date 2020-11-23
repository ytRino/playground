package net.nessness.playground.usecase.timeline.model

data class TimelineModel(
    val items: List<TimelineItem>
)

data class TimelineItem(
    val id: String,
    val name: String,
    val status: SalesStatus,
    val numLikes: Int,
    val numComments: Int,
    val price: Int,
    val photo: String
)

enum class SalesStatus {
    ON_SALE, SOLD_OUT, UNKNOWN
}

fun SalesStatus(status: String) = when (status) {
    "on_sale" -> SalesStatus.ON_SALE
    "sold_out" -> SalesStatus.SOLD_OUT
    else -> SalesStatus.UNKNOWN
}
