package com.github.mrgrtt.douban.ui.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter

import com.github.mrgrtt.douban.R
import com.github.mrgrtt.douban.databinding.ListFragmentBinding
import com.github.mrgrtt.douban.model.Novel
import com.github.mrgrtt.douban.network.BASE_READ_URL
import com.github.mrgrtt.douban.ui.adapter.NovelAdapter
import com.github.mrgrtt.douban.viewModel.NovelViewModel
import com.thefinestartist.finestwebview.FinestWebView
import kotlinx.android.synthetic.main.list_fragment.*
import kotlinx.android.synthetic.main.loading_error.*

/**
 * Created by mrgrtt on 2019.8.10
 */
class NovelFragment(val sort: String) : Fragment() {

    companion object {
        fun newInstance(sort: String) = NovelFragment(sort)
    }

    private lateinit var viewModel: NovelViewModel
    private lateinit var bind: ListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this, NovelViewModel.Factory(sort)).get(NovelViewModel::class.java)
        bind = DataBindingUtil.inflate(inflater, R.layout.list_fragment, container, false)
        bind.lifecycleOwner = this
        bind.viewModel = viewModel
        return bind.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val adapter = NovelAdapter(R.layout.item_novel, ArrayList<Novel>())
        recycler_view.adapter= adapter
        recycler_view.layoutManager = LinearLayoutManager(activity)

        adapter.setOnLoadMoreListener(BaseQuickAdapter.RequestLoadMoreListener {
            viewModel.loadNovels({
                adapter.addData(it)
                adapter.loadMoreComplete()
            }, {adapter.loadMoreFail()})
        }, recycler_view)

        adapter.setOnItemClickListener {
                adapter, view, position ->
            val item = (adapter as NovelAdapter).data[position]
            FinestWebView.Builder(activity!!)
                .stringResRefresh(R.string.menu_refresh)
                .stringResShareVia(R.string.menu_share)
                .stringResCopyLink(R.string.menu_copy_link)
                .stringResOpenWith(R.string.menu_open_with)
                .stringResCopiedToClipboard(R.string.copy_done)
                .show(BASE_READ_URL + "ebook/" + item.id + "/")
        }
        retry_button.setOnClickListener { viewModel.startLoading(adapter) }
        viewModel.startLoading(adapter)
    }

}
