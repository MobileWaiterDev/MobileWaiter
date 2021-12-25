package com.mwaiterdev.waiter.ui

interface IScreenView {
    /**
     * Отобразить/скрыть анимацию загрузки
     * @param visible Показать/скрыть
     */
    fun showLoading(visible: Boolean)

    /**
     * Отобразить ошибку
     * @param error Throwable
     */
    fun showError(error: Throwable)
}