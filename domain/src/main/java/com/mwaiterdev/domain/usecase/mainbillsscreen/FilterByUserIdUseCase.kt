package com.mwaiterdev.domain.usecase.mainbillsscreen

import com.mwaiterdev.domain.models.TableGroup

class FilterByUserIdUseCase : InputUseCase<Boolean, List<TableGroup>?> {
    override fun execute(arg: Boolean, args: List<TableGroup>?): List<TableGroup>? {
        if (arg) {
            val userId = args
                ?.first()
                ?.tables
                ?.first()
                ?.bills
                ?.first()
                ?.createdByUserId

            return args?.filter { tableGroup ->
                tableGroup.tables?.any { table ->
                    table.bills?.any {
                        it.createdByUserId == userId
                    } == true
                } == true
            }
        } else {
            return args
        }
    }
}