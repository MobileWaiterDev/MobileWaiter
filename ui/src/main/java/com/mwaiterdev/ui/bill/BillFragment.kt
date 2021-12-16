package com.mwaiterdev.ui.bill

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mwaiterdev.ui.R
import com.mwaiterdev.ui.databinding.FragmentBillBinding

class BillFragment : Fragment(R.layout.fragment_bill) {

    private val viewBinding: FragmentBillBinding by viewBinding()

    companion object {
        fun newInstance() = BillFragment()
    }
}