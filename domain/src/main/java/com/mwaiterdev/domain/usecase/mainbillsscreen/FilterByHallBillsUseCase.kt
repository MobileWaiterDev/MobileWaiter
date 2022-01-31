package com.mwaiterdev.domain.usecase.mainbillsscreen

import com.mwaiterdev.domain.models.TableGroup

class FilterByHallBillsUseCase : InputUseCase<String, List<TableGroup>?> {
    override fun execute(arg: String, args: List<TableGroup>?): List<TableGroup>? {
        return if (arg.isBlank() || args?.none { it.name == arg } == true) {
            args
        } else {
            args?.filter { it.name == arg }
        }
    }
}