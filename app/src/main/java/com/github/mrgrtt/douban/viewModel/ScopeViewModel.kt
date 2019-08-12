package com.github.mrgrtt.douban.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * Created by mrgrtt on 2019.8.8
 */

fun ViewModel.launch(block: suspend () -> Unit,
                         error: suspend (Throwable) -> Unit) = viewModelScope.launch {
    try {
        block()
    } catch (e: Throwable) {
        error(e)
    }
}