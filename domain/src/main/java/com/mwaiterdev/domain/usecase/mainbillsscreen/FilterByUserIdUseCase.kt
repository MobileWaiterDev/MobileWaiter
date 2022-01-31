package com.mwaiterdev.domain.usecase.mainbillsscreen

import com.mwaiterdev.domain.models.TableGroup

class FilterByUserIdUseCase : InputOutputUseCase<Boolean, List<TableGroup>?, String> {
    override fun execute(argIn: Boolean, args: List<TableGroup>?, arg: String): List<TableGroup>? {
        return if (argIn) {
            args?.filter { tableGroup ->
                tableGroup.tables?.any { table ->
                    table.bills?.any {
                        it.createdByUserName == arg
                    } == true
                } == true
            }
        } else {
            args
        }
    }
}