package com.sun.android.fragment



import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.sun.android.R
import com.sun.android.databinding.FragmentSimpleBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val yes = 1
private const val no = 0
const val radio_button_yes = "radio_button_yes"
const val radio_button_no = "radio_button_no"

class SimpleFragment : Fragment() {
    val binding by lazy { FragmentSimpleBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            binding.radioGroup.setOnCheckedChangeListener {
                group, checkedId ->
            val textView:TextView = binding.textHeader
            val index = group.indexOfChild(group.findViewById(checkedId))
            when (index) {
                yes -> textView.setText(R.string.yes_message)
                no -> textView.setText(R.string.no_message)
                else -> {}
            }
        }
            binding.ratingBar.setOnRatingBarChangeListener { ratingBar, fl, b ->
                val rateing = ratingBar.rating.toString()
                val myRate = getString(R.string.my_rating) + "$rateing"
                Toast.makeText(context,myRate, Toast.LENGTH_LONG).show()
            }
        return binding.root
    }
    fun newInstance(): SimpleFragment? {
        return SimpleFragment()
    }
}


