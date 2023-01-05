package com.sun.android.contentProvider

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sun.android.databinding.ListItemContactBinding

class ContentRecyclerAdapter(private val contact: MutableList<Contact>) :
    RecyclerView.Adapter<ContentRecyclerAdapter.ViewHolder>() {
    private var mlistener: Recycler0nClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, mlistener)
    }

    fun setOnClickListener(listener: Recycler0nClickListener?){
        mlistener = listener
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = contact[position].name
        holder.sdt.text = contact[position].number

    }

    override fun getItemCount(): Int {
        return contact.size
    }

    inner class ViewHolder(binding: ListItemContactBinding, private val listener: Recycler0nClickListener?) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        val name = binding.textName
        val sdt = binding.textSdt

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            listener?.onItemClick(layoutPosition)
        }

    }


}

