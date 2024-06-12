package com.example.framework.cache

import kotlin.reflect.KClass

class DataBridgeManager {

    companion object {
        private val storage = mutableMapOf<String, Any?>()
    }

    fun <T : Any> setValue(value: T, type: KClass<T>) {
        val key = type.qualifiedName ?: error("Cannot get the qualified name of the type")
        storage[key] = value
    }

    fun <T : Any> getValue(type: KClass<T>): T? {
        val key = type.qualifiedName ?: error("Cannot get the qualified name of the type")
        return storage[key] as? T
    }

    fun <T : Any> removeValue(type: KClass<T>) {
        val key = type.qualifiedName ?: error("Cannot get the qualified name of the type")
        storage.remove(key)
    }
}