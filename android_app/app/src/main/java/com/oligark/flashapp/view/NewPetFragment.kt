package com.oligark.flashapp.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.oligark.flashapp.R

class NewPetFragment : Fragment() {
    companion object {
        var TAG = NewPetFragment::class.java.simpleName
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var rootView = inflater.inflate(R.layout.fragment_new_pet, container, false)

        return rootView
    }
}
