package com.github.mrgrtt.douban.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.github.mrgrtt.douban.R
import com.github.mrgrtt.douban.ui.fragment.HotHomeFragment
import com.github.mrgrtt.douban.ui.fragment.NovelHomeFragment
import com.github.mrgrtt.douban.ui.fragment.VideoHomeFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

/**
 * Created by mrgrtt on 2019.8.7
 */
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        initDrawer()
        replaceHomePager(HotHomeFragment.newInstance(),
            getString(R.string.menu_hot))
    }

    private fun initDrawer() {
        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.hot -> {
                replaceHomePager(HotHomeFragment.newInstance(),
                    getString(R.string.menu_hot))
            }
            R.id.movie -> {
                replaceHomePager(VideoHomeFragment.newInstance("movie"),
                    getString(R.string.menu_movie))
            }
            R.id.tv -> {
                replaceHomePager(VideoHomeFragment.newInstance("tv"),
                    getString(R.string.menu_tv))
            }
            R.id.novel -> {
                replaceHomePager(NovelHomeFragment(), getString(R.string.menu_novel))
            }
            R.id.about -> {
                val webpage = Uri.parse("https://github.com/Mrgrtt/douhub")
                val intent = Intent(Intent.ACTION_VIEW, webpage)
                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                }
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun isSameHomePager(t: String): Boolean{
        return title.equals(t)
    }

    private fun replaceHomePager(fragment: Fragment, t: String) {
        if (!title.equals(t)) {
            title = t
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
        }
    }
}
