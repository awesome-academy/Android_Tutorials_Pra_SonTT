package com.sun.android
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TableLayout
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.Tab
import com.google.android.material.tabs.TabLayoutMediator
import com.sun.android.databinding.ActivitySecondBinding
import com.sun.android.table.PagerAdapter


class SecondActivity : AppCompatActivity() {
    val binding by lazy{ ActivitySecondBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val dataRevice = intent.getStringExtra(EXTRA_REV)
        binding.textViewRevice.text = dataRevice.toString()
        val pager = binding.pager
        val tl : TabLayout = binding.tabLayout
        pager.adapter = PagerAdapter(supportFragmentManager,lifecycle)

        val tabTitle = listOf(R.string.story.toString() ,R.string.tech.toString(),R.string.cook.toString())

        TabLayoutMediator(tl, pager) { tab, position ->
            tab.text = tabTitle[position]
        }.attach()
    }
    fun returnReply(view: View?) {
        val reply: String = binding.editTextSecond.text.toString()
        val replyIntent = Intent(this, MainActivity::class.java)
        replyIntent.putExtra(EXTRA_REPLY, reply)
        setResult(RESULT_OK, replyIntent)
        finish()
    }
}

