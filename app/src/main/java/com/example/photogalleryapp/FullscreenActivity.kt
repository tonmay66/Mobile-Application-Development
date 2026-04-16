package com.example.photogalleryapp

import android.graphics.Matrix
import android.graphics.PointF
import android.os.Bundle
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.photogalleryapp.databinding.ActivityFullscreenBinding

class FullscreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFullscreenBinding
    private var matrix = Matrix()
    private var savedMatrix = Matrix()
    private var mode = NONE

    private val start = PointF()
    private val mid = PointF()
    private var oldDist = 1f

    companion object {
        const val EXTRA_IMAGE_RES = "extra_image_res"
        const val NONE = 0
        const val DRAG = 1
        const val ZOOM = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageRes = intent.getIntExtra(EXTRA_IMAGE_RES, 0)
        binding.ivFullscreen.setImageResource(imageRes)

        binding.btnBack.setOnClickListener {
            finish()
        }

        setupZoom()
    }

    private fun setupZoom() {
        val view = binding.ivFullscreen
        view.setOnTouchListener { v, event ->
            val imageView = v as ImageView
            when (event.action and MotionEvent.ACTION_MASK) {
                MotionEvent.ACTION_DOWN -> {
                    savedMatrix.set(matrix)
                    start.set(event.x, event.y)
                    mode = DRAG
                }
                MotionEvent.ACTION_POINTER_DOWN -> {
                    oldDist = spacing(event)
                    if (oldDist > 10f) {
                        savedMatrix.set(matrix)
                        midPoint(mid, event)
                        mode = ZOOM
                    }
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP -> {
                    mode = NONE
                }
                MotionEvent.ACTION_MOVE -> {
                    if (mode == DRAG) {
                        matrix.set(savedMatrix)
                        matrix.postTranslate(event.x - start.x, event.y - start.y)
                    } else if (mode == ZOOM) {
                        val newDist = spacing(event)
                        if (newDist > 10f) {
                            matrix.set(savedMatrix)
                            val scale = newDist / oldDist
                            matrix.postScale(scale, scale, mid.x, mid.y)
                        }
                    }
                }
            }
            imageView.imageMatrix = matrix
            true
        }
    }

    private fun spacing(event: MotionEvent): Float {
        val x = event.getX(0) - event.getX(1)
        val y = event.getY(0) - event.getY(1)
        return Math.sqrt((x * x + y * y).toDouble()).toFloat()
    }

    private fun midPoint(point: PointF, event: MotionEvent) {
        val x = event.getX(0) + event.getX(1)
        val y = event.getY(0) + event.getY(1)
        point.set(x / 2, y / 2)
    }
}