package com.gluehome.common.presentation.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.drawable.Animatable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator
import com.gluehome.common.presentation.extensions.toDP
import com.google.android.material.button.MaterialButton

class ProgressButton : MaterialButton {

    private var animatedDrawable: CircularAnimationDrawable? = null
    private var currentText: CharSequence = ""
    private var state: State = State.IDLE

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        when (state) {
            State.LOADING -> drawIndeterminateProgress(canvas)
            State.IDLE -> stopIndeterminateProgress()
        }
    }

    fun startLoading() {
        isClickable = false
        if (state != State.IDLE) {
            return
        }
        state = State.LOADING
        currentText = text
        text = ""
    }

    fun stopLoading() {
        if (state != State.LOADING) {
            return
        }
        state = State.IDLE
        text = currentText
        isClickable = true
    }

    private fun drawIndeterminateProgress(canvas: Canvas) {
        if (animatedDrawable == null || !animatedDrawable!!.isRunning) {
            animatedDrawable = CircularAnimationDrawable(this, 10f, Color.WHITE)

            val padding = 15f.toDP(context)

            val offset = (width - height) / 2
            val left = offset + padding
            val right = width - offset - padding
            val bottom = height - padding
            val top = 0 + padding

            animatedDrawable!!.setBounds(left, top, right, bottom)
            animatedDrawable!!.callback = this
            animatedDrawable!!.start()
        } else {
            animatedDrawable!!.draw(canvas)
        }
    }

    private fun stopIndeterminateProgress() {
        if (animatedDrawable != null && animatedDrawable?.isRunning!!) {
            animatedDrawable?.stop()
        }
    }

    enum class State(val stateId: Int) {
        IDLE(0), LOADING(1)
    }
}

class CircularAnimationDrawable : Drawable, Animatable {

    private var valueAnimatorAngle: ValueAnimator? = null
    private var valueAnimatorSweep: ValueAnimator? = null
    private var valueAnimatorAlpha: ValueAnimator? = null
    private var angleInterpolator: Interpolator = LinearInterpolator()
    private var sweepInterpolator: Interpolator = DecelerateInterpolator()

    private var fBounds: RectF = RectF()
    private var paint: Paint? = null
    private var animatedView: View? = null

    private var borderWidth: Float = 0f
    private var currentGlobalAngle: Float = 0f
    private var currentSweepAngle: Float = 0f
    private var currentGlobalAngleOffset: Float = 0f

    private var modeAppearing = false
    private var running = false

    companion object {
        const val ANGLE_ANIMATOR_DURATION = 2000L
        const val SWEEP_ANIMATOR_DURATION = 900L
        const val ALPHA_ANIMATOR_DURATION = 250L
        const val MIN_SWEEP_ANGLE = 30f
    }

    constructor(view: View, width: Float, arcColor: Int) : super() {
        animatedView = view
        borderWidth = width
        paint = Paint().apply {
            isAntiAlias = true
            style = Paint.Style.STROKE
            strokeWidth = borderWidth
            color = arcColor
        }
        setupAnimations()
    }

    override fun draw(canvas: Canvas) {
        var startAngle = currentGlobalAngle - currentGlobalAngleOffset
        var sweepAngle = currentSweepAngle
        when (modeAppearing) {
            true -> {
                sweepAngle += MIN_SWEEP_ANGLE
            }
            false -> {
                startAngle += sweepAngle
                sweepAngle = 360f - sweepAngle - MIN_SWEEP_ANGLE
            }
        }
        canvas.drawArc(fBounds, startAngle, sweepAngle, false, paint)
    }

    override fun setAlpha(alpha: Int) {
        paint?.alpha = alpha
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSPARENT
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        paint?.colorFilter = colorFilter
    }

    override fun isRunning(): Boolean {
        return running
    }

    override fun start() {
        if (running) {
            return
        }
        running = true
        valueAnimatorAngle?.start()
        valueAnimatorSweep?.start()
        valueAnimatorAlpha?.start()
    }

    override fun stop() {
        if (!running) {
            return
        }
        running = false
        valueAnimatorAngle?.cancel()
        valueAnimatorSweep?.cancel()
        valueAnimatorAlpha?.cancel()
    }

    override fun onBoundsChange(bounds: Rect) {
        super.onBoundsChange(bounds)
        fBounds.left = bounds.left + borderWidth / 2f + .5f
        fBounds.right = bounds.right - borderWidth / 2f - .5f
        fBounds.top = bounds.top + borderWidth / 2f + .5f
        fBounds.bottom = bounds.bottom - borderWidth / 2f - .5f
    }

    private fun setupAnimations() {
        valueAnimatorAlpha = ValueAnimator.ofInt(0, 255).apply {
            interpolator = angleInterpolator
            duration = ALPHA_ANIMATOR_DURATION
        }
        valueAnimatorAlpha?.addUpdateListener {
            setAlpha(it.animatedValue as Int)
            animatedView?.invalidate()
        }

        valueAnimatorAngle = ValueAnimator.ofFloat(0f, 360f).apply {
            interpolator = angleInterpolator
            duration = ANGLE_ANIMATOR_DURATION
            repeatCount = ValueAnimator.INFINITE
        }
        valueAnimatorAngle?.addUpdateListener {
            currentGlobalAngle = it.animatedValue as Float
            animatedView?.invalidate()
        }
        valueAnimatorSweep = ValueAnimator.ofFloat(0F, 360f - 2 * MIN_SWEEP_ANGLE).apply {
            interpolator = sweepInterpolator
            duration = SWEEP_ANIMATOR_DURATION
            repeatCount = ValueAnimator.INFINITE
        }
        valueAnimatorSweep?.addUpdateListener {
            currentSweepAngle = it.animatedValue as Float
            invalidateSelf()
        }
        valueAnimatorSweep?.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationRepeat(animation: Animator) {
                toggleAppearingMode()
            }
        })
    }

    private fun toggleAppearingMode() {
        modeAppearing = !modeAppearing
    }
}