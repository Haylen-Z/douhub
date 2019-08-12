package com.github.mrgrtt.douban

import android.app.Application
import android.content.Context

class DoubanApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        DoubanApplication.context = this
    }

    companion object{
        lateinit var context: Context
    }
}