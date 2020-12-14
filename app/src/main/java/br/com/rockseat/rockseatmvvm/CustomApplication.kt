package br.com.rockseat.rockseatmvvm

import android.app.Application
import br.com.rockseat.rockseatmvvm.service.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CustomApplication: Application() {


    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CustomApplication)
            modules(networkModule)
        }
    }
}