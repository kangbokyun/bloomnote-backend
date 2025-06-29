package com.bloomnote

class ResponseNear<T> {
    var statusCode: Int = 0
    var result: T? = null
    var message: String? = null

    constructor()
    constructor(statusCode: Int) { this.statusCode = statusCode }
    constructor(statusCode: Int, result: T) {
        this.statusCode = statusCode
        this.result = result
    }
    constructor(statusCode: Int, result: T, message: String) {
        this.statusCode = statusCode
        this.result = result
        this.message = message
    }
}