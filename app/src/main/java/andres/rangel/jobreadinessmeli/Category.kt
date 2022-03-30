package andres.rangel.jobreadinessmeli

import com.google.gson.annotations.SerializedName

data class Category (
    @SerializedName("domain_id") val domainId: String,
    @SerializedName("domain_name") val domainName: String,
    @SerializedName("category_id") val categoryId: String,
    @SerializedName("category_name") val categoryName: String
)