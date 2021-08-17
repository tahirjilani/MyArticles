package com.tahir.nyt

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.pressBack
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.tahir.nyt.app.ui.ArticleDetailActivity
import com.tahir.nyt.app.ui.MainActivity
import com.tahir.nyt.app.ui.adapter.ArticleViewHolder
import com.tahir.nyt.app.utils.AppIdlingResource
import com.tahir.nyt.core.data.network.FakeArticlesListWebApi
import com.tahir.nyt.core.data.network.FakeErrorWebApi
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

/**
 * Remove NetworkModule from DI and install FakeNetworkModule that exposes FakeErrorWebApi which
 * tries mock error message from server
 *
 */
@RunWith(AndroidJUnit4::class)
@UninstallModules(NetworkModule::class)
@HiltAndroidTest
class UnHappyPathTest {

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class FakeNetworkModule {
        @Binds
        @Singleton
        abstract fun bindFaleArticlesListWebApi(fakeErrorApi: FakeErrorWebApi): WebApi
    }

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
    fun tryLoadingArticles_errorHappens_showsErrorMsg() {

        AppIdlingResource.increment()

        //launch MainActivity
        ActivityScenario.launch(MainActivity::class.java)

        //check if error msg displays
        onView(withId(R.id.error_text_view)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        //test passed
        assert(true)
    }
}