package com.github.mrgrtt.douban.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.github.mrgrtt.douban.R
import com.github.mrgrtt.douban.ui.adapter.HotPagerAdapter
import kotlinx.android.synthetic.main.tab_view_pager.*

/**
 * Create by mrgrtt on 2019.8.8
 */
class HotHomeFragment : BaseTabPaperFragment() {
    val pageLimit = 3

    companion object {
        fun newInstance():HotHomeFragment {
            return HotHomeFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.tab_view_pager, container, false)
    }

    override fun initViewPager() {
        val adapter = HotPagerAdapter(childFragmentManager)
        pager.adapter = adapter
        pager.offscreenPageLimit = pageLimit
        tab.setViewPager(pager)
    }
}
