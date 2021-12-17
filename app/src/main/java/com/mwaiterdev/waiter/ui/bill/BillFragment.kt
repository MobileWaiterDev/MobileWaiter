package com.mwaiterdev.waiter.ui.bill

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mwaiterdev.waiter.R
import com.mwaiterdev.waiter.databinding.FragmentBillBinding

class BillFragment : Fragment(R.layout.fragment_bill) {

    private val viewBinding: FragmentBillBinding by viewBinding()

    companion object {
        fun newInstance() = BillFragment()
    }
}