package andres.rangel.jobreadinessmeli

import com.google.gson.annotations.SerializedName

data class Category (
    @SerializedName("category_id")
    var categoryId: String,
    @SerializedName("category_name")
    var categoryName: String
)

data class ItemId(
    var content: ArrayList<DataId> = arrayListOf()
)

data class DataId(
    var id: String
)

data class Item (
    var body: ItemBody? = ItemBody()
)

data class ItemBody(
    var title: String? = null,
    var price: Double? = null,
    var attributes: ArrayList<Detail> = arrayListOf(),
    @SerializedName("seller_address")
    var location: Address? = Address(),
    var pictures: ArrayList<ItemPictures> = arrayListOf()
)

data class Detail (
    @SerializedName("value_name")
    var valueName: String? = null
)

data class Address (
    var city: Name? = Name(),
    var country: Name? = Name()
)

data class Name(
    var name: String? = null
)

data class ItemPictures (
    var url: String? = null
)