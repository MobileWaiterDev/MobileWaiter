package com.mwaiterdev.waiter.ui.bills

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mwaiterdev.domain.AppState
import com.mwaiterdev.waiter.R
import com.mwaiterdev.waiter.databinding.FragmentBillsBinding
import com.mwaiterdev.waiter.ui.TitleToolbarListener
import com.mwaiterdev.waiter.ui.bills.adapters.AdapterBills

class BillsFragment : Fragment(R.layout.fragment_bills) {

    private val viewBinding: FragmentBillsBinding by viewBinding(CreateMethod.INFLATE)
    private val viewModel by lazy {
        ViewModelProvider(this).get(BillsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        setFloatingActionButtonListener()
        return viewBinding.root
    }

    private fun setFloatingActionButtonListener() {
        viewBinding.addBill.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.nav_bill)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })
        viewModel.gedData()
    }

    private fun renderData(appState: AppState?) {
        when (appState) {
            is AppState.Success -> {
                viewBinding.billsRecycleView.adapter = AdapterBills(appState.data)
                (activity as TitleToolbarListener).updateTitle(appState.data[0].tables[0].userObserverName)
            }
        }
    }

    companion object {
        fun newInstance() = BillsFragment()
    }
}