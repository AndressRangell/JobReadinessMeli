package andres.rangel.jobreadinessmeli.data.model

import com.google.gson.annotations.SerializedName

// Model category predictor
data class Category(
    @SerializedName("category_id")
    var categoryId: String,
    @SerializedName("category_name")
    var categoryName: String
)

// Models top 20 Highlight
data class ItemId(
    var content: ArrayList<DataId> = arrayListOf()
)

data class DataId(
    var id: String,
    var type: String
)

// Models product details
data class Item(
    var body: ItemBody? = ItemBody()
)

data class ItemBody(
    var id: String? = null,
    var title: String? = null,
    var price: Double? = null,
    var attributes: ArrayList<Detail> = arrayListOf(),
    @SerializedName("seller_address")
    var location: Address? = Address(),
    var pictures: ArrayList<ItemPictures> = arrayListOf()
)

data class Detail(
    @SerializedName("value_name")
    var valueName: String? = null
)

data class Address(
    var city: Name? = Name(),
    var country: Name? = Name()
)

data class Name(
    var name: String? = null
)

data class ItemPictures(
    var url: String? = null
)

data class Description(
    @SerializedName("plain_text")
    var description: String? = null
)

// Model access token
data class Token(
    @SerializedName("access_token")
    var token: String
)