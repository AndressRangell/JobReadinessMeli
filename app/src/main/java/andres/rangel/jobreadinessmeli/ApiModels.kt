package andres.rangel.jobreadinessmeli

import com.google.gson.annotations.SerializedName

data class Category (
    @SerializedName("domain_id") var domainId: String,
    @SerializedName("domain_name") var domainName: String,
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
    @SerializedName("pictures") var pictures: ArrayList<ItemPictures> = arrayListOf()
)

data class ItemPictures (
    @SerializedName("url") var url: String? = null
)