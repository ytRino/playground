package net.nessness.playground.data.api

object TestData {

    val master = """
        [
            {
              "name" : "Cat1",
              "data" : "https://dummy/cat1.json"
            },
            {
              "name" : "Cat2",
              "data" : "https://dummy/cat2.json"
            },
            {
              "name" : "Cat3",
              "data" : "https://dummy/cat3.json"
            }
        ]
    """.trimIndent()

    val items = """
        [
          {
            "id": "cat1",
            "name": "item1",
            "status": "on_sale",
            "num_likes": 91,
            "num_comments": 59,
            "price": 51,
            "photo": "https://dummyimage.com/360x360/ccc/333?text=cat1"
          },
          {
            "id": "cat2",
            "name": "item2",
            "status": "on_sale",
            "num_likes": 81,
            "num_comments": 89,
            "price": 2,
            "photo": "https://dummyimage.com/360x360/ccc/333?text=cat2"
          },
          {
            "id": "cat3",
            "name": "item3",
            "status": "sold_out",
            "num_likes": 17,
            "num_comments": 58,
            "price": 38,
            "photo": "https://dummyimage.com/360x360/ccc/333?text=cat3"
          },
          {
            "id": "cat4",
            "name": "item4",
            "status": "on_sale",
            "num_likes": 41,
            "num_comments": 54,
            "price": 38,
            "photo": "https://dummyimage.com/360x360/ccc/333?text=cat4"
          },
          {
            "id": "cat5",
            "name": "item5",
            "status": "on_sale",
            "num_likes": 19,
            "num_comments": 32,
            "price": 23,
            "photo": "https://dummyimage.com/360x360/ccc/333?text=cat5"
          },
          {
            "id": "cat6",
            "name": "item6",
            "status": "sold_out",
            "num_likes": 38,
            "num_comments": 66,
            "price": 92,
            "photo": "https://dummyimage.com/360x360/ccc/333?text=cat6"
          },
          {
            "id": "cat7",
            "name": "item7",
            "status": "on_sale",
            "num_likes": 17,
            "num_comments": 66,
            "price": 33,
            "photo": "https://dummyimage.com/360x360/ccc/333?text=cat7"
          }
       ]
    """.trimIndent()
}
