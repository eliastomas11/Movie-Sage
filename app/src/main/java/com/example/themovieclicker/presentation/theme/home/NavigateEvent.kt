package com.example.themovieclicker.presentation.theme.home

sealed interface OneTimeEvent{

    object Navigate : OneTimeEvent

    object Bookmark : OneTimeEvent

    object Filter : OneTimeEvent
}

fun OneTimeEvent.updateState() {
    when (this) {
        is OneTimeEvent.Navigate -> {
            val a = also {  }
        }

        else -> {}
    }
}
