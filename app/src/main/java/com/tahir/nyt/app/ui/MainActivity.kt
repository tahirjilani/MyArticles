package com.tahir.nyt.app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.tahir.nyt.app.ui.adapter.ArticlesAdapter
import com.tahir.nyt.app.utils.SpacesItemDecoration
import com.tahir.nyt.app.utils.Utils
import com.tahir.nyt.app.ui.vm.ArticlesViewModel
import com.tahir.nyt.app.utils.AppIdlingResource
import com.tahir.nyt.core.domain.Article
import com.tahir.nyt.core.domain.Resource
import com.tahir.nyt.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Main screen to show list of articles
 */

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject lateinit var articlesAdapter: ArticlesAdapter

    private lateinit var binding: ActivityMainBinding
    private val viewModel: ArticlesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.recyclerView.apply {
            adapter = articlesAdapter

            setHasFixedSize(true)
            addItemDecoration(SpacesItemDecoration(Utils.dpToPx(14)))
        }

        //fetch articles
        viewModel.getPopularArticles().observe(this) { resource ->
            updateUI(resource)
        }
    }

    /**
     * Update UI on the basis of loading states
     */
    private fun updateUI(resource: Resource<List<Article>>) {
        when(resource) {
            is Resource.Error -> {
                binding.progressBar.visibility = View.GONE
                binding.errorTextView.text = resource.exception.message
                binding.errorTextView.visibility = View.VISIBLE

                //for ui testing
                AppIdlingResource.decrement()
            }
            is Resource.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            is Resource.Success -> {
                binding.errorTextView.visibility = View.GONE
                binding.progressBar.visibility = View.GONE

                articlesAdapter.showArticles(resource.data)

                //for ui testing
                AppIdlingResource.decrement()
            }
        }
    }
}