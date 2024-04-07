package com.example.compose_clean_base.app.controller

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.semantics.Role

internal interface MultipleEvents {
    fun processEvent(event: () -> Unit)

    companion object
}

internal fun MultipleEvents.Companion.get(): MultipleEvents =
    MultipleEventsImpl()

private class MultipleEventsImpl : MultipleEvents {
    private val now: Long
        get() = System.currentTimeMillis()

    private var lastEventTimeMs: Long = 0

    override fun processEvent(event: () -> Unit) {
        if (now - lastEventTimeMs >= 300L) {
            event.invoke()
        }
        lastEventTimeMs = now
    }
}

fun Modifier.clickableWithoutRipple(
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    onClick: () -> Unit
) = this.then(
    Modifier.clickable(
        interactionSource = interactionSource,
        indication = null,
        onClick = { onClick() }
    )
)

fun Modifier.clickableSingle(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit
) = this.then(
    composed(
        inspectorInfo = debugInspectorInfo {
            name = "clickable"
            properties["enabled"] = enabled
            properties["onClickLabel"] = onClickLabel
            properties["role"] = role
            properties["onClick"] = onClick
        },
        factory = {
            val multipleEventsCutter = remember { MultipleEvents.get() }
            Modifier.clickable(
                enabled = enabled,
                onClickLabel = onClickLabel,
                onClick = { multipleEventsCutter.processEvent { onClick() } },
                role = role,
                indication = LocalIndication.current,
                interactionSource = remember { MutableInteractionSource() }
            )
        })
)