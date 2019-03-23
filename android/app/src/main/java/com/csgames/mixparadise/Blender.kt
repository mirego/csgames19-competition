package com.csgames.mixparadise

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import com.gelitenight.waveview.library.WaveView
import android.view.animation.LinearInterpolator
import android.animation.ValueAnimator
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.csgames.mixparadise.extensions.lighter
import com.csgames.mixparadise.model.StackedIngredient
import com.google.android.flexbox.FlexboxLayout

class Blender(
    private val waveView: WaveView,
    private val stackView: ViewGroup,
    private val solidIngredientsContainer: FlexboxLayout,
    private val capacityInOunces: Int,
    private val onReadyToServe: () -> Unit,
    private val onServeComplete: (ingredientsIdToOunces: HashMap<String, Int>) -> Unit
) {

    companion object {
        private const val MINIMUM_AMPLITUDE_RATIO = 0.0001f
        private const val MAXIMUM_AMPLITUDE_RATIO = 0.05f
        private const val MINIMUM_WAVE_SHIFT_RATIO = 0f
        private const val MAXIMUM_WAVE_SHIFT_RATIO = 1f
        private const val OUNCES_TO_ADD = 1
    }

    val ingredientsIdToOunces = hashMapOf<String, Int>()

    private val stackedIngredients = mutableListOf<StackedIngredient>()
    private var currentARGBColor = 0
    private var mixed = false
    private var mixAnimator: ValueAnimator? = null

    init {
        waveView.setShapeType(WaveView.ShapeType.SQUARE)
        waveView.isShowWave = true
        waveView.waterLevelRatio = 0f
        waveView.amplitudeRatio = MINIMUM_AMPLITUDE_RATIO
        setWaveColor(ContextCompat.getColor(waveView.context, android.R.color.black))
    }

    fun addLiquid(id: String, color: String, opacity: Float) {
        if (mixed) {
            return
        }

        val height = stackView.measuredHeight * (1f / capacityInOunces)
        View(waveView.context).apply {
            setBackgroundColor(Color.parseColor(color))
            alpha = opacity
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height.toInt())
            stackView.addView(this, 0)
        }

        addIngredientToMap(id)
        stackedIngredients.add(StackedIngredient(color, opacity))
    }

    fun addSolidIngredient() {
        addIngredientToMap("ingredientIdReplaceMe")

        // TODO: add the ingredient visually
    }

    private fun createSolidIngredientImageView() = ImageView(waveView.context).apply {
        adjustViewBounds = true
        scaleType = ImageView.ScaleType.FIT_CENTER
        Glide.with(waveView.context)
            .load("image url replace me")
            .into(this)
    }


    private fun addIngredientToMap(id: String) {
        ingredientsIdToOunces[id] = ingredientsIdToOunces[id]?.plus(OUNCES_TO_ADD) ?: OUNCES_TO_ADD
    }

    fun start() {
        if (stackedIngredients.isEmpty()) {
            return
        }

        waveView.amplitudeRatio = MAXIMUM_AMPLITUDE_RATIO
        mixAnimator = shiftWaves(0f, 1f, ValueAnimator.INFINITE)

        if (mixed) {
            return
        }

        mix()
        mixed = true
        onReadyToServe()
    }

    private fun mix() {
        stackView.visibility = View.GONE
        solidIngredientsContainer.visibility = View.GONE
        waveView.waterLevelRatio = stackedIngredients.size / capacityInOunces.toFloat()

        // TODO: mix the liquid ingredients color
        waveView.alpha = 1f
        setWaveColor(Color.parseColor("#A66C1E"))
    }

    fun stop() {
        mixAnimator?.cancel()
        animateAmplitude(waveView.amplitudeRatio, MINIMUM_AMPLITUDE_RATIO, 0)
        shiftWaves(waveView.waveShiftRatio, 1f, 0)
    }

    fun empty() {
        animateLevel(waveView.waterLevelRatio, 0f).addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                val ingredientsIdToOuncesBackup = HashMap(ingredientsIdToOunces)
                reset()
                onServeComplete(ingredientsIdToOuncesBackup)
            }
        })
    }

    private fun reset() {
        mixed = false
        stackedIngredients.clear()
        ingredientsIdToOunces.clear()
        currentARGBColor = 0
        stackView.removeAllViews()
        stackView.visibility = View.VISIBLE
        solidIngredientsContainer.removeAllViews()
        solidIngredientsContainer.visibility = View.VISIBLE
    }

    private fun shiftWaves(
        from: Float = MINIMUM_WAVE_SHIFT_RATIO,
        to: Float = MAXIMUM_WAVE_SHIFT_RATIO,
        repeatCount: Int = ValueAnimator.INFINITE
    ) = ObjectAnimator.ofFloat(
        waveView, "waveShiftRatio", from, to
    ).apply {
        this.repeatCount = repeatCount
        duration = 1000
        interpolator = LinearInterpolator()
        start()
    }

    private fun animateAmplitude(
        from: Float = MINIMUM_AMPLITUDE_RATIO,
        to: Float = MAXIMUM_AMPLITUDE_RATIO,
        repeatCount: Int = ValueAnimator.INFINITE
    ) = ObjectAnimator.ofFloat(
        waveView, "amplitudeRatio", from, to
    ).apply {
        this.repeatCount = repeatCount
        repeatMode = ValueAnimator.REVERSE
        duration = 1000
        interpolator = LinearInterpolator()
        start()
    }

    private fun animateLevel(from: Float, to: Float) = ObjectAnimator.ofFloat(
        waveView, "waterLevelRatio", from, to
    ).apply {
        duration = 1000
        interpolator = DecelerateInterpolator()
        start()
    }

    private fun setWaveColor(color: Int) {
        waveView.setWaveColor(
            color.lighter(0.75f),
            color
        )
    }
}