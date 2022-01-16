package com.mwaiterdev.waiter.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mwaiterdev.data.BuildConfig
import com.mwaiterdev.data.api.WaiterApi
import com.mwaiterdev.data.api.WaiterApiInterceptor
import com.mwaiterdev.data.repository.RepositoryImpl
import com.mwaiterdev.data.repository.RepositoryMockImp
import com.mwaiterdev.data.repository.datasource.IRemoteDataSource
import com.mwaiterdev.data.repository.datasource.RemoteDataSourceImpl
import com.mwaiterdev.domain.repository.Repository
import com.mwaiterdev.domain.usecase.billscreen.*
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
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Di {

    private const val REPOSITORY_MOCK = "Mock"
    private const val REPOSITORY_REMOTE = "Remote"

    fun repositoryModule() = module {
        single<Repository>(qualifier = named(REPOSITORY_MOCK)) {
            RepositoryMockImp()
        }

        single<Repository>(qualifier = named(REPOSITORY_REMOTE)) {
            RepositoryImpl(dataSource = get())
        }

        single<IRemoteDataSource> {
            RemoteDataSourceImpl(waiterApi = get())
        }
    }

    fun interactorModule() = module {
        factory<ITablesInteractor> {
            TablesInteractorImpl(
                repository = get(
                    named(REPOSITORY_REMOTE)
                )
            )
        }

        factory<MainBillsIteractor> {
            MainBillsIteractorImpl(
                repository = get(
                    named(REPOSITORY_REMOTE)
                )
            )
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
                BillViewModel(
                    getBillItemsUseCase = get(),
                    getMenuUseCase = get(),
                    createBillUseCase = get(),
                    getBillInfoUseCase = get(),
                    addItemIntoBillUseCase = get(),
                    updateAmountItemUseCase = get(),
                    deleteItemUseCase = get()
                )
            }
        }
    }

    fun waiterApiModule() = module {
        single<Interceptor> {
            WaiterApiInterceptor()
        }

        single<Gson> {
            GsonBuilder()
                .create()
        }

        single<WaiterApi> {
            Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(
                    OkHttpClient.Builder()
                        .addInterceptor(interceptor = get())
                        .addInterceptor(HttpLoggingInterceptor().apply {
                            level = if (BuildConfig.DEBUG) {
                                HttpLoggingInterceptor.Level.BODY
                            } else {
                                HttpLoggingInterceptor.Level.NONE
                            }
                        })
                        .build()
                )
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create(get()))
                .build()
                .create(WaiterApi::class.java)
        }
    }

    fun useCasesModule() = module {
        factory<GetBillItemsUseCase> {
            GetBillItemsUseCase(
                repository = get(
                    named(REPOSITORY_REMOTE)
                )
            )
        }

        factory<GetMenuUseCase> {
            GetMenuUseCase(
                repository = get(
                    named(REPOSITORY_REMOTE)
                )
            )
        }

        factory<CreateBillUseCase> {
            CreateBillUseCase(
                repository = get(
                    named(REPOSITORY_REMOTE)
                )
            )
        }

        factory<GetBillInfoUseCase> {
            GetBillInfoUseCase(
                repository = get(
                    named(REPOSITORY_REMOTE)
                )
            )
        }

        factory<AddItemIntoBillUseCase> {
            AddItemIntoBillUseCase(
                repository = get(
                    named(REPOSITORY_REMOTE)
                )
            )
        }

        factory<UpdateAmountItemUseCase> {
            UpdateAmountItemUseCase(
                repository = get(
                    named(REPOSITORY_REMOTE)
                )
            )
        }

        factory<DeleteItemUseCase> {
            DeleteItemUseCase(
                repository = get(
                    named(REPOSITORY_REMOTE)
                )
            )
        }
    }
}