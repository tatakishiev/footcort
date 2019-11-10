package endpoint.user

import mapper.user.UserMapper
import service.user.UserService

interface UserEndpoint {
}

class UserEndpointImpl(private val userService: UserService,
                       private val userMapper: UserMapper) : UserEndpoint {
}