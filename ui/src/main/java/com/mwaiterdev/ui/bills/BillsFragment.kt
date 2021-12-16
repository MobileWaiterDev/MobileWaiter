package com.mwaiterdev.ui.bills

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mwaiterdev.ui.R
import com.mwaiterdev.ui.databinding.FragmentBillsBinding

class BillsFragment : Fragment(R.layout.fragment_bills) {

    private val viewBinding: FragmentBillsBinding by viewBinding()

    companion object {
        fun newInstance() = BillsFragment()
    }
}