package com.tahir.nyt.app.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.tahir.nyt.BuildConfig
import com.tahir.nyt.core.domain.Resource
import com.tahir.nyt.core.domain.Article
import com.tahir.nyt.core.interactor.FetchPopularArticles
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * View Model that fetches articles and provide UI with live data to observe
 * data loading states.
 */
@HiltViewModel
class ArticlesViewModel @Inject constructor(private val fetchPopularArticles: FetchPopularArticles)
    : ViewModel(){

    fun getPopularArticles() = liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
        emit(Resource.Loading())

        //lets hard code these values here. in future we may select from user preferences from UI
        val section = "all-sections"
        val period = "7"
        emit(fetchPopularArticles.invoke(section, period))
    }
}