package com.tahir.nyt.app.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tahir.nyt.R
import com.tahir.nyt.app.ui.ArticleDetailActivity
import com.tahir.nyt.core.domain.Article
import com.tahir.nyt.databinding.ArticleItemLayoutBinding

class ArticleViewHolder constructor(private val binding: ArticleItemLayoutBinding)
    : RecyclerView.ViewHolder(binding.root) {

    private var article: Article ?= null
    init {
        itemView.setOnClickListener {
            article?.url?.let { url ->
                Log.d("ArticleViewHolder", "Open url: $url")
                with(itemView.context) {
                    val intent = ArticleDetailActivity.createIntent(this, url)
                    startActivity(intent)
                }
            }
        }
    }

    fun bind(article: Article?) {
        this.article = article

        with(binding) {
            article?.also {
                articleTitleTextView.text = it.title
                byTextView.text = it.byline
                dateTextView.text = it.publishedDate

                if (it.media.isNotEmpty() && it.media[0].mediaMetadata.isNotEmpty()) {
                    val url = it.media[0].mediaMetadata[0].url
                    Glide.with(this.root.context)
                        .load(url)
                        .into(this.ownerImageView)
                }

            }
        }
    }


    companion object {
        fun create(parent: ViewGroup): ArticleViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.article_item_layout, parent, false)
            val binding = ArticleItemLayoutBinding.bind(view)
            return ArticleViewHolder(binding)
        }
    }


}