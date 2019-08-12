package com.github.mrgrtt.douban.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.github.mrgrtt.douban.R
import com.github.mrgrtt.douban.ui.adapter.VideoPagerAdapter
import com.github.mrgrtt.douban.databinding.VideoHomeFragmentBinding
import com.github.mrgrtt.douban.viewModel.VideoHomeViewModel
import kotlinx.android.synthetic.main.loading_error.*
import kotlinx.android.synthetic.main.tab_view_pager.*

/**
 * Created by mrgrtt on 2019.8.9
 */
class VideoHomeFragment(val type: String): BaseTabPaperFragment() {
    companion object {
        fun newInstance(type: String): VideoHomeFragment{
            return VideoHomeFragment(type)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.video_home_fragment, container, false)
    }
    override fun initViewPager() {
        val viewModel = ViewModelProviders.
            of(this, VideoHomeViewModel.Factry(type))
            .get(VideoHomeViewModel::class.java)

        pager.offscreenPageLimit = 3

        viewModel.titles.observe(this, Observer {
                titles ->
            pager.adapter = VideoPagerAdapter(childFragmentManager, titles, type)
            tab.setViewPager(pager)
        })

        val bind = DataBindingUtil.bind<VideoHomeFragmentBinding>(view!!)
        bind?.viewModel = viewModel
        bind?.lifecycleOwner = this
        retry_button.setOnClickListener { viewModel.loadTitles() }
        viewModel.loadTitles()
    }
}