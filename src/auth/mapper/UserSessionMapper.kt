package auth.mapper

import configuration.application.UserSession
import user.entity.User

interface UserSessionMapper {
    fun toUserSession(user: User): UserSession
}

class UserSessionMapperImpl : UserSessionMapper {

    override fun toUserSession(user: User): UserSession {
        return UserSession(
            id = user.id,
            phoneNumber = user.phoneNumber,
            role = user.role
        )
    }
}