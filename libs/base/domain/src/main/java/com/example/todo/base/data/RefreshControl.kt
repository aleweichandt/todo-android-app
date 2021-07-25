package com.example.todo.base.data

import java.util.*
import java.util.concurrent.TimeUnit

class RefreshControl(
    rate: Long = DEFAULT_REFRESH_RATE_MS,
    private var lastUpdateDate: Date? = null
) : ITimeLimitedResource {
    companion object {
        val DEFAULT_REFRESH_RATE_MS = TimeUnit.MINUTES.toMillis(5)
    }

    interface Listener {
        suspend fun cleanup()
    }

    private val listeners: MutableList<Listener> = mutableListOf()
    private val children: MutableList<RefreshControl> = mutableListOf()

    // ITimeLimitedResource
    override var refreshRate: Long = rate
    override val lastUpdate: Date?
        get() = lastUpdateDate

    override suspend fun evict(cleanup: Boolean) {
        lastUpdateDate = null
        children.forEach { it.evict(cleanup) }
        if (cleanup) {
            listeners.forEach { it.cleanup() }
        }
    }

    // Public API
    fun createChild(): RefreshControl =
        RefreshControl(refreshRate, lastUpdateDate).also { children.add(it) }

    fun addListener(listener: Listener) {
        listeners.add(listener)
    }

    fun refresh() {
        lastUpdateDate = Date()
    }

    fun isExpired() = lastUpdateDate?.let { (Date().time - it.time) > refreshRate } ?: true
}
