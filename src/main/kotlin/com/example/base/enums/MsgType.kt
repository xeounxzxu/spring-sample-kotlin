package com.example.base.enums

enum class MsgType constructor(
    private var code: String
) {
    SystemError("S001");

    fun getCode() = this.code
}