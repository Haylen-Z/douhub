package com.github.mrgrtt.douban.model

/**
 * Created by mrgrtt on 2019.8.10
 */
class Novel {
    lateinit var title: String
    lateinit var cover: String
    lateinit var url: String
    var ratingCount = 0
    lateinit var author: List<Author>
    lateinit var origAuthor: List<Author>
    lateinit var translator: List<Author>
    lateinit var abstract: String
    var averageRating = 0.0
    var salesPrice = 0.0
    var id = 0L
}