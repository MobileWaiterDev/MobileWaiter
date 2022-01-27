package com.mwaiterdev.waiter.ui.bills

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.SimpleItemAnimator
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mwaiterdev.domain.AppState
import com.mwaiterdev.domain.models.TableGroup
import com.mwaiterdev.utils.extensions.showAlertDialogFragment
import com.mwaiterdev.waiter.R
import com.mwaiterdev.waiter.databinding.FragmentBillsBinding
import com.mwaiterdev.waiter.ui.TitleToolbarListener
import com.mwaiterdev.waiter.ui.bills.adapters.AdapterBills
import org.koin.android.ext.android.getKoin

class BillsFragment : Fragment(R.layout.fragment_bills) {

    private val scope = getKoin().createScope<BillsFragment>()
    private val viewModel: BillsViewModel = scope.get()
    private val viewBinding: FragmentBillsBinding by viewBinding()
    private var data: List<TableGroup>? = null
    private var adapter: AdapterBills? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        setFloatingActionButtonListener()

        initBackPressListener()
    }

    private fun initBackPressListener() {
        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val builder = AlertDialog.Builder(requireContext())
                    builder.setTitle(APP_TITLE)
                        .setMessage(APP_CLOSE_QUESTIONS)
                        .setIcon(R.drawable.ic_launcher_foreground)
                        .setPositiveButton(DIALOG_OK_BUTTON_TEXT) { _, _ ->
                            requireActivity().finish()
                        }
                        .setNegativeButton(DIALOG_CANCEL_BUTTON_TEXT) { dialog, id ->
                            dialog.cancel()
                        }
                    builder.create()
                    builder.show()
                }
            })
    }

    override fun onStart() {
        viewModel.getLiveData().observe(viewLifecycleOwner) { renderData(it) }
        viewModel.getData()
        viewBinding.mineBillsSwitcher.isChecked = viewModel.initFilterData()
        super.onStart()
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

    private fun renderData(appState: AppState?) {
        when (appState) {
            is AppState.Success -> {
                if (appState.data is List<*>) {
                    data = appState.data as List<TableGroup>?
                    data?.let { data ->
                        initAdapter(data)
                        initExpandedFilter(data)
                        initTitleToolBar(data)
                        initSwitchMine()
                    }
                }
            }
            is AppState.Error -> {
                showAlertDialogFragment(requireContext(), appState.error.localizedMessage)
            }
            else -> null
        }
    }

    private fun initSwitchMine() {
        viewBinding.mineBillsSwitcher.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setFilter(isChecked)
            viewBinding.spinnerTableGroups.setSelection(0).apply {
                adapter?.getMineBills(
                    viewModel.filterByUserId(isChecked, data)
                )
            }
        }
    }

    private fun initTitleToolBar(data: List<TableGroup>) {
        data
            .firstOrNull()
            ?.tables
            ?.firstOrNull()
            ?.bills
            ?.firstOrNull()?.let {
                (activity as TitleToolbarListener).updateTitle(
                    it.createdByUserName
                )
            }
    }

    private fun initAdapter(data: List<TableGroup>) {
        (viewBinding.billsRecycleView.itemAnimator as SimpleItemAnimator)
            .supportsChangeAnimations = false
        adapter = AdapterBills(
            data
        ) { billId: Long? -> setBillItemListener(billId) }
        viewBinding.billsRecycleView.adapter = adapter
    }

    private fun initExpandedFilter(data: List<TableGroup>) {
        val listTitleOfHals = data.map {
            it.name
        } as MutableList<String>
        listTitleOfHals.add(0, ALL_HALS)

        viewBinding.spinnerTableGroups.adapter = ArrayAdapter(
            requireContext(),
            R.layout.support_simple_spinner_dropdown_item,
            listTitleOfHals
        )

        viewModel.initHallSwitcherItem()
            .let { position -> viewBinding.spinnerTableGroups.setSelection(position) }

        viewBinding.spinnerTableGroups.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    adapter?.filter(viewModel.filterBillsByHall(listTitleOfHals[position], data))
                    viewModel.setHallSwitcherItem(position)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }
    }

    companion object {
        const val BILL_ID = "billId"
        const val ALL_HALS = "All Hals"
        const val APP_TITLE = "Mobile Waiter"
        const val APP_CLOSE_QUESTIONS = "Вы уверены, что хотите закрыть приложение?"
        const val DIALOG_OK_BUTTON_TEXT = "Закрыть"
        const val DIALOG_CANCEL_BUTTON_TEXT = "Отмена"
        fun newInstance() = BillsFragment()
    }
}