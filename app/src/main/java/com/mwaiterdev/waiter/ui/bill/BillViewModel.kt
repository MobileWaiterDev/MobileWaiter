package com.mwaiterdev.waiter.ui.bill

import com.mwaiterdev.domain.ScreenState
import com.mwaiterdev.domain.models.CreateBillData
import com.mwaiterdev.domain.models.response.ResultOperation
import com.mwaiterdev.domain.usecase.GetUserUseCase
import com.mwaiterdev.domain.usecase.billscreen.*
import com.mwaiterdev.waiter.ui.bill.enums.TypeUpdate
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BillViewModel(
    private val getBillItemsUseCase: GetBillItemsUseCase,
    private val getMenuUseCase: GetMenuUseCase,
    private val createBillUseCase: CreateBillUseCase,
    private val getBillInfoUseCase: GetBillInfoUseCase,
    private val addItemIntoBillUseCase: AddItemIntoBillUseCase,
    private val updateAmountItemUseCase: UpdateAmountItemUseCase,
    private val deleteItemUseCase: DeleteItemUseCase,
    private val searchItemUseCase: SearchItemUseCase,
    private val updateFavouriteStateUseCase: UpdateFavouriteStateUseCase,
    private val getFavouriteMenuUseCase: GetFavouriteMenuUseCase,
    private val deleteBillUseCase: DeleteBillUseCase,
    private val sendCookItemsUseCase: SendCookItemsUseCase,
    private val deleteItemEmergencyUseCase: DeleteItemEmergencyUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val printBillUseCase: PrintBillUseCase,
    private val closeBillUseCase: CloseBillUseCase
) : BaseBillViewModel() {

    private var currentBillId: Long = ZERO_VALUE

    fun setCurrentBill(billId: Long) {
        currentBillId = billId
    }


    fun setBillPrinted(printed: Boolean) {
        blockEditLiveData().postValue(printed)
    }

    private var currentItemGroupId: Long = ZERO_VALUE

    fun setItemGroupId(itemGroupId: Long) {
        currentItemGroupId = itemGroupId
    }

    override fun getBillItems(needScrollToPosition: Boolean) =
        viewModelScopeCoroutine.launch {
            billItemsLiveData().postValue(
                ScreenState.Loading
            )
            val billItems =
                getBillItemsUseCase.execute(billId = currentBillId, needScrollToPosition)
            if (billItems.data.isNullOrEmpty().not()) {
                billItemsLiveData().postValue(
                    ScreenState.Success(data = billItems)
                )
            } else {
                billItemsLiveData().postValue(
                    ScreenState.Error(error = Exception(ERROR_NO_DATA))
                )
            }
        }

    override fun search(text: String) =
        viewModelScopeCoroutine.launch {
            menuLiveData().postValue(
                ScreenState.Loading
            )
            val result = searchItemUseCase.execute(text)
            if (result.data.isNullOrEmpty().not()) {
                menuLiveData().postValue(
                    ScreenState.Success(data = result)
                )
            } else {
                menuLiveData().postValue(
                    ScreenState.Error(error = Exception(ERROR_MESSAGE))
                )
            }
        }

    override fun getMenu(itemGroupId: Long) =
        viewModelScopeCoroutine.launch {
            menuLiveData().postValue(
                ScreenState.Loading
            )
            val result = getMenuUseCase.execute(itemGroupId)
            if (result.data.isNullOrEmpty().not()) {
                menuLiveData().postValue(
                    ScreenState.Success(data = result)
                )
            } else {
                menuLiveData().postValue(
                    ScreenState.Error(error = Exception(ERROR_MESSAGE))
                )
            }
        }

    override fun createBill(tableId: Long) =
        viewModelScopeCoroutine.launch {
            newBillLiveData().postValue(
                ScreenState.Loading
            )

            val userId: Long? = getUserUseCase.execute()?.userId
            userId?.let { userIdValue ->
                val arg = CreateBillData(tableId, userIdValue)

                createBillUseCase.execute(
                    arg = arg,
                    onSuccess = { result ->
                        newBillLiveData().postValue(ScreenState.Success(data = result.data))
                        setCurrentBill(result.data.data)
                        getBillInfo()
                    },
                    onError = {
                        newBillLiveData().postValue(
                            ScreenState.Error(error = Exception(ERROR_MESSAGE))
                        )
                    })
            }
        }

    override fun getBillInfo() =
        viewModelScopeCoroutine.launch {
            billInfoLiveData().postValue(
                ScreenState.Loading
            )
            val billInfo = getBillInfoUseCase.execute(billId = currentBillId)
            billInfoLiveData().postValue(
                ScreenState.Success(data = billInfo)
            )
        }

    override fun handleError(throwable: Throwable) {}

    override fun addItemIntoBill(itemId: Long, amount: Float, price: Float) =
        viewModelScopeCoroutine.launch {
            addItemIntoBillUseCase.execute(
                billId = currentBillId,
                itemId = itemId,
                amount = amount,
                price = price
            ).also {
                getBillItems(needScrollToPosition = true)
                getBillInfo()
            }
        }

    override fun updateAmount(billItemId: Long, amount: Float, price: Float) =
        viewModelScopeCoroutine.launch {
            updateAmountItemUseCase.execute(
                billItemId = billItemId,
                amount = amount,
                price = price
            ).also {
                getBillItems(needScrollToPosition = false)
                getBillInfo()
            }
        }

    override fun deleteItem(billItemId: Long) =
        viewModelScopeCoroutine.launch {
            deleteItemUseCase.execute(
                billItemId = billItemId,
            )
            getBillItems(needScrollToPosition = false)
            getBillInfo()
        }


    override fun deleteBill() =
        viewModelScopeCoroutine.launch {
            val billInfo = getBillInfoUseCase.execute(billId = currentBillId)
            if (billInfo.data.countItems == 0) {
                val result = deleteBillUseCase.execute(billId = currentBillId)
                deleteBillLiveData().postValue(ScreenState.Success(ResultOperation(result)))
            } else {
                deleteBillLiveData().postValue(ScreenState.Success(ResultOperation(false)))
            }
        }

    fun updateFavouriteState(
        favourite: Int,
        itemId: Long,
        typeUpdate: TypeUpdate,
        text: String = ""
    ) {
        val currentGroup = currentItemGroupId
        when (typeUpdate) {
            TypeUpdate.updateMenu -> {
                viewModelScopeCoroutine.launch {
                    updateFavouriteStateUseCase.execute(favourite, itemId)
                    getMenu(currentGroup)
                }
            }
            TypeUpdate.updateFavourite -> {
                viewModelScopeCoroutine.launch {
                    updateFavouriteStateUseCase.execute(favourite, itemId)
                    getFavouriteMenu()
                }
            }
            TypeUpdate.updateSearch -> {
                viewModelScopeCoroutine.launch {
                    updateFavouriteStateUseCase.execute(favourite, itemId)
                    search(text)
                }
            }
        }
    }

    override fun getFavouriteMenu() =
        viewModelScopeCoroutine.launch {
            menuLiveData().postValue(
                ScreenState.Loading
            )
            val result = getFavouriteMenuUseCase.execute()
            if (result.data.isNullOrEmpty().not()) {
                menuLiveData().postValue(
                    ScreenState.Success(data = result)
                )
            } else {
                menuLiveData().postValue(
                    ScreenState.Error(error = Exception(ERROR_MESSAGE))
                )
            }
        }

    override fun sendCookBill() =
        viewModelScopeCoroutine.launch {
            sendCookBillLiveData().postValue(ScreenState.Loading)
            delay(DELAY_SEND_COOK_REQUEST)
            val result = sendCookItemsUseCase.execute(billId = currentBillId)
            sendCookBillLiveData().postValue(ScreenState.Success(ResultOperation(result)))

        }

    override fun deleteEmergencyItem(billItemId: Long) =
        viewModelScopeCoroutine.launch {
            deleteItemEmergencyUseCase.execute(
                billItemId = billItemId,
            )
            getBillItems(needScrollToPosition = false)
            getBillInfo()
        }

    override fun printBill() =
        viewModelScopeCoroutine.launch {
            sendCookBill().join()
            val printResult = printBillUseCase.execute(currentBillId)
            printBillLiveData().postValue(ScreenState.Success(ResultOperation(printResult)))
        }

    override fun closeBill() =
        viewModelScopeCoroutine.launch {
            closeBillLiveData().postValue(ScreenState.Loading)
            delay(DELAY_SEND_COOK_REQUEST)
            val closeResult = closeBillUseCase.execute(currentBillId)
            closeBillLiveData().postValue(ScreenState.Success(ResultOperation(closeResult)))
        }

    companion object {
        //ToDo Вынести в ресурсы и создать интерактор для получения данных с ресурсов
        const val ERROR_MESSAGE = "Данные отсутствуют"
        const val ERROR_NO_DATA = "Отсутствуют данные..."
        const val ZERO_VALUE = 0L
        const val DELAY_SEND_COOK_REQUEST = 2500L
    }
}