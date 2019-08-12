package com.github.mrgrtt.douban.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseListViewModel: ViewModel() {
    val showErrorPage = MutableLiveData(false)
    val showProgressBar = MutableLiveData(false)
}