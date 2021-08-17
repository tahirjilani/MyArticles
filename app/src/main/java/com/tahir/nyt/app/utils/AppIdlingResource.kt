package com.tahir.nyt.app.utils

import androidx.test.espresso.idling.CountingIdlingResource
import org.jetbrains.annotations.TestOnly
import java.lang.Exception

/**
 * When adding idling resources into your app, Android highly recommend placing the idling resource
 * logic in the app itself and performing only the registration and unregistration operations in
 * your tests.
 */
class AppIdlingResource {
    companion object {
        private var countingIdlingResource: CountingIdlingResource? = null

        /**
         * Only call this from your tests
         */
        @TestOnly
        fun getInstance(): CountingIdlingResource {
            if (countingIdlingResource == null) {
                countingIdlingResource = CountingIdlingResource("app_idle_resource")
            }
            return countingIdlingResource!!
        }

        @TestOnly
        fun dispose() {
            countingIdlingResource = null
        }

        fun increment() {
            countingIdlingResource?.increment()
        }

        fun decrement() {
            try {
                countingIdlingResource?.decrement()
            }catch (ex:Exception){}
        }
    }
}