package net.nessness.playground.data.api.dummy

import kotlin.random.Random

object DummyResponse {
    val cats = """
        [
            {
              "name" : "Cat1",
              "data" : "cat1"
            },
            {
              "name" : "Cat2",
              "data" : "cat2"
            },
            {
              "name" : "Cat3",
              "data" : "cat3"
            }
        ]
    """.trimIndent()

    fun items(cat: String): String {
        return """
            [
                ${List(20){ item(cat) }.joinToString(",") }
            ]
        """.trimIndent()
    }

    private fun item(cat: String): String {
        return """
            {
                "id": "$cat",
                "name": "item$cat${random()}",
                "status": "on_sale",
                "num_likes": ${random()},
                "num_comments": ${random()},
                "price": 51,
                "photo": "https://dummyimage.com/360x360/cff/cccccc?text=$cat"
            }
        """.trimIndent()
    }

    private fun random(): Int = Random.nextInt(100)
}
