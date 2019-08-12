package com.github.mrgrtt.douban.ui.adapter

import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.github.mrgrtt.douban.R
import com.github.mrgrtt.douban.model.Novel
import java.lang.StringBuilder

/**
 * Created by mrgrtt on 2019.8.10
 */
class NovelAdapter(layoutId: Int, novels: List<Novel>):
    BaseQuickAdapter<Novel, BaseViewHolder>(layoutId, novels) {

    override fun convert(helper: BaseViewHolder, item: Novel?) {
        helper.setText(R.id.title, item!!.title)
        helper.setText(R.id.rating, item.averageRating.plus(5.0).toString())
        helper.setText(R.id.price, "豆瓣售价：￥" + item.salesPrice.div(100))
        Glide.with(mContext).load(item.cover).into(helper.getView<AppCompatImageView>(R.id.cover))

        val builder = StringBuilder()
        var first = true
        if (item.author.isNotEmpty()) {
            builder.append(item.author[0].name)
            first = false
        }
        if (item.origAuthor.isNotEmpty()) {
            if (!first) {
                builder.append(" 著，")
            }
            builder.append(item.origAuthor[0].name)
            first = false
        }
        if (item.translator.isNotEmpty()) {
            if (!first) {
                builder.append(" 著，")
            }
            builder.append(item.translator[0].name)
            builder.append(" 译")
            first = false
        }
        helper.setText(R.id.author, builder.toString())
        helper.setText(R.id.rating_count, item.ratingCount.toString() + "人评价")
    }
}