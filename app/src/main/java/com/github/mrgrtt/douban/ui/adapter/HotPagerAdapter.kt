package com.github.mrgrtt.douban.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.github.mrgrtt.douban.DoubanApplication
import com.github.mrgrtt.douban.R
import com.github.mrgrtt.douban.ui.fragment.NovelFragment
import com.github.mrgrtt.douban.ui.fragment.VideoFragment

/**
 * Crated by mrgrtt on 2019.8.7
 */
class HotPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private val fragments by lazy {
        arrayOf(VideoFragment("movie", "热门"),
            VideoFragment("tv", "热门"), NovelFragment.newInstance("hot"))
    }

    private val titles by lazy {
        arrayOf(R.string.menu_movie, R.string.menu_tv, R.string.menu_novel)
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return DoubanApplication.context.getString(titles[position])
    }
}