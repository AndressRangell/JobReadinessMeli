package andres.rangel.jobreadinessmeli

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

private const val BASE_URL = "https://api.mercadolibre.com/"

val retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface ApiService {

    @GET("sites/MLM/domain_discovery/search?")
    suspend fun getCategory(
        @Query("q") query: String,
        @Header("Authorization") token: String
    ): Response<List<Category>>

}

object MeliApi {
    val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}