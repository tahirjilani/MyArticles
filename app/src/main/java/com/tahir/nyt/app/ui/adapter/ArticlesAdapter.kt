package com.tahir.nyt.app.ui.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tahir.nyt.core.domain.Article
import javax.inject.Inject

class ArticlesAdapter @Inject constructor() : RecyclerView.Adapter<ArticleViewHolder>() {

    private var articles: List<Article>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
       return ArticleViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        articles?.apply {
            holder.bind(this[position])
        }
    }

    override fun getItemCount(): Int {
        return articles?.size ?: 0
    }

    @SuppressLint("NotifyDataSetChanged")
    fun showArticles(list: List<Article>) {
        this.articles = list
        notifyDataSetChanged()
    }
}