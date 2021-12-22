package com.mwaiterdev.waiter.di

import com.mwaiterdev.domain.repository.Repository
import com.mwaiterdev.domain.repository.RepositoryImp
import com.mwaiterdev.waiter.ui.bill.BillFragment
import com.mwaiterdev.waiter.ui.bill.BillViewModel
import com.mwaiterdev.waiter.ui.bills.BillsFragment
import com.mwaiterdev.waiter.ui.bills.BillsViewModel
import com.mwaiterdev.waiter.ui.login.LoginFragment
import com.mwaiterdev.waiter.ui.login.LoginViewModel
import com.mwaiterdev.waiter.ui.tables.ITablesInteractor
import com.mwaiterdev.waiter.ui.tables.TablesFragment
import com.mwaiterdev.waiter.ui.tables.TablesInteractorImpl
import com.mwaiterdev.waiter.ui.tables.TablesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object Di {

    fun repositoryModule() = module {
        single<Repository> {
            RepositoryImp()
        }
    }

    fun interactorModule() = module {
        factory<ITablesInteractor> {
            TablesInteractorImpl(repository = get())
        }
    }

    fun viewModelModule() = module {
        scope<LoginFragment> {
            viewModel() {
                LoginViewModel()
            }
        }

        scope<BillsFragment> {
            viewModel() {
                BillsViewModel(repository = get())
            }
        }

        scope<TablesFragment> {
            viewModel() {
                TablesViewModel(interactor = get())
            }
        }

        scope<BillFragment> {
            viewModel() {
                BillViewModel()
            }
        }
    }
}