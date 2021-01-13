package br.com.rockseat.rockseatmvvm.repository

import br.com.rockseat.rockseatmvvm.address.Address
import br.com.rockseat.rockseatmvvm.service.BrasilApiService
import br.com.rockseat.rockseatmvvm.service.Resource
import br.com.rockseat.rockseatmvvm.service.ResponseHandler


open class AddressRepository(
    private val service: BrasilApiService,
    private val responseHandler: ResponseHandler
) {
    suspend fun getAddress(cep: String): Resource<Address> {
        return try {
            val response = service.getAddressByCep(cep)
            return responseHandler.handleSuccess(response)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }
}