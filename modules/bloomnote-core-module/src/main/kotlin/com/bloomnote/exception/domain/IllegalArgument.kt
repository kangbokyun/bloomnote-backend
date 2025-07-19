package com.bloomnote.exception.domain

enum class IllegalArgument(val state: String, val errorCode: Int) {
    BAD_REQUEST("bad_request", 400);

    companion object {
        fun fromState(state: String) = entries.first { it.state == state }
    }
}