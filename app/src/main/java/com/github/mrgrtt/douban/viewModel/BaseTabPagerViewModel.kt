package com.github.mrgrtt.douban.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;

abstract class BaseTabPagerViewModel : ViewModel() {
    val titles = MutableLiveData<List<String>>(ArrayList())
    abstract fun loadTitles()

}
