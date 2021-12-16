package com.mwaiterdev.ui.login

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mwaiterdev.ui.R
import com.mwaiterdev.ui.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewBinding: FragmentLoginBinding by viewBinding()

    companion object {
        fun newInstance() = LoginFragment()
    }
}