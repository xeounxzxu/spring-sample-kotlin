package com.example.base

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class JasyptTest {

    private var jasypt: StandardPBEStringEncryptor? = null

    @BeforeEach
    fun before() {

        jasypt = StandardPBEStringEncryptor()

        jasypt!!.setPassword("")

        jasypt!!.setAlgorithm("PBEWithMD5AndDES")

    }

    @Test
    @DisplayName("jaspt 암복호화 테스트")
    fun decodeTest() {

        var array: Array<String> = arrayOf(
            "jdbc:mysql://ec2-3-36-123-250.ap-northeast-2.compute.amazonaws.com:13306/app1",
            "root",
            "1234"
        )

        pritText(array, jasypt!!)

    }

    /**
     * Print method </br>
     * @param array Array<String>
     * @param jasypt StandardPBEStringEncryptor
     * @return void
     */
    private fun pritText(array: Array<String>, jasypt: StandardPBEStringEncryptor) {
        for (encrypt in array) {
            val encryptedText = jasypt.encrypt(encrypt)
            val decryptText = jasypt.decrypt(encryptedText)
            println("encryptedText:  $encryptedText")
            println("decode text :  $decryptText")
        }
    }

}
