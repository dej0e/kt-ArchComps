package wards.jungle.archcomps

import android.app.Application
import android.content.Context

class ArchApp: Application() {
    companion object {
        lateinit var appContext: Context
    }
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }
}