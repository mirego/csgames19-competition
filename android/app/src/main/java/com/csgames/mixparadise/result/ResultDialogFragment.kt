package com.csgames.mixparadise.result

import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.csgames.mixparadise.R
import com.csgames.mixparadise.extensions.setImmersiveMode
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.view_result_dialog.view.*
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.doOnLayout
import nl.dionsegijn.konfetti.KonfettiView
import nl.dionsegijn.konfetti.models.Shape
import nl.dionsegijn.konfetti.models.Size


class ResultDialogFragment : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.view_result_dialog, container, false).also {
            setupDialogView(it)
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun setupDialogView(dialogView: View) {
        dialogView.close.setOnClickListener {
            dismiss()
        }

        dialogView.layoutParams =
            WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)

        dialogView.percentage.text =
            String.format(requireContext().getString(R.string.percentage), 50)
        dialogView.comment.text = "mmhhh"

        setProgress(dialogView.taste_progress_container, R.id.taste_progress, 50 / 100f)
        setProgress(dialogView.volume_progress_container, R.id.volume_progress, 50 / 100f)
        setProgress(dialogView.strength_progress_container, R.id.strength_progress, 50 / 100f)
    }

    private fun setProgress(constraintLayout: ConstraintLayout, @IdRes progressViewId: Int, percentage: Float) {
        ConstraintSet().apply {
            clone(constraintLayout)
            constrainPercentWidth(progressViewId, percentage)
            applyTo(constraintLayout)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?) = super.onCreateDialog(savedInstanceState).also {
        it.window?.apply {
            setImmersiveMode()
            setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                (context?.resources?.displayMetrics?.heightPixels?.times(0.85))?.toInt()
                    ?: ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
    }

    private fun showSuccessAnimation(dialogView: View) {
        dialogView.konfettiView.visibility = View.VISIBLE
        dialogView.konfettiView.doOnLayout {
            val konfettiView = it as KonfettiView
            konfettiView.build()
                .addColors(
                    requireContext().getColor(R.color.dark_peach),
                    requireContext().getColor(R.color.topaz),
                    requireContext().getColor(R.color.dull_yellow)
                )
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .addShapes(Shape.RECT, Shape.CIRCLE)
                .addSizes(Size(12), Size(16, 6f))
                .setPosition(-50f, konfettiView.width + 50f, -50f, -50f)
                .streamFor(300, 5000L)
        }
    }
}