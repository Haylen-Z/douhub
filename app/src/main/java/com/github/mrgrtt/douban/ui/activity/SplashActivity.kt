package com.github.mrgrtt.douban.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.github.mrgrtt.douban.R
import com.github.mrgrtt.douban.viewModel.SplashViewModel

/**
 * Created by mrgrtt on 2019.8.12
 */
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val viewModel = ViewModelProviders.of(this).get(SplashViewModel::class.java)
        viewModel.delay {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


}
