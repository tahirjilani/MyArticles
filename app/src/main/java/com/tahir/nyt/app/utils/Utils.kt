package com.tahir.nyt.app.utils

import android.content.res.Resources
import kotlin.math.roundToInt

object Utils {
    fun dpToPx(dp: Int): Int {
        val metrics = Resources.getSystem().displayMetrics
        val px = dp * (metrics.densityDpi / 160f)
        return px.roundToInt()
    }
}