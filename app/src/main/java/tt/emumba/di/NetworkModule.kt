package tt.emumba.di

import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tt.emumba.BuildConfig
import tt.emumba.data.remote.api.ApiClient
import java.util.concurrent.TimeUnit

fun provideHttpClient(): OkHttpClient {
    return OkHttpClient
        .Builder()
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .build()
}


fun provideConverterFactory(): GsonConverterFactory =
    GsonConverterFactory.create()


fun provideRetrofit(
    okHttpClient: OkHttpClient,
    gsonConverterFactory: GsonConverterFactory
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(gsonConverterFactory)
        .build()
}

fun provideService(retrofit: Retrofit): ApiClient =
    retrofit.create(ApiClient::class.java)


val networkModule= module {
    single { provideHttpClient() }
    single { provideConverterFactory() }
    single { provideRetrofit(get(),get()) }
    single { provideService(get()) }
}