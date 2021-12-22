package com.mwaiterdev.waiter.ui.tables

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mwaiterdev.domain.ScreenTablesState
import com.mwaiterdev.domain.models.ITableItem
import com.mwaiterdev.domain.models.TableGroupItem
import com.mwaiterdev.domain.models.TableItem
import com.mwaiterdev.utils.extensions.showSnakeBar
import com.mwaiterdev.waiter.R
import com.mwaiterdev.waiter.databinding.FragmentTablesBinding
import com.mwaiterdev.waiter.ui.tables.adapter.TablesAdapter
import org.koin.android.ext.android.getKoin

class TablesFragment : Fragment(R.layout.fragment_tables), TablesAdapter.Delegate {

    private val scope = getKoin().createScope<TablesFragment>()
    private val viewModel: TablesViewModel = scope.get()

    private val viewBinding: FragmentTablesBinding by viewBinding()

    private val tablesAdapter by lazy { TablesAdapter(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecyclerSetting()

        initObserve()

        viewModel.getTableGroups()
        viewModel.getTables()
    }

    private fun initRecyclerSetting() {
        val tablesRV: RecyclerView = viewBinding.tablesList
        val gridLayoutManager = GridLayoutManager(context, HEADER_SIZE)
        /**
         * Определяем сколько ячеек занимать в зависимости от ItemViewType. Если это заголовок,
         * то занимаем 4 ячейки. Если это товар, то соответственно одну.
         */
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (tablesAdapter.getItemViewType(position) == TablesAdapter.HEADER) {
                    HEADER_SIZE
                } else BILL_SIZE
            }
        }

        with(tablesRV) {
            layoutManager = gridLayoutManager
            adapter = tablesAdapter
            setHasFixedSize(true)
        }
    }

    private fun initObserve() {
        viewModel.getTableGroupsLiveData()
            .observe(viewLifecycleOwner, { fillSpinnerTableGroups(it) })

        viewModel.getTablesLiveData()
            .observe(viewLifecycleOwner, { fillTables(it) })
    }

    private fun fillTables(appState: ScreenTablesState?) {
        if (appState is ScreenTablesState.Success) {
            tablesAdapter.addItems(appState.result as ArrayList<ITableItem>)
        }
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

    override fun onItemPicked(table: TableItem) {
        viewBinding.root.showSnakeBar(table.name)
    }

    override fun onGroupPicked(group: TableGroupItem) {
        viewBinding.root.showSnakeBar(group.name)
    }

    companion object {
        fun newInstance() = TablesFragment()

        private const val HEADER_SIZE = 4
        private const val BILL_SIZE = 1
    }
}