package com.sun.android.table

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter


class PagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
       when(position){
           0 -> return Tab_Fragment_1()
           1 -> return Tab_Fragment_2()
           2 -> return Tab_Fragment_3()
           else -> return  Tab_Fragment_1()
       }
    }

}

