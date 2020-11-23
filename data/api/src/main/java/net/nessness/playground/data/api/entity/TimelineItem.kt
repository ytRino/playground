package net.nessness.playground.data.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * <pre>
 * [
 *  {
 *    "id": "mmen1",
 *    "name": "men1",
 *    "status": "on_sale",
 *    "num_likes": 91,
 *    "num_comments": 59,
 *    "price": 51,
 *    "photo": "https://dummyimage.com/400x400/000/fff?text=men1"
 *  },
 *  ...
 *  </pre>
 */
@Serializable
data class TimelineItem(
    val id: String,
    val name: String,
    val status: String,
    @SerialName("num_likes")
    val numLikes: Int,
    @SerialName("num_comments")
    val numComments: Int,
    val price: Int,
    val photo: String
)
