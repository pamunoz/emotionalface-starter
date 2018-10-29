package com.raywenderlich.emotionalface

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class EmotionalFaceView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    // Paint object for coloring and styling
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    // Some colors for the face background, eyes and mouth.
    private var faceColor = Color.YELLOW
    private var eyesColor = Color.BLACK
    private var mouthColor = Color.BLACK
    private var borderColor = Color.BLACK
    // Face border width in pixels
    private var borderWidth = 4.0f
    // View size in pixels
    private var size = 320

    override fun onDraw(canvas: Canvas) {
        // call the super method to keep any drawing from the parent side.
        super.onDraw(canvas)

        drawFaceBackground(canvas)
        drawEyes(canvas)
        drawMouth(canvas)
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
    }

    private fun drawMouth(canvas: Canvas) {
    }



}