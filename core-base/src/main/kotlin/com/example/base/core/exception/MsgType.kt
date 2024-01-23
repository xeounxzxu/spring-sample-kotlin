package com.example.base.core.exception

enum class MsgType constructor(
    private var code: String
) {
    SystemError("S001");

    fun getCode() = this.code
}
