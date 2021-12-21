package com.mwaiterdev.waiter.ui

interface TitleToolbarListener {
    fun updateTitle(title: String)

    /**
     * Скрыть/показать ToolBar
     * @param isVisible - true/false
     */
    fun showToolBar(isVisible: Boolean)
}