package br.com.rockseat.rockseatmvvm.repository

import org.koin.dsl.module

val addressRepositoryModule = module {
    factory { AddressRepository(get(), get()) }
}