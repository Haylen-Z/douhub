package com.github.mrgrtt.douban.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.github.mrgrtt.douban.ui.fragment.NovelFragment

/**
 * Created by mrgrtt on 2019.8.10
 */
class NovelPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
    val sorts by lazy { arrayOf("new", "rating", "sales") }
    val titles by lazy { arrayOf("最新", "评分", "销量") }

    override fun getItem(position: Int): Fragment {
        return NovelFragment(sorts[position])
    }

    override fun getCount(): Int {
        return titles.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }
}