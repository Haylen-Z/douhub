package com.github.mrgrtt.douban.ui.adapter

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.github.mrgrtt.douban.R
import com.github.mrgrtt.douban.model.Movie

class MovieAdapter(layoutId: Int, movies: List<Movie>) :
    BaseQuickAdapter<Movie, BaseViewHolder>(layoutId, movies) {

    override fun convert(helper: BaseViewHolder, item: Movie?) {
        helper.setText(R.id.title, item?.title)
        helper.setText(R.id.rate, item?.rate.toString())
        Glide.with(mContext).load(item?.cover).into(helper.getView(R.id.cover) as ImageView)
    }
}