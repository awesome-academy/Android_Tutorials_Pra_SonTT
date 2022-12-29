package com.sun.android.fragment


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.sun.android.R
import com.sun.android.databinding.ActivityStartFragmentBinding


class StartFragmentActivity : AppCompatActivity() {
    private var isFragmentDisplayed: Boolean = false

    val binding by lazy { ActivityStartFragmentBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnStartFrag.setOnClickListener(){
            if (isFragmentDisplayed == false){
                displayFragment()
            }else{
                closeFragment()
            }
        }
    }
    fun displayFragment() {
        val simpleFragment: SimpleFragment? = SimpleFragment().newInstance()
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager
            .beginTransaction()
        if (simpleFragment != null) {
            fragmentTransaction.add(R.id.fragment_container,simpleFragment).addToBackStack(null).commit()
        }
        binding.btnStartFrag.setText(R.string.close)
        isFragmentDisplayed = true
    }
    fun closeFragment() {
        val fragmentManager = supportFragmentManager
        val simpleFragment = fragmentManager
            .findFragmentById(R.id.fragment_container) as? SimpleFragment?
        if (simpleFragment != null) {
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.remove(simpleFragment).commit()
        }
        binding.btnStartFrag.setText(R.string.open)
        isFragmentDisplayed = false
    }
}
