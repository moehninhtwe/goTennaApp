package mhh.com.gotennaapp.api

import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIService {
    private val endPoint = HttpUrl.parse("https://annetog.gotenna.com")

    private fun provideAdapter(): Retrofit {
        val builder = OkHttpClient().newBuilder()

        val retrofitBuilder = Retrofit.Builder().client(builder.build())
                .baseUrl(endPoint!!)
                .addConverterFactory(GsonConverterFactory.create())
        return retrofitBuilder.build()
    }

    fun provideGetPlacesService(): GetPlacesService {
        return provideAdapter().create(GetPlacesService::class.java)
    }
}
