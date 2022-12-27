package com.sun.android.Drawables_style

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import com.sun.android.R
import com.sun.android.databinding.ActivityScorekeeperBinding

class ScorekeeperActivity : AppCompatActivity() {
    val binding by lazy { ActivityScorekeeperBinding.inflate(layoutInflater) }

    var mScore1: Int = 0
    var mScore2: Int = 0

    private var STATE_SCORE_1: String = "Team 1 Score"
    private var STATE_SCORE_2: String = "Team 2 Score"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (savedInstanceState != null) {
            mScore1 = savedInstanceState.getInt(STATE_SCORE_1)
            mScore2 = savedInstanceState.getInt(STATE_SCORE_2)
            binding.score1.text = mScore1.toString()
            binding.score2.text = mScore2.toString()
        }
    }

    fun decreaseScore(view: View) {
        val viewID: Int = view.id
        when (viewID) {
            binding.decreaseTeam1.id -> {
                mScore1--
                binding.score1.text = mScore1.toString()
            }
            binding.decreaseTeam2.id -> {
                mScore2--
                binding.score2.text = mScore2.toString()

            }
        }
    }

    fun increaseScore(view: View) {
        val viewID: Int = view.id
        when (viewID) {
            binding.increaseTeam1.id -> {
                mScore1++
                binding.score1.text = mScore1.toString()
            }
            binding.increaseTeam2.id -> {
                mScore2++
                binding.score2.text = mScore2.toString()

            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val nightMode: Int = AppCompatDelegate.getDefaultNightMode()
        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            menu?.let { it.findItem(R.id.night_mode).setTitle(R.string.day_mode) }
        } else {
            menu?.let { it.findItem(R.id.night_mode).setTitle(R.string.night_mode) }
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.night_mode) {
            val nightMode: Int = AppCompatDelegate.getDefaultNightMode()
            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            recreate()
        }
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(STATE_SCORE_1, mScore1)
        outState.putInt(STATE_SCORE_2, mScore2)
    }
}


