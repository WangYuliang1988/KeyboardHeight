package com.yigelangzi.keyboardheight

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue

/**
 * Created by wangyuliang on 2015/10/30.
 */
object DensityUtil {
    fun dp2px(context: Context, dp: Int): Int {
        return (dp * getScreenDensity(context) + 0.5).toInt()
    }

    fun px2dp(context: Context, px: Int): Int {
        return (px / getScreenDensity(context) + 0.5).toInt()
    }

    fun sp2px(c: Context?, sp: Int): Float {
        val r: Resources = if (c == null) {
            Resources.getSystem()
        } else {
            c.resources
        }
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            sp.toFloat(),
            r.displayMetrics
        )
    }

    private fun getScreenDensity(context: Context): Float {
        return context.resources.displayMetrics.density
    }
}