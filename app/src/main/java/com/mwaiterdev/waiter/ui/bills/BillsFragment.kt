package com.mwaiterdev.waiter.ui.bills

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.SimpleItemAnimator
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mwaiterdev.domain.AppState
import com.mwaiterdev.domain.models.response.BillsResponse
import com.mwaiterdev.utils.extensions.showAlertDialogFragment
import com.mwaiterdev.waiter.R
import com.mwaiterdev.waiter.databinding.FragmentBillsBinding
import com.mwaiterdev.waiter.ui.TitleToolbarListener
import com.mwaiterdev.waiter.ui.bills.adapters.AdapterBills
import org.koin.android.ext.android.getKoin

class BillsFragment : Fragment(R.layout.fragment_bills) {

    private val viewBinding: FragmentBillsBinding by viewBinding(CreateMethod.INFLATE)
    private val scope = getKoin().createScope<BillsFragment>()
    private val viewModel: BillsViewModel = scope.get()

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
            NavHostFragment.findNavController(this).navigate(R.id.nav_tables)
        }
    }

    private fun setBillItemListener(billId: Long?) = View.OnClickListener {
        NavHostFragment.findNavController(this).navigate(R.id.nav_bill, bundleOf().apply {
            billId?.let { billId -> putLong(BILL_ID, billId) }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })
        viewModel.getData()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getData()
    }

    private fun renderData(appState: AppState?) {
        when (appState) {
            is AppState.Success -> {
                if (appState.data is BillsResponse) {
                    initAdapter(appState.data as BillsResponse)
                    initExpandedFilter(appState.data as BillsResponse)
                    initTitleToolBar(appState.data as BillsResponse)
                    viewBinding.mineBillsSwitcher.setOnCheckedChangeListener { _, isChecked ->
                        Log.e("error", "clicked")
                        (viewBinding.billsRecycleView.adapter as AdapterBills).getMineBills(
                            1L,
                            isChecked
                        )
                    }
                }
            }
            is AppState.Error -> {
                showAlertDialogFragment(requireContext(), appState.error.localizedMessage)
            }
            else -> null
        }
    }

    private fun initTitleToolBar(data: BillsResponse) {
        data.tableGroups
            ?.first()
            ?.tables
            ?.first()
            ?.bills
            ?.first()?.let {
                (activity as TitleToolbarListener).updateTitle(
                    it
                        .createdByUserName
                )
            }
    }

    private fun initAdapter(data: BillsResponse) {
        (viewBinding.billsRecycleView.itemAnimator as SimpleItemAnimator)
            .supportsChangeAnimations = false
        viewBinding.billsRecycleView.adapter = AdapterBills(
            data.tableGroups
        ) { billId: Long? -> setBillItemListener(billId) }
    }

    private fun initExpandedFilter(data: BillsResponse) {
        val listTitleOfHals = data.tableGroups?.map {
            it.name
        } as MutableList<String>
        listTitleOfHals.add(0, "All Hals")

        viewBinding.spinnerTableGroups.adapter = ArrayAdapter(
            requireContext(),
            R.layout.support_simple_spinner_dropdown_item,
            listTitleOfHals
        )

        viewBinding.spinnerTableGroups.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    (viewBinding.billsRecycleView.adapter as AdapterBills).filter.filter(
                        listTitleOfHals[position]
                    )
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    viewBinding.billsRecycleView.adapter = AdapterBills(
                        data.tableGroups
                    ) { billId: Long? -> setBillItemListener(billId) }
                }
            }
    }

    companion object {
        const val BILL_ID = "billId"
        fun newInstance() = BillsFragment()
    }
}