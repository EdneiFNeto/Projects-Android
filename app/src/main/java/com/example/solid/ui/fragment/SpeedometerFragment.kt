package com.example.solid.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.solid.databinding.FragmentVelocimetroBinding
import com.example.solid.model.Speedometer

class SpeedometerFragment : BasaFragment() {
    private lateinit var velocimetroBinding: FragmentVelocimetroBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        velocimetroBinding = FragmentVelocimetroBinding.inflate(inflater, container, false)
        return velocimetroBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        velocimetroBinding.speedometer = Speedometer(km = 200.0)
    }
}