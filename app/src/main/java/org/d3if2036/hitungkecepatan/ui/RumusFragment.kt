package org.d3if2036.hitungkecepatan.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.d3if2036.hitungkecepatan.databinding.FragmentRumusBinding

class RumusFragment: Fragment() {

    private lateinit var binding: FragmentRumusBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRumusBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}