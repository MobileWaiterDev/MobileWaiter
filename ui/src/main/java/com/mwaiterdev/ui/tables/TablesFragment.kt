package com.mwaiterdev.ui.tables

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mwaiterdev.ui.R
import com.mwaiterdev.ui.databinding.FragmentTablesBinding

class TablesFragment : Fragment(R.layout.fragment_tables) {

    private val viewBinding: FragmentTablesBinding by viewBinding()

    companion object {
        fun newInstance() = TablesFragment()
    }
}