package com.sun.android.sharedPreferences

import android.content.SharedPreferences
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.sun.android.R
import com.sun.android.databinding.ActivitySharedPreferencesBinding


class SharedPreferencesActivity : AppCompatActivity(), OnClickListener {
    private val binding by lazy { ActivitySharedPreferencesBinding.inflate(layoutInflater) }

    private var mCount = 0
    private var mColor = 0

    private val mPreferences: SharedPreferences by lazy {
        getSharedPreferences(sharedPrefFile, MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        mColor = ContextCompat.getColor(this, R.color.default_background)

        mCount = mPreferences.getInt(COUNT_KEY, 0)
        binding.textViewCount.text = String.format("%s", mCount)
        mColor = mPreferences.getInt(COLOR_KEY, mColor)
        binding.textViewCount.setBackgroundColor(mColor)

        binding.buttonCount.setOnClickListener(this)
        binding.buttonBlackBackground.setOnClickListener(this)
        binding.buttonRedBackground.setOnClickListener(this)
        binding.buttonBlueBackground.setOnClickListener(this)
        binding.buttonGreenBackground.setOnClickListener(this)
        binding.buttonReset.setOnClickListener(this)

    }

    private fun changeBackground(view: View) {
        view.backgroundTintList?.let {
            binding.textViewCount.setBackgroundColor(
                it.getColorForState(intArrayOf(android.R.attr.state_enabled), 0)
            )
        }
        mColor = ((binding.textViewCount.background) as? ColorDrawable)?.color ?: 0
    }

    private fun countUp() {
        mCount++
        binding.textViewCount.text = String.format("%s", mCount)
    }

    override fun onClick(view: View) {
        when (view) {
            binding.buttonCount -> countUp()
            binding.buttonRedBackground, binding.buttonBlackBackground, binding.buttonRedBackground, binding.buttonBlueBackground, binding.buttonGreenBackground -> changeBackground(
                view
            )
            binding.buttonReset -> reset()
        }
    }

    private fun reset() {
        mCount = 0
        binding.textViewCount.text = String.format("%s", mCount)
        mColor = ContextCompat.getColor(
            this,
            R.color.default_background
        )
        binding.textViewCount.setBackgroundColor(mColor)
    }

    override fun onPause() {
        super.onPause()
        val preferencesEditor = mPreferences.edit()
        with(preferencesEditor) {
            putInt(COUNT_KEY, mCount)
            putInt(COLOR_KEY, mColor)
            apply()
        }
    }

    companion object {
        private const val COUNT_KEY = "count"
        private const val COLOR_KEY = "color"
        private const val sharedPrefFile = "com.example.android.shareholdings"
    }
}


