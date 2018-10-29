package com.raywenderlich.emotionalface

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class EmotionalFaceView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    // 1 Add two constants, one for the HAPPY state and one for the SAD state.
    companion object {
        private const val DEFAULT_FACE_COLOR = Color.YELLOW
        private const val DEFAULT_EYES_COLOR = Color.BLACK
        private const val DEFAULT_MOUTH_COLOR = Color.BLACK
        private const val DEFAULT_BORDER_COLOR = Color.BLACK
        private const val DEFAULT_BORDER_WIDTH = 4.0f

        const val HAPPY = 0L
        const val SAD = 1L
    }

    // 2 Setup default values of the XML attribute properties, in case a user of the
    // custom view does not set one of them
    private var faceColor = DEFAULT_FACE_COLOR
    private var eyesColor = DEFAULT_EYES_COLOR
    private var mouthColor = DEFAULT_MOUTH_COLOR
    private var borderColor = DEFAULT_BORDER_COLOR
    private var borderWidth = DEFAULT_BORDER_WIDTH

    private val paint = Paint()
    private val mouthPath = Path()
    private var size = 0

    // 3: Add a new property called happinessState for the face happiness state.
    var happinessState = HAPPY
        set(state) {
            field = state
            // 4: Call the invalidate() method in the set happinessState method.
            // The invalidate() method makes Android redraw the view by calling onDraw().
            invalidate()
        }


    override fun onDraw(canvas: Canvas) {
        // call the super method to keep any drawing from the parent side.
        super.onDraw(canvas)

        drawFaceBackground(canvas)
        drawEyes(canvas)
        drawMouth(canvas)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        // 1 Calculate the smaller dimension of your view
        size = Math.min(measuredWidth, measuredHeight)
        // 2 Use setMeasuredDimension(int, int) to store the measured width and measured
        // height of the view, in this case making your view width and height equivalent.
        setMeasuredDimension(size, size)
    }


    private fun drawFaceBackground(canvas: Canvas) {
        // 1 Set the paint color to the faceColor and make it fill the drawing area.
        paint.color = faceColor
        paint.style = Paint.Style.FILL

        // 2 Calculate a radius for a circle which you want to draw as the face background.
        val radius = size / 2f

        // 3 Draw the background circle with a center of (x,y), where x and y are equal to the half
        // of size, and with the calculated radius.
        canvas.drawCircle(size / 2f, size / 2f, radius, paint)

        // 4 Change the paint color to the borderColor and make it just draw a border around the
        // drawing area by setting the style to STROKE.
        paint.color = borderColor
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = borderWidth

        // 5 Draw a border with the same center but with a radius shorter than the previous
        // radius by the borderWidth.
        canvas.drawCircle(size / 2f, size / 2f, radius - borderWidth / 2f, paint)
    }

    private fun drawEyes(canvas: Canvas) {
        // 1 Set the paint color to the eyesColor and make it fill the drawing area.
        paint.color = eyesColor
        paint.style = Paint.Style.FILL

        // 2 Create a RectF object with left, top, right and bottom using the following
        // percentages of the size: (32%, 23%, 43%, 50%). Then you draw the left eye by
        // drawing an oval with the created RectF. For more info about RectF, check the
        // docs. [https://developer.android.com/reference/android/graphics/RectF.html]
        val leftEyeRect =
                RectF(size * 0.32f, size * 0.23f, size * 0.43f, size * 0.50f)

        canvas.drawOval(leftEyeRect, paint)

        // 3 Do the same as the last step but with the following percentages of the size:
        // (57%, 23%, 68%, 50%)
        val rightEyeRect =
                RectF(size * 0.57f, size * 0.23f, size * 0.68f, size * 0.50f)

        canvas.drawOval(rightEyeRect, paint)
    }

    private fun drawMouth(canvas: Canvas) {
        // 1 Set the starting point of the path to (x0,y0) by using the moveTo() method where:
        //
        //    x0 is equal to 22% of the size.
        //    y0 is equal to 70% of the size.
        mouthPath.moveTo(size * 0.22f, size * 0.7f)
        // 2 Draw a curved path from the starting point and through (x1,y1) that ends with (x2,y2) where:
        //
        //    x1 is equal to 50% of the size.
        //    y1 is equal to 80% of the size.
        //    x2 is equal to 78% of the size.
        //    y2 is equal to 70% of the size.
        mouthPath.quadTo(size * 0.50f, size * 0.80f, size * 0.78f, size * 0.70f)
        // 3 Draw a curved path starting from the last end point (x2,y2) and through (x3,y3) and that ends with (x0,y0) where:
        //
        //    x3 is equal to 50% of the size.
        //    y3 is equal to 90% of the size.
        //    x0 is equal to 22% of the size.
        //    y0 is equal to 70% of the size.
        mouthPath.quadTo(size * 0.50f, size * 0.90f, size * 0.22f, size * 0.70f)
        // 4 Set the paint color to the mouthColor and make it filling the drawing area.
        paint.color = mouthColor
        paint.style = Paint.Style.FILL
        // 5 Draw the path to the canvas.
        canvas.drawPath(mouthPath, paint)
    }



}