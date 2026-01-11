package com.owenbean.magic8watch

import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import java.util.*
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.animation.doOnEnd

class MainActivity : ComponentActivity() {
    private lateinit var answers: Array<String>
    private lateinit var rotates: Array<Float>

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        answers = arrayOf(
                "Yes\nDefinitely",
                "No",
                "Maybe",
                "Without\nA\nDoubt",
                "Ask\nAgain\nLater",
                "Outlook\nNot So\nGood",
                "Very\nDoubtful",
                "Most\nLikely")

        rotates = arrayOf(
                10f, 10f, -10f, -10f,
                -15f, 15f,
                -20f, 20f,
                30f, -30f,
                0f, 0f, 0f,
        )


        val watchInterface: RelativeLayout = findViewById(R.id.watchInterface)
        watchInterface.setOnClickListener {
            opacityOff()
        }
    }

    private fun opacityOff() {
        val textView: TextView =
            findViewById<TextView>(R.id.answer)
        val imageView: ImageView =
            findViewById<ImageView>(R.id.magicTriangle)
        val a: Animation =
            AnimationUtils.loadAnimation(this, R.anim.animateon)
        textView.postDelayed(Runnable {
            changeText(textView)
            opacityOn()
        }, 200)
        textView.startAnimation(a)
        imageView.startAnimation(a)
    }

    private fun changeText(textView: TextView) {
        textView.text = answers[Random().nextInt(answers.size)]
    }

    private fun opacityOn() {
        val textView: TextView =
            findViewById<TextView>(R.id.answer)
        val imageView: ImageView =
            findViewById<ImageView>(R.id.magicTriangle)
        val randomRotate: Int = Random().nextInt(rotates.size)
        imageView.rotation = rotates[randomRotate]
        textView.rotation = rotates[randomRotate]
        val a: Animation =
            AnimationUtils.loadAnimation(this, R.anim.animationoff)
        textView.startAnimation(a)
        imageView.startAnimation(a)
    }
}
