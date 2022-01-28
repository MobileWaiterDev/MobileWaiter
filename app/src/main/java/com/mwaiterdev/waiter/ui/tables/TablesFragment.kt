package com.mwaiterdev.waiter.ui.tables

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mwaiterdev.domain.ScreenState
import com.mwaiterdev.domain.models.TableGroupItem
import com.mwaiterdev.domain.models.TableItem
import com.mwaiterdev.domain.models.response.Tables
import com.mwaiterdev.utils.extensions.TABLE_STATE_BUSY
import com.mwaiterdev.utils.extensions.showSnakeBar
import com.mwaiterdev.waiter.R
import com.mwaiterdev.waiter.databinding.FragmentTablesBinding
import com.mwaiterdev.waiter.ui.TitleToolbarListener
import com.mwaiterdev.waiter.ui.tables.adapter.TablesAdapter
import org.koin.android.ext.android.getKoin

class TablesFragment : Fragment(R.layout.fragment_tables), TablesAdapter.Delegate {

    private val scope = getKoin().createScope<TablesFragment>()
    private val viewModel: TablesViewModel = scope.get()

    private val viewBinding: FragmentTablesBinding by viewBinding()

    private val tablesAdapter by lazy { TablesAdapter(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecyclerSetting()

        viewModel.getLiveData()
            .observe(viewLifecycleOwner, { renderData(it) })

        viewModel.getData()
    }

    private fun initRecyclerSetting() {
        val tablesRV: RecyclerView = viewBinding.tablesList
        val gridLayoutManager = GridLayoutManager(context, HEADER_SIZE)
        /**
         * Определяем сколько ячеек занимать в зависимости от ItemViewType. Если это заголовок,
         * то занимаем 4 ячейки. Если это столик, то соответственно одну.
         */
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (tablesAdapter.getItemViewType(position) == TablesAdapter.HEADER) {
                    HEADER_SIZE
                } else TABLE_SIZE
            }
        }

        with(tablesRV) {
            layoutManager = gridLayoutManager
            adapter = tablesAdapter
            setHasFixedSize(true)
        }
    }

    private fun renderData(appState: ScreenState?) {
        when (appState) {
            is ScreenState.Success -> {
                when (appState.data) {
                    is Tables -> {
                        tablesAdapter.addItems((appState.data as Tables).data)
                    }
                }
            }
            is ScreenState.Error -> {
            }
            is ScreenState.Loading -> {
            }
        }
    }

    override fun onItemPicked(table: TableItem) {
        if (table.state == TABLE_STATE_BUSY) {
            viewBinding.root.showSnakeBar("Данный столик занят, выберите другой.")
            return
        }

        NavHostFragment.findNavController(this)
            .navigate(R.id.action_tables_to_bill, bundleOf().apply {
                putLong(KEY_TABLE_ID, table.tableId)
            })
    }

    override fun onGroupPicked(group: TableGroupItem) {
        viewBinding.root.showSnakeBar(group.name)
    }

    override fun onStart() {
        super.onStart()
        (activity as TitleToolbarListener).updateTitle("Выбор столика")
    }

    override fun onStop() {
        super.onStop()
        (activity as TitleToolbarListener).updateTitle("")
    }

    companion object {
        fun newInstance() = TablesFragment()

        private const val HEADER_SIZE = 4
        private const val TABLE_SIZE = 1
        const val KEY_TABLE_ID = "tableId"
    }
}