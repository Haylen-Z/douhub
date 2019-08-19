package com.github.mrgrtt.douban.ui.activity

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.github.mrgrtt.douban.R
import com.github.mrgrtt.douban.ui.fragment.NovelHomeFragment
import com.github.mrgrtt.douban.ui.fragment.VideoHomeFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by mrgrtt on 2019.8.19
 */
class MainActivity : AppCompatActivity() {
    private val movieFragment = VideoHomeFragment.newInstance("movie")
    private val tvFragment = VideoHomeFragment.newInstance("tv")
    private val novelHomeFragment = NovelHomeFragment()

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        item.isChecked = true
        when (item.itemId) {
            R.id.movie -> {
                replaceHomePager(movieFragment)
            }
            R.id.tv -> {
                replaceHomePager(tvFragment)
            }
            R.id.novel -> {
                replaceHomePager(novelHomeFragment)
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nav_view.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        replaceHomePager(VideoHomeFragment.newInstance("movie"))
    }

    private fun replaceHomePager(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
