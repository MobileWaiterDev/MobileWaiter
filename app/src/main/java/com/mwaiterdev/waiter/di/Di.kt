package com.mwaiterdev.waiter.di

import com.mwaiterdev.domain.repository.Repository
import com.mwaiterdev.domain.repository.RepositoryImp
import com.mwaiterdev.domain.usecase.billscreen.BillInteractorImpl
import com.mwaiterdev.domain.usecase.billscreen.IBillInteractor
import com.mwaiterdev.domain.usecase.mainbillsscreen.MainBillsIteractor
import com.mwaiterdev.domain.usecase.mainbillsscreen.MainBillsIteractorImpl
import com.mwaiterdev.domain.usecase.tablesscreen.ITablesInteractor
import com.mwaiterdev.domain.usecase.tablesscreen.TablesInteractorImpl
import com.mwaiterdev.waiter.ui.bill.BillFragment
import com.mwaiterdev.waiter.ui.bill.BillViewModel
import com.mwaiterdev.waiter.ui.bills.BillsFragment
import com.mwaiterdev.waiter.ui.bills.BillsViewModel
import com.mwaiterdev.waiter.ui.login.LoginFragment
import com.mwaiterdev.waiter.ui.login.LoginViewModel
import com.mwaiterdev.waiter.ui.tables.TablesFragment
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

        factory<IBillInteractor> {
            BillInteractorImpl(repository = get())
        }
        factory<MainBillsIteractor> {
            MainBillsIteractorImpl(repository = get())
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
                BillsViewModel(interactor = get())
            }
        }

        scope<TablesFragment> {
            viewModel() {
                TablesViewModel(interactor = get())
            }
        }

        scope<BillFragment> {
            viewModel() {
                BillViewModel(interactor = get())
            }
        }
    }
}