package com.mwaiterdev.domain.usecase.mainbillsscreen

interface InputOutputUseCase<R,T,U> {
    fun execute(argIn: R, args : T, arg: U): T
}