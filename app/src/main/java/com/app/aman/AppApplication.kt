package com.app.aman

import android.app.Application
import com.app.aman.injection.component.AppComponent
import com.app.aman.injection.component.DaggerAppComponent
import com.app.aman.injection.module.AppDatabaseModule

class AppApplication : Application() {
    companion object {
        private var appComponent: AppComponent? = null
        private var instance: AppApplication? = null

        fun getAppComponent(): AppComponent {
            return appComponent!!
        }

        /**
         * Using this instance, we can access any method from this SmallCaseApp class.
         */
        fun getInstance(): AppApplication {
            return instance ?: AppApplication()
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder().appDatabaseModule(AppDatabaseModule(this)).build()
    }
}