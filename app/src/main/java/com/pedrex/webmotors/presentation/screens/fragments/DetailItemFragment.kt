package com.pedrex.webmotors.presentation.screens.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import com.pedrex.webmotors.databinding.FragmentDetailBinding
import com.pedrex.webmotors.presentation.model.ItemModel

class DetailItemFragment : DialogFragment() {

    companion object {
        const val ARG_CAR = "car"

        fun newInstance(car: ItemModel): DetailItemFragment {
            val fragment = DetailItemFragment()

            val bundle = Bundle().apply {
                putSerializable(ARG_CAR, car)
            }

            fragment.arguments = bundle
            return fragment
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDetailBinding.inflate(inflater, container, false)
        val car = arguments?.getSerializable(ARG_CAR)
        binding.apply {
            includeDetail.car = car as ItemModel
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        dialog?.window?.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
    }
}