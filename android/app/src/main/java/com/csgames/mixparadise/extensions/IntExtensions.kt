package com.csgames.mixparadise.extensions

import android.content.res.Resources
import android.graphics.Color

val Int.toPx: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

fun Int.lighter(ratio: Float): Int {
    val hsv = FloatArray(3)
    Color.colorToHSV(this, hsv)
    hsv[2] = 1.0f - ratio * (1.0f - hsv[2])
    return Color.HSVToColor(hsv)
}