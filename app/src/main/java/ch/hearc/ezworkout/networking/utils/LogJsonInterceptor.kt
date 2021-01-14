package ch.hearc.ezworkout.networking.utils

import android.util.Log
import androidx.core.net.toUri
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import okio.Buffer
import java.io.IOException

class LogJsonInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val response: Response = chain.proceed(request)
        val rawJson: String = response.body()!!.string()
        val rawRequest = bodyToString(request)
        if(!response.isSuccessful){
            Log.d("json interceptor request", String.format("raw request is: %s: %s", response.request().method(), response.request().url().toString()))
            Log.d("json interceptor response", String.format("raw JSON response is: %s", rawJson))
        }

        // Re-create the response before returning it because body can be read only once
        return response.newBuilder()
            .body(ResponseBody.create(response.body()!!.contentType(), rawJson)).build()
    }

    private fun bodyToString(request: Request): String? {
        return try {
            val buffer = Buffer()
            request.body()?.writeTo(buffer)
            buffer.readUtf8()
        } catch (e: IOException) {
            "did not work"
        }
    }
}