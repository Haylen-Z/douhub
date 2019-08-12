package com.github.mrgrtt.douban.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.github.mrgrtt.douban.ui.fragment.VideoFragment

/**
 * Created by mrgrtt on 2019.8.10
 */
class VideoPagerAdapter(fm: FragmentManager,
                        val titles: List<String>, val type: String):
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return VideoFragment(type, titles[position])
    }

    override fun getCount(): Int {
        return titles.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }
}