package com.example.tohtobin_pro_41

import android.app.Fragment
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var btn_photo: Button
    private lateinit var btn_video: Button
    private lateinit var currentFragment: Fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        setupListener()
    }
    private fun initComponents() {
        btn_photo = findViewById(R.id.btn_photo)
        btn_video = findViewById(R.id.btn_video)
    }
    private fun loadFragment(fragment: Fragment, tag: String) {
        currentFragment = fragment
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
            R.anim.slide_in_right,
            R.anim.slide_out_left,
            R.anim.slide_in_left,
            R.anim.slide_out_right
        )
            .replace(R.id.fragment_container, fragment, tag)
            .commit()
    }
    private fun updateButtonStates(isActive: Boolean) {
        btn_photo.isEnabled = isActive
    }
    private fun setupListener() {
        btn_photo.setOnClickListener {
            if (currentFragment !is PhotoFragment) {
                loadFragment(PhotoFragment, "photos")
                updateButtonStates(false)
            }
        }
        btn_video.setOnClickListener {
            if (currentFragment !is VideoFragment) {
                loadFragment(VideoFragment, "videos")
                updateButtonStates(false)
            }
        }
    }
}