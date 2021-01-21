package ch.hearc.ezworkout.networking.utils

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.IOException

/***
 * Intercept json retrofit request and show request and raw json response if response not successful
 * @author Jonatan Baumgartner<jonatan.baumgartner@ik.me>
 */
class LogJsonInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val response: Response = chain.proceed(request)
        val rawJson: String = response.body()!!.string()
        if(response.isSuccessful){
            Log.d("json interceptor request", String.format("raw request is: %s: %s", response.request().method(), response.request().url().toString()))
            Log.d("json interceptor response", String.format("raw JSON response is: %s", rawJson))
        }

        // Re-create the response before returning it because body can be read only once
        return response.newBuilder()
            .body(ResponseBody.create(response.body()!!.contentType(), rawJson)).build()
    }

}