package com.github.mrgrtt.douban.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.mrgrtt.douban.network.DoubanNetwork

/**
 * Create by mrgrtt on 2019.8.8
 */
class VideoHomeViewModel(val type: String): BaseTabPagerViewModel() {
    var showErrorPage = MutableLiveData(false)

    override fun loadTitles() {
        launch({
            showErrorPage.value = false
            super.titles.value =
                DoubanNetwork.getTags(type).filter {
                    !(it.equals("热门") or it.equals("可播放"))
                }
        }, {
            showErrorPage.value = true
        })
    }

    class Factry(val type: String): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return VideoHomeViewModel(type) as T
        }
    }
}