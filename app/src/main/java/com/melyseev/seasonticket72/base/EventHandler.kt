package com.melyseev.seasonticket72.base

interface EventHandler<T> {
    fun obtainEvent(event: T)
}