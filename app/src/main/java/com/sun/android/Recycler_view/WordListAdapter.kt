package com.sun.android.Recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sun.android.databinding.WordlistItemBinding


class WordListAdapter(wordList: MutableList<String>) : RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {
    var mWordList = wordList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val binding = WordlistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WordViewHolder(binding, this)
    }

    override fun getItemCount(): Int {
        return mWordList.size
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val mCurrent = mWordList[position]
        holder.wordItemView.text = mCurrent
    }

    inner class WordViewHolder(binding: WordlistItemBinding, adapter: WordListAdapter) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        var wordItemView = binding.textWord
        private var mAdapter: WordListAdapter = adapter

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position: Int = layoutPosition
            val element: String = mWordList[position]
            if (element.startsWith("Clicked")) {
                mWordList[position] = element.replace("Clicked! ", "")
            } else {
                mWordList[position] = "Clicked! $element"
            }
            mAdapter.notifyDataSetChanged()

        }
    }

}

