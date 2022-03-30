package andres.rangel.jobreadinessmeli

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class TokenInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val token = "APP_USR-3424956346656258-033013-5fed353f284a43ac95ca8ded1f04c842-316674397"
        //rewrite the request to add bearer token
        val newRequest: Request = chain.request().newBuilder()
            .header("Authorization", "Bearer $token")
            .build()
        return chain.proceed(newRequest)
    }
}