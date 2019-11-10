package utils

import com.auth0.jwt.algorithms.Algorithm

object Cipher {
    val algorithm = Algorithm.HMAC256("umpa-lumpa-paspartu")

    fun encrypt(data: String?): ByteArray =
            algorithm.sign(data?.toByteArray())
}
