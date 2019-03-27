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
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import com.bumptech.glide.Glide
import com.csgames.mixparadise.extensions.lighter
import com.csgames.mixparadise.extensions.toPx
import com.csgames.mixparadise.model.Ingredient
import com.csgames.mixparadise.model.StackedIngredient
import com.google.android.flexbox.FlexboxLayout
import kotlin.random.Random

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

    fun addSolidIngredient(ingredient: Ingredient) {
        addIngredientToMap(ingredient.id)

        val imageView = createSolidIngredientImageView(ingredient)
        solidIngredientsContainer.addView(imageView)

        (imageView.layoutParams as FlexboxLayout.LayoutParams).apply {
            width = 40.toPx
            height = FrameLayout.LayoutParams.WRAP_CONTENT
        }
    }

    private fun createSolidIngredientImageView(ingredient: Ingredient) = ImageView(waveView.context).apply {
        adjustViewBounds = true
        scaleType = ImageView.ScaleType.FIT_CENTER

        val spriteUrl = if (ingredient.sprites?.isNotEmpty() == true) ingredient.sprites[Random.nextInt(
            0, ingredient.sprites.size
        )] else ingredient.imageUrl

        Glide.with(waveView.context)
            .load(spriteUrl)
            .into(this)
    }

    private fun addIngredientToMap(id: String) {
        ingredientsIdToOunces[id] = ingredientsIdToOunces[id]?.plus(OUNCES_TO_ADD) ?: OUNCES_TO_ADD
    }

    private fun getARGBColor(rgbColor: Int, alpha: Float): Int {
        return Color.argb(Math.round(alpha * 255), Color.red(rgbColor), Color.green(rgbColor), Color.blue(rgbColor))
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

        stackedIngredients.forEachIndexed { index, stackedIngredient ->
            val color = Color.parseColor(stackedIngredient.color)

            if (index == 0) {
                currentARGBColor = getARGBColor(color, stackedIngredient.opacity)
                waveView.alpha = stackedIngredient.opacity
            } else {
                val numberOfLiquids = index + 1
                currentARGBColor = ColorUtils.blendARGB(
                    getARGBColor(color, stackedIngredient.opacity),
                    currentARGBColor,
                    1f / numberOfLiquids
                )
                waveView.alpha = Color.alpha(currentARGBColor) / 255f
            }

            setWaveColor(currentARGBColor)
        }
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