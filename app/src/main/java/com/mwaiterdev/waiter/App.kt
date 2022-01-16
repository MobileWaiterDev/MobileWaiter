package com.mwaiterdev.waiter

import android.app.Application
import com.mwaiterdev.waiter.di.Di
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(
                listOf(
                    Di.interactorModule(),
                    Di.repositoryModule(),
                    Di.viewModelModule(),
                    Di.waiterApiModule(),
                    Di.useCasesModule()
                    Di.sharedPrefModule()
                )
            )
        }
    }
}