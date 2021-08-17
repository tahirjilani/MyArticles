package com.tahir.nyt.app.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import com.tahir.nyt.R
import com.tahir.nyt.app.utils.AppIdlingResource
import com.tahir.nyt.databinding.ActivityArticleDetailBinding
import com.tahir.nyt.databinding.ActivityMainBinding

/**
 * Screen to show Article details
 */
private const val PARAM_URL = "url-param"

class ArticleDetailActivity : AppCompatActivity() {

    companion object {
        fun createIntent(context: Context, url: String): Intent {
            val intent = Intent(context, ArticleDetailActivity::class.java)
            intent.putExtra(PARAM_URL, url)
            return intent
        }
    }
    private lateinit var binding: ActivityArticleDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityArticleDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val url = intent.extras?.getString(PARAM_URL)
        binding.webView.apply {
            settings.loadWithOverviewMode = true
            webViewClient = CustomWebViewClient(binding.progressBar)
            url?.also {
                loadUrl(it)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private class CustomWebViewClient(val progressBar: ProgressBar): WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            progressBar.visibility = View.VISIBLE
            view?.apply {
                url?.also {
                    loadUrl(it)
                }
            }
            return true
        }
        override fun onPageFinished(view: WebView?, url: String?) {
            progressBar.visibility = View.GONE

            AppIdlingResource.decrement()
        }
    }
}