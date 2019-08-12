package com.github.mrgrtt.douban.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.mrgrtt.douban.model.Novel
import com.github.mrgrtt.douban.network.DoubanNetwork
import com.github.mrgrtt.douban.ui.adapter.NovelAdapter

/**
 * Created by mrgrtt on 2019.8.10
 */
class NovelViewModel(val sort: String): ListViewModel() {
    var page = 1
    var start = true

    fun loadNovels(done: (List<Novel>) -> Unit, fail: () -> Unit) {
        launch({
            val ms = DoubanNetwork.getNovels(sort, page)
            ++page
            done(ms)
        }, {fail()})
    }

    fun startLoading(adapter: NovelAdapter) {
        if (!start) {
            return
        }
        showProgressBar.value = true
        showErrorPage.value = false
        loadNovels({
            adapter.addData(it)
            showProgressBar.value =false
        }, {
            showProgressBar.value = false
            showErrorPage.value = true
        })
    }

    class Factory(val sort: String): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return NovelViewModel(sort) as T
        }
    }
}
