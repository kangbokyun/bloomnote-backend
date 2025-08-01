package com.bloomnote.exception.domain

enum class IllegalArgument(val state: String, val errorCode: Int) {
    BAD_REQUEST("bad_request", 400),
    INVALID_TOKEN("invalid_token", 401),
    EXPIRED_TOKEN("expired_token", 401),
    TOKEN_MISSING("token_missing", 401),
    TOKEN_INVALID("token_invalid", 401);

    companion object {
        fun fromState(state: String) = entries.first { it.state == state }
    }
}