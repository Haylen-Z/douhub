package com.github.mrgrtt.douban.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.github.mrgrtt.douban.model.Movie
import com.github.mrgrtt.douban.network.DoubanNetwork

/**
 * Created by mrgrtt on 2019.8.9
 */
class VideoViewModel(val type: String, val tag: String): ListViewModel() {
    var page_start = 0
    val page_limit = 21
    var start = true

    fun loadVideo(done: (List<Movie>) -> Unit, fail: () -> Unit) {
        launch({
            val ms = DoubanNetwork.getVideos(type, tag, page_limit, page_start)
            page_start = page_start + ms.size
            done(ms)
        }, {
            fail()
        })
    }

    fun startLoading(adapter: BaseQuickAdapter<Movie, BaseViewHolder>) {
        if (!start) {
            return
        }

        this.showProgressBar.value = true
        loadVideo({
            showProgressBar.value = false
            adapter.addData(it)
            start = false
        }, {
            showErrorPage.value = true
            showProgressBar.value = false
        })
    }

    fun retry(adapter: BaseQuickAdapter<Movie, BaseViewHolder>) {
        showErrorPage.value = false
        startLoading(adapter)
    }

    fun loadMore(adapter: BaseQuickAdapter<Movie, BaseViewHolder>) {
        loadVideo({
            adapter.addData(it)
            adapter.loadMoreComplete()
        }, {
            adapter.loadMoreFail()
        })
    }

    class Factory(val type: String, val tag: String): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return VideoViewModel(type, tag) as T
        }

    }
}