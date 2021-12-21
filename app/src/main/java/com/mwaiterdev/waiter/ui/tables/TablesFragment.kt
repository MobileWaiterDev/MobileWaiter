package com.mwaiterdev.waiter.ui.tables

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mwaiterdev.domain.ScreenTablesState
import com.mwaiterdev.waiter.R
import com.mwaiterdev.waiter.databinding.FragmentTablesBinding

class TablesFragment : Fragment(R.layout.fragment_tables) {

    private val viewBinding: FragmentTablesBinding by viewBinding()
    private val viewModel by lazy {
        ViewModelProvider(this).get(TablesViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getTableGroupsLiveData()
            .observe(viewLifecycleOwner, { fillSpinnerTableGroups(it) })
        viewModel.getTableGroups()
    }

    private fun fillSpinnerTableGroups(appState: ScreenTablesState?) {
        if (appState is ScreenTablesState.Success) {
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                appState.result as List<*>
            ).also {
                viewBinding.spinnerTableGroups.adapter = it
            }
        }
    }

    companion object {
        fun newInstance() = TablesFragment()
    }
}