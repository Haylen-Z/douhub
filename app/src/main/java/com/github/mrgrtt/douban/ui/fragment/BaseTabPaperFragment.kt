package com.github.mrgrtt.douban.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mrgrtt.douban.R
import com.github.mrgrtt.douban.viewModel.BaseTabPagerViewModel

/**
 * Create by mrgrtt on 2019.8.8
 */
abstract class BaseTabPaperFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.tab_view_pager, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewPager()
    }

    abstract fun initViewPager()
}
