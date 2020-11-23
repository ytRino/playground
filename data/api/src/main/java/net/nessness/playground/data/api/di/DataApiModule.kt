package net.nessness.playground.data.api.di

import android.os.StrictMode
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import net.nessness.playground.data.api.TimelineService
import net.nessness.playground.data.api.dummy.DummyResponse
import okhttp3.HttpUrl
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.*
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@ExperimentalSerializationApi
@Module
@InstallIn(SingletonComponent::class)
object DataApiModule {

    @Provides
    @Singleton
    fun provideTimelineService(retrofit: Retrofit): TimelineService {
        return retrofit.create()
    }

    @Provides
    fun provideRetrofit(): Retrofit {
        val mockWebServer = mockWebServer()

        val contentType: MediaType = "application/json".toMediaType()

        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().permitAll().build())

        val url = mockWebServer.url("/")

        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().detectAll().build())

        return Retrofit.Builder()
            // TODO: switchable
            .baseUrl(url)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(
                        HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).apply {
                            level = HttpLoggingInterceptor.Level.HEADERS
                        }
                    )
                    .build()
            )
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
    }

    // region Mock response

    private fun mockWebServer(): MockWebServer {
        val mockWebServer = MockWebServer()
        val dispatcher = object : QueueDispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return when (request.path) {
                    "/cats.json" -> response(DummyResponse.cats)
                    else -> response(DummyResponse.items(request.path!!.drop(1)))
                }
            }
        }
        mockWebServer.dispatcher = dispatcher
        Thread { mockWebServer.start() }
        return mockWebServer
    }

    private fun response(response: String): MockResponse {
        println(response)
        return MockResponse().apply {
            setResponseCode(200)
            setBody(response)
        }
    }

    // endregion
}
