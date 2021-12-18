package com.mwaiterdev.waiter.ui.bills

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mwaiterdev.waiter.R
import com.mwaiterdev.waiter.databinding.FragmentBillsBinding
import com.mwaiterdev.waiter.ui.MainActivity
import com.mwaiterdev.waiter.ui.TitleToolbarListener

class BillsFragment : Fragment(R.layout.fragment_bills) {

    private val viewBinding: FragmentBillsBinding by viewBinding(CreateMethod.INFLATE)



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        //TODO: change to name of waitress
        (activity as TitleToolbarListener).updateTitle("MY BILLS")
        return viewBinding.root
    }

    companion object {
        fun newInstance() = BillsFragment()
    }
}