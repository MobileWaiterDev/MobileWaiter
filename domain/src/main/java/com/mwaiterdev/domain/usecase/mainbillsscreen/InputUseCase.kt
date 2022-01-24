package com.mwaiterdev.domain.usecase.mainbillsscreen

interface InputUseCase<R,T> {
     fun execute(arg: R, args : T): T
}