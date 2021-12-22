package com.mwaiterdev.waiter.ui.bill

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mwaiterdev.domain.ScreenState
import com.mwaiterdev.waiter.R
import com.mwaiterdev.waiter.databinding.FragmentBillBinding
import org.koin.android.ext.android.getKoin

class BillFragment : Fragment(R.layout.fragment_bill) {

    private val scope = getKoin().createScope<BillFragment>()
    private val viewModel: BillViewModel = scope.get()
    private val viewBinding: FragmentBillBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getBillItemLiveData()
            .observe(viewLifecycleOwner, { billItems -> showBillItems(billItems) })

        viewModel.getItemGroupListLiveData()
            .observe(viewLifecycleOwner, { itemGroups -> showMenu(itemGroups) })

        viewModel.getItemListLiveData()
            .observe(viewLifecycleOwner, { items -> showMenu(items) })

        viewModel.getBillItems()
        viewModel.getItemGroups()
    }

    /** Отобразить меню */
    private fun showMenu(itemGroups: ScreenState?) {

    }

    /** Отобразить существующие товары в счете */
    private fun showBillItems(billItems: ScreenState?) {

    }

    companion object {
        fun newInstance() = BillFragment()
    }
}