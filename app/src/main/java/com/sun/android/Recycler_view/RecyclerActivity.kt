package com.sun.android.Recycler_view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.sun.android.databinding.ActivityRecyclerBinding


class RecyclerActivity : AppCompatActivity() {

    private var adapter: RecyclerView.Adapter<WordListAdapter.WordViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        val binding by lazy { ActivityRecyclerBinding.inflate(layoutInflater) }
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val mWordList: MutableList<String> = mutableListOf()
        for (i in 0..19) {
            mWordList.add("Word $i")
        }

        adapter = WordListAdapter(mWordList)
        binding.recyclerview.adapter = adapter

        binding.fab.setOnClickListener {
            mWordList.add("Word ${mWordList.size}")
            binding.recyclerview.adapter?.notifyItemInserted(mWordList.size)
            binding.recyclerview.smoothScrollToPosition(mWordList.size)
        }
    }

}
