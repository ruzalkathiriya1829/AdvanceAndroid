package com.example.whatsapp

import android.os.Parcel
import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class TabLayoutAdapter(var supportFragmentManager: FragmentManager) : FragmentPagerAdapter(supportFragmentManager) {
    override fun getCount(): Int {

        return 3

    }

    override fun getItem(position: Int): Fragment {

        if(position == 0)
        {
            return ChatFragment()
        }
        else if(position == 1)
        {
            return StatuesFragment()
        }
        else if(position == 2)
        {
            return CallsFragment()
        }
        else
        {
            return getItem(position)
        }
    }


}