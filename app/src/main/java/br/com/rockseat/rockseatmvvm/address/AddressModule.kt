package br.com.rockseat.rockseatmvvm.address

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val addressModule = module {
    viewModel { AddressViewModel(get()) }
}