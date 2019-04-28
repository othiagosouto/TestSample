package br.com.tsouto.testsample

import android.app.Application
import br.com.tsouto.testsample.data.PersonDataSource
import br.com.tsouto.testsample.data.PersonRepository
import br.com.tsouto.testsample.data.remote.RemoteDataSource
import org.koin.dsl.module.module
import org.koin.android.ext.android.startKoin

class App : Application() {
    private val modules = listOf(module(override = true) {
        single { RemoteDataSource() as PersonDataSource }
        single { PersonRepository(get()) }
    })

    override fun onCreate() {
        super.onCreate()
        startKoin(this, modules)
    }
}