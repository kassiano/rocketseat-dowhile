package br.com.rockseat.rockseatmvvm.address

import androidx.lifecycle.*
import br.com.rockseat.rockseatmvvm.repository.AddressRepository
import br.com.rockseat.rockseatmvvm.service.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddressViewModel(
    private val addressRepository: AddressRepository
) : ViewModel() {

    private val _liveData = MutableLiveData<Resource<Address>>()
    private val cepLiveData = MutableLiveData<String>()
    val addressResult: LiveData<Resource<Address>> = _liveData

    fun getAddress(cep: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result: Resource<Address> = addressRepository.getAddress(cep)
            _liveData.postValue(result)
        }
    }

    fun getCep(cep: String) {
        cepLiveData.value = cep
    }

    var hehe = cepLiveData.switchMap { cep ->
        liveData {
            emit(Resource.loading(null))
            emit(addressRepository.getAddress(cep))
        }
    }
}