package br.com.rockseat.rockseatmvvm.service

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { createRetrofit() }
    single { createService(get()) }
    factory { ResponseHandler() }
}


private fun createRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://brasilapi.com.br/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

private fun createService(retrofit: Retrofit): BrasilApiService {
    return retrofit.create(BrasilApiService::class.java)
}