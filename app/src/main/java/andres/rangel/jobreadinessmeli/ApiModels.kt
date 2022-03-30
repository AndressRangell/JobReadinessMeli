package andres.rangel.jobreadinessmeli

import com.google.gson.annotations.SerializedName

data class Category (
    @SerializedName("category_id") var categoryId: String,
    @SerializedName("category_name") var categoryName: String
)

data class ItemId(
    @SerializedName("content") var content: ArrayList<DataId> = arrayListOf()
)

data class DataId(
    @SerializedName("id") var id: String
)

data class Item (
    @SerializedName("body") var body: ItemBody? = ItemBody()
)

data class ItemBody(
    @SerializedName("title") var title: String? = null,
    @SerializedName("price") var price: Double? = null,
    @SerializedName("attributes") var details: ArrayList<Detail> = arrayListOf(),
    @SerializedName("seller_address") var location: Address? = Address(),
    @SerializedName("pictures") var pictures: ArrayList<ItemPictures> = arrayListOf()
)

data class Detail (
    @SerializedName("value_name") var valueName: String? = null
)

data class Address (
    @SerializedName("city") var city: Name? = Name(),
    @SerializedName("country") var country: Name? = Name()
)

data class Name(
    @SerializedName("name") var name: String? = null
)

data class ItemPictures (
    @SerializedName("url") var url: String? = null
)