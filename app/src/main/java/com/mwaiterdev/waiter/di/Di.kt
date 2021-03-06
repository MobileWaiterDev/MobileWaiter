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
import com.mwaiterdev.data.repository.sharedpref.LocalRepositoryImpl
import com.mwaiterdev.domain.models.TableGroup
import com.mwaiterdev.domain.repository.LocalRepository
import com.mwaiterdev.domain.repository.Repository
import com.mwaiterdev.domain.usecase.GetUserUseCase
import com.mwaiterdev.domain.usecase.OutputUseCase
import com.mwaiterdev.domain.usecase.billscreen.*
import com.mwaiterdev.domain.usecase.loginscreen.LogInUseCase
import com.mwaiterdev.domain.usecase.mainbillsscreen.*

import com.mwaiterdev.domain.usecase.tablesscreen.GetTablesUseCase
import com.mwaiterdev.domain.usecase.tablesscreen.ITablesInteractor
import com.mwaiterdev.domain.usecase.tablesscreen.TablesInteractorImpl
import com.mwaiterdev.utils.extensions.SharedPreferences.BillsLocalStorage
import com.mwaiterdev.utils.extensions.SharedPreferences.BillsLocalStorageImpl
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
    private const val FILTER_BY_USER_ID_USE_CASE = "filterByUserIdUseCase"
    private const val FILTER_BY_HALL_USE_CASE = "Remote"

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
    }

    fun sharedPrefModule() = module {
        factory<BillsLocalStorage> {
            BillsLocalStorageImpl(get())
        }

        factory<LocalRepository> {
            LocalRepositoryImpl(get())
        }
    }

    fun viewModelModule() = module {
        scope<LoginFragment> {
            viewModel() {
                LoginViewModel(
                    logInUseCase = get()
                )
            }
        }

        scope<BillsFragment> {
            viewModel() {
                BillsViewModel(
                    interactor = get(),
                    preferences = get(),
                    filterUseCase = get(
                        named(FILTER_BY_HALL_USE_CASE)
                    ),
                    filterByUserIdUseCase = get(
                        named(FILTER_BY_USER_ID_USE_CASE)
                    ),
                    getUserUseCase = get()
                )
            }
        }

        scope<TablesFragment> {
            viewModel() {
                TablesViewModel(
                    getTablesUseCase = get()
                )
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
                    deleteItemUseCase = get(),
                    searchItemUseCase = get(),
                    updateFavouriteStateUseCase = get(),
                    getFavouriteMenuUseCase = get(),
                    deleteBillUseCase = get(),
                    deleteItemEmergencyUseCase = get(),
                    sendCookItemsUseCase = get(),
                    getUserUseCase = get(),
                    printBillUseCase = get(),
                    closeBillUseCase = get()
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
        factory<GetTablesUseCase> {
            GetTablesUseCase(
                repository = get(
                    named(REPOSITORY_REMOTE)
                )
            )
        }

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

        factory<LogInUseCase> {
            LogInUseCase(
                repository = get(named(REPOSITORY_REMOTE)),
                localRepository = get()
            )
        }

        factory<OutputUseCase<List<TableGroup>?>> {
            GetBillsUseCase(
                repository = get(
                    named(REPOSITORY_REMOTE)
                )
            )
        }

        factory<InputUseCase<String, List<TableGroup>?>>(qualifier = named(FILTER_BY_HALL_USE_CASE)) {
            FilterByHallBillsUseCase()
        }

        factory<InputOutputUseCase<Boolean, List<TableGroup>?, String>>(
            qualifier = named(FILTER_BY_USER_ID_USE_CASE)
        ) {
            FilterByUserIdUseCase()
        }

        /*???????????????? ???????????????? ?????????????????????????????? ????????????????????????*/
        factory<GetUserUseCase> {
            GetUserUseCase(
                localRepository = get()
            )
        }

        factory<SearchItemUseCase> {
            SearchItemUseCase(
                repository = get(
                    named(REPOSITORY_REMOTE)
                )
            )
        }

        factory<UpdateFavouriteStateUseCase> {
            UpdateFavouriteStateUseCase(
                repository = get(
                    named(REPOSITORY_REMOTE)
                )
            )
        }

        factory<GetFavouriteMenuUseCase> {
            GetFavouriteMenuUseCase(
                repository = get(
                    named(REPOSITORY_REMOTE)
                )
            )
        }

        factory<DeleteBillUseCase> {
            DeleteBillUseCase(
                repository = get(
                    named(REPOSITORY_REMOTE)
                )
            )
        }

        factory<SendCookItemsUseCase> {
            SendCookItemsUseCase(
                repository = get(
                    named(REPOSITORY_REMOTE)
                )
            )
        }

        factory<DeleteItemEmergencyUseCase> {
            DeleteItemEmergencyUseCase(
                repository = get(
                    named(REPOSITORY_REMOTE)
                )
            )
        }

        factory<PrintBillUseCase> {
            PrintBillUseCase(
                repository = get(
                    named(REPOSITORY_REMOTE)
                )
            )
        }

        factory<CloseBillUseCase> {
            CloseBillUseCase(
                repository = get(
                    named(REPOSITORY_REMOTE)
                )
            )
        }
    }
}