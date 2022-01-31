package com.mwaiterdev.waiter.ui

import com.mwaiterdev.domain.models.User

interface TitleToolbarListener {
    fun updateTitle(title: String)

    /**
     * Скрыть/показать ToolBar
     * @param isVisible - true/false
     */
    fun showToolBar(isVisible: Boolean)

    /**
     * Установить группу и текущего пользователя в шторке
     * @param user - Пользователь
     */
    fun setUser(user: User)

    /**
     * Установить текст в туллбар
     * @param topTitle - Верхняя строка
     * @param downTitle - Нижняя строка
     */
    fun setMultiLineTitle(topTitle: String, downTitle: String)
}