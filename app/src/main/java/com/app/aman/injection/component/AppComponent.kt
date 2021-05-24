package com.app.aman.injection.component

import com.app.aman.injection.module.AppDatabaseModule
import com.app.aman.ui.MainActivity
import com.app.aman.injection.module.NetworkModule
import com.app.aman.repository.MainRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NetworkModule::class, AppDatabaseModule::class]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainRepository: MainRepository)
}