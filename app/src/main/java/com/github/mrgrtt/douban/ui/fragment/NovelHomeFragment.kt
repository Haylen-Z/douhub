package com.github.mrgrtt.douban.ui.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mrgrtt.douban.R
import com.github.mrgrtt.douban.ui.adapter.NovelPagerAdapter
import kotlinx.android.synthetic.main.tab_view_pager.*

/**
 * Create by mrgrtt on 2019.8.9
 */
class NovelHomeFragment : Fragment() {
    val pageLimit = 4

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.tab_view_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pager.adapter = NovelPagerAdapter(childFragmentManager)
        pager.offscreenPageLimit = pageLimit
        tab.setViewPager(pager)
    }

}
