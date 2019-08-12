package com.github.mrgrtt.douban.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.github.mrgrtt.douban.DoubanApplication
import com.github.mrgrtt.douban.R
import com.github.mrgrtt.douban.ui.adapter.MovieAdapter
import com.github.mrgrtt.douban.databinding.ListFragmentBinding
import com.github.mrgrtt.douban.model.Movie
import com.github.mrgrtt.douban.network.BASE_MOVIE_DETAIL_URL
import com.github.mrgrtt.douban.network.BASE_MOVIE_URL
import com.github.mrgrtt.douban.viewModel.VideoViewModel
import com.thefinestartist.finestwebview.FinestWebView
import com.thefinestartist.finestwebview.listeners.WebViewListener
import kotlinx.android.synthetic.main.list_fragment.*
import kotlinx.android.synthetic.main.loading_error.*

/**
 * Created by mrgrtt on 2019.8.9
 */
class VideoFragment(val type: String, val vtag: String): Fragment() {
    protected lateinit var viewModel: VideoViewModel
    protected lateinit var binding: ListFragmentBinding
    protected val gridCount = 3
    protected val adapter by lazy {
        MovieAdapter(R.layout.item_movie, ArrayList<Movie>())
    }

    protected open fun initView(inflater: LayoutInflater, container: ViewGroup?) {
        this.binding = DataBindingUtil.
            inflate(inflater, R.layout.list_fragment, container, false)

        viewModel = ViewModelProviders
            .of(this, VideoViewModel.Factory(type, vtag))
            .get(VideoViewModel::class.java)

        binding.viewModel = this.viewModel
        binding.lifecycleOwner = this
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initView(inflater, container)
        return this.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retry_button.setOnClickListener { viewModel.retry(adapter) }
        initRecycler()
        viewModel.startLoading(adapter)
    }

    private fun initRecycler() {
        recycler_view.adapter = adapter
        recycler_view.setHasFixedSize(true)
        recycler_view.layoutManager = GridLayoutManager(activity, gridCount);

        adapter.setOnLoadMoreListener({
            viewModel.loadMore(adapter)
        }, recycler_view)

        adapter.setOnItemClickListener {
                adapter, view, position ->
            val item = (adapter as MovieAdapter).data[position]
            FinestWebView.Builder(activity!!)
                .stringResRefresh(R.string.menu_refresh)
                .stringResShareVia(R.string.menu_share)
                .stringResCopyLink(R.string.menu_copy_link)
                .stringResOpenWith(R.string.menu_open_with)
                .stringResCopiedToClipboard(R.string.copy_done)
                .show(BASE_MOVIE_DETAIL_URL + item.id + "/")
        }
    }
}