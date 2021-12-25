package com.mwaiterdev.waiter.ui.bills

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.SimpleItemAnimator
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mwaiterdev.domain.AppState
import com.mwaiterdev.domain.models.TableGroup
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

    private fun setBillItemListener() = View.OnClickListener {
        NavHostFragment.findNavController(this).navigate(R.id.nav_bill)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })
        viewModel.getData()
    }

    private fun renderData(appState: AppState?) {
        when (appState) {
            is AppState.Success -> {
                if (appState.data is List<*>) {
                    (viewBinding.billsRecycleView.itemAnimator as SimpleItemAnimator).supportsChangeAnimations =
                        false
                    viewBinding.billsRecycleView.adapter = AdapterBills(
                        appState.data as List<TableGroup>,
                        setBillItemListener()
                    )
                    viewBinding.spinnerTableGroups.adapter = ArrayAdapter(
                        requireContext(),
                        R.layout.support_simple_spinner_dropdown_item,
                        (appState.data as List<TableGroup>).map {
                            it.name
                        } as List<*>)
                    (activity as TitleToolbarListener).updateTitle((appState.data as List<TableGroup>)[0].tables[0].userObserverName)
                }
            }
            else -> {
                TODO()
            }
        }
    }

    companion object {
        fun newInstance() = BillsFragment()
    }
}