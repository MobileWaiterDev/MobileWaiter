package com.mwaiterdev.waiter.ui.tables

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mwaiterdev.waiter.R
import com.mwaiterdev.waiter.databinding.FragmentTablesBinding

class TablesFragment : Fragment(R.layout.fragment_tables) {

    private val viewBinding: FragmentTablesBinding by viewBinding()

    companion object {
        fun newInstance() = TablesFragment()
    }
}