package br.com.rockseat.rockseatmvvm.service

import br.com.rockseat.rockseatmvvm.address.Address
import retrofit2.http.GET
import retrofit2.http.Path

interface BrasilApiService {
    @GET("api/cep/v1/{cep}")
    suspend fun getAddressByCep(@Path("cep") cep: String): Address
}