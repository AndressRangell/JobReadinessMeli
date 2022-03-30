package andres.rangel.jobreadinessmeli

import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

private const val BASE_URL = "https://api.mercadolibre.com/"

val interceptor = TokenInterceptor()

val client: OkHttpClient = OkHttpClient.Builder()
    .addInterceptor(interceptor).build();

val retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .client(client)
    .baseUrl(BASE_URL)
    .build()

interface ApiService {

    @GET("sites/MLM/domain_discovery/search?")
    suspend fun getCategory(
        @Query("q") query: String
    ): Response<List<Category>>

    @GET
    suspend fun getHighlights(
        @Url query: String
    ): Response<ItemId>

    @GET("items?")
    suspend fun getItems(
        @Query("ids") query: String
    ): Response<List<Item>>

}

object MeliApi {
    val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}