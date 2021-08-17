package com.tahir.nyt

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.pressBack
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.tahir.nyt.app.ui.ArticleDetailActivity
import com.tahir.nyt.app.ui.MainActivity
import com.tahir.nyt.app.ui.adapter.ArticleViewHolder
import com.tahir.nyt.app.utils.AppIdlingResource
import com.tahir.nyt.core.data.network.FakeArticlesListWebApi
import com.tahir.nyt.core.data.network.WebApi
import com.tahir.nyt.core.di.NetworkModule
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Rule
import javax.inject.Singleton

@Suppress("IllegalIdentifier")

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class OnlineHappyPathTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private lateinit var idlingResource: IdlingResource

    @Before
    fun setUp() {
        idlingResource = AppIdlingResource.getInstance()
        IdlingRegistry.getInstance().register(idlingResource)

        Intents.init()
    }

    @After
    fun tearDown() {
        idlingResource.let {
            IdlingRegistry.getInstance().unregister(it)
        }
        AppIdlingResource.dispose()

        Intents.release()
    }

    @Test
    fun loadArticles_clickOne_showsDetailScreen() {
        //launch MainActivity
        ActivityScenario.launch(MainActivity::class.java)

        //wait to load data from FakeArticlesListWebApi
        AppIdlingResource.increment()

        //click on first item
        onView(withId(R.id.recycler_view))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<ArticleViewHolder>(0, ViewActions.click())
            )
        //check if ArticleDetailActivity opens up
        Intents.intended(IntentMatchers.hasComponent(ArticleDetailActivity::class.java.name))

        //wait until content loads in ArticleDetailActivity
        AppIdlingResource.increment()

        //press back
        onView(isRoot()).perform(pressBack())

        //stop waiting.
        AppIdlingResource.decrement()

        //test passed
        assert(true)
    }
}