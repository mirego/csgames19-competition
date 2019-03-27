package com.csgames.mixparadise.helper

import java.security.MessageDigest

object DigestHelper {
    fun sha1(text: String): String {
        val messageDigest = MessageDigest.getInstance("SHA-1")
        messageDigest.update(text.toByteArray())
        val bytes = messageDigest.digest()
        return encodeHex(bytes)
    }

    private fun encodeHex(bytes: ByteArray): String {
        val hex = StringBuffer(bytes.size * 2)

        for (i in bytes.indices) {
            if (bytes[i].toInt() and 0xff < 0x10) {
                hex.append("0")
            }
            hex.append(Integer.toString(bytes[i].toInt() and 0xff, 16))
        }

        return hex.toString()
    }
}