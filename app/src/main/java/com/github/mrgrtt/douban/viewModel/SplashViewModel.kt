package com.github.mrgrtt.douban.viewModel

import androidx.lifecycle.ViewModel

/**
 * Created by mrgrtt on 2019.8.12
 */
class SplashViewModel: ViewModel() {
    fun delay(done: () -> Unit) {
        launch({
            kotlinx.coroutines.delay(1000)
            done()
        }, {})
    }
}