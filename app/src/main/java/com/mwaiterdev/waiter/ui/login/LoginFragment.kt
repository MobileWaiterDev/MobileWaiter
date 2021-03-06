package com.mwaiterdev.waiter.ui.login

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.textfield.TextInputEditText
import com.mwaiterdev.domain.ScreenState
import com.mwaiterdev.domain.models.User
import com.mwaiterdev.waiter.R
import com.mwaiterdev.waiter.databinding.FragmentLoginBinding
import com.mwaiterdev.waiter.ui.IScreenView
import com.mwaiterdev.waiter.ui.TitleToolbarListener
import com.mwaiterdev.waiter.ui.bill.BillFragment
import org.koin.java.KoinJavaComponent

class LoginFragment : Fragment(R.layout.fragment_login), IScreenView {
    private val scope = KoinJavaComponent.getKoin().createScope<LoginFragment>()
    private val viewModel: LoginViewModel = scope.get()
    private val viewBinding: FragmentLoginBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getLiveData()
            .observe(viewLifecycleOwner, { user -> renderLogin(result = user) })

        viewBinding.loginBtn.setOnClickListener {
            viewModel.logIn(viewBinding.inputValue.text.toString())
            hideKeyboardForTextView()
        }

        viewBinding.inputValue.setOnEditorActionListener { view, actionId, event ->
            if (actionId == android.view.inputmethod.EditorInfo.IME_ACTION_DONE) {
                if (view.text.isNotEmpty()) {
                    hideKeyboardForTextView()
                    viewModel.logIn(view.text.toString())
                    true
                } else {
                    false
                }
            } else {
                false
            }
        }
    }

    private fun renderLogin(result: ScreenState?) {
        when (result) {
            is ScreenState.Success -> {
                renderSuccess(result)
            }
            is ScreenState.Error -> {
                renderError(result)
            }
            is ScreenState.Loading -> {
                showLoading(true)
            }
            else -> {
                throw IllegalArgumentException(BillFragment.ILLEGAL_STATE_ERROR)
            }
        }
    }

    private fun renderError(result: ScreenState.Error) {
        showLoading(false)
        showError(result.error)
        viewBinding.textInputLayout.error = result.error.localizedMessage
    }

    private fun renderSuccess(result: ScreenState.Success) {
        showLoading(false)
        when (result.data) {
            is User -> {
                val user = (result.data as User)
                (activity as TitleToolbarListener).setUser(user)
                NavHostFragment.findNavController(this).navigate(R.id.nav_bills, bundleOf().apply {
                    putString(BUNDLE_KEY_USER_NAME, user.name)
                })
            }
        }
    }

    override fun onStart() {
        showToolBar(false)
        super.onStart()
    }

    override fun onStop() {
        showToolBar(true)
        super.onStop()
    }

    /*???????????? ????????????????????*/
    private fun hideKeyboardForTextView() {
        val view = requireActivity().currentFocus
        view?.let {
            val inputMethodManager =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as
                        InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(it.windowToken, INPUT_METHOD_MANAGER_FLAGS)
        }
        (view as? TextInputEditText)?.clearFocus()
    }

    private fun showToolBar(visible: Boolean) {
        (activity as TitleToolbarListener).showToolBar(visible)
    }

    override fun showLoading(visible: Boolean) {
        viewBinding.progress.isVisible = visible
        viewBinding.loginBtn.isEnabled = visible.not()
    }

    override fun showError(error: Throwable) {
    }

    companion object {
        fun newInstance() = LoginFragment()
        private const val INPUT_METHOD_MANAGER_FLAGS = 0
        const val BUNDLE_KEY_USER_NAME = "User_name"
    }
}