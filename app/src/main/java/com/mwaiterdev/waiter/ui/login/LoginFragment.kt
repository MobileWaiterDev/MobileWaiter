package com.mwaiterdev.waiter.ui.login

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mwaiterdev.waiter.R
import com.mwaiterdev.waiter.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewBinding: FragmentLoginBinding by viewBinding()

    companion object {
        fun newInstance() = LoginFragment()
    }
}