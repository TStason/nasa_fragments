package com.example.nasa_api.utils

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.View
import java.lang.ref.WeakReference

class CustomPB: View {
    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet): super(context, attrs)

    private var onePart = 0
    private val TAG = "CustomPB"

    private val animSet = WeakReference(AnimatorSet())
/*    private val animListener = object : Animator.AnimatorListener{
        override fun onAnimationRepeat(animation: Animator?) {
            Log.d(TAG, "onAnimationRepeat")
        }
        override fun onAnimationCancel(animation: Animator?) {
            Log.d(TAG, "onAnimationCancel")
        }
        override fun onAnimationStart(animation: Animator?) {
            Log.d(TAG, "onAnimationStart")
        }
        override fun onAnimationEnd(animation: Animator?) {
            Log.d(TAG, "onAnimationEnd")
            setRect()
            invalidate()
            if (isAnimated)
                animation?.start()
        }
    }
*/
    private val r1 = Rect()
    private val r2 = Rect()
    private val r3 = Rect()
    private val r4 = Rect()
    private val r5 = Rect()
    private val r6 = Rect()
    private val r7 = Rect()

    private val p1 = Paint().apply {
        color = Color.parseColor("#000000")
        style = Paint.Style.FILL
    }
    private val p2 = Paint().apply {
        color = Color.parseColor("#FFFFFF")
        style = Paint.Style.STROKE
        strokeWidth = 1.0f
    }

    var isAnimated = false

    init{
        visibility = INVISIBLE
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        Log.d(TAG, "onMeasure")
        val side = (100 * resources.displayMetrics.density).toInt()
        setMeasuredDimension(side, side)
    }
    private fun setOnePartSide(){
        onePart = (this.measuredHeight.toFloat() / 4).toInt()
        //Log.d(TAG, "new Edge -> $onePart")
    }
    private fun setAnimation(){
        val animator1 = ValueAnimator.ofInt(4 * onePart, 2 * onePart).apply {
            duration = 1000L
            addUpdateListener {
                r3.bottom = it.animatedValue as Int
                r3.top = r3.bottom + onePart
                invalidate()
//                Log.d(TAG, "1 New Value set ${it.animatedValue}")
            }
            //start()
        }
        val animator2 = ValueAnimator.ofInt(0, 4 * onePart).apply {
            duration = 1500L
            addUpdateListener {
                r4.left = 4 * onePart - it.animatedValue as Int
                r4.right = r4.left + 3 * onePart

                r5.bottom = it.animatedValue as Int
                r5.top = r5.bottom - 3 * onePart
                invalidate()
//                Log.d(TAG, "2 New Value set ${it.animatedValue}")
            }
            //start()
        }
        val animator3 = ValueAnimator.ofInt(4 * onePart, onePart).apply {
            duration = 1500L
            addUpdateListener {
                r6.left = it.animatedValue as Int
                r6.right = r6.left + 3 * onePart
                invalidate()
//                Log.d(TAG, "3 New Value set ${it.animatedValue}")
            }
            //start()
        }
        val animator4 = ValueAnimator.ofInt(0, onePart).apply {
            duration = 1500L
            addUpdateListener {
                r7.right = it.animatedValue as Int
                r7.left = r7.right - onePart
                invalidate()
//                Log.d(TAG, "4 New Value set ${it.animatedValue}")
            }
        }
        animSet.get()?.apply {
            playSequentially(
                animator1, animator2, animator3, animator4
            )
        }
    }
    private fun setRect(){
        r1.left = onePart
        r1.top = onePart
        r1.right = 3 * onePart
        r1.bottom = 2 * onePart
        r2.left = onePart
        r2.top = 2 * onePart
        r2.right = 2 * onePart
        r2.bottom = 3 * onePart
        // first animation
        r3.left = 2 * onePart
        r3.top = 4 * onePart
        r3.right = 3 * onePart
        r3.bottom = 5 * onePart
        // second animation
        r4.left = 4 * onePart
        r4.top = 3 * onePart
        r4.right = 7 * onePart
        r4.bottom = 4 * onePart

        r5.left = 3 * onePart
        r5.top = -3 * onePart
        r5.right = 4 * onePart
        r5.bottom = 0
        //third animation
        r6.left = 4 * onePart
        r6.top = 0
        r6.right = 7 * onePart
        r6.bottom = onePart

        r7.left = -onePart
        r7.top = 0
        r7.right = 0
        r7.bottom = 3 * onePart
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.let{
            it.drawRect(r1, p1)
            it.drawRect(r2, p1)
            it.drawRect(r3, p1)
            it.drawRect(r4, p1)
            it.drawRect(r5, p1)
            it.drawRect(r6, p1)
            it.drawRect(r7, p1)

            it.drawRect(r1, p2)
            it.drawRect(r2, p2)
            it.drawRect(r3, p2)
            it.drawRect(r4, p2)
            it.drawRect(r5, p2)
            it.drawRect(r6, p2)
            it.drawRect(r7, p2)
        }
    }

    fun startAnim(){
        isAnimated = true
        visibility = VISIBLE
        setOnePartSide()
        setRect()
        setAnimation()
        animSet.get()?.addListener(object : Animator.AnimatorListener{
            override fun onAnimationRepeat(animation: Animator?) {
                Log.d(TAG, "onAnimationRepeat")
            }
            override fun onAnimationCancel(animation: Animator?) {
                Log.d(TAG, "onAnimationCancel")
            }
            override fun onAnimationStart(animation: Animator?) {
                Log.d(TAG, "onAnimationStart")
            }
            override fun onAnimationEnd(animation: Animator?) {
                Log.d(TAG, "onAnimationEnd")
                setRect()
                invalidate()
                if (isAnimated)
                    animation?.start()
            }
        })
        animSet.get()?.start()
    }

    fun stopAnim(){
        isAnimated = false
        animSet.get()?.end()
        animSet.get()?.removeAllListeners()
        animSet.get()?.childAnimations?.clear()
        visibility = INVISIBLE
    }

    override fun onDetachedFromWindow() {
        Log.d(TAG, "onDetachedFromWindow")
        if (isAnimated)
            stopAnim()
        super.onDetachedFromWindow()
    }
}