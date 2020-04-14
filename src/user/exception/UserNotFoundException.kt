package user.exception

open class UserByPhoneNumberNotFoundException(phoneNumber: String) : Exception("there is no user with $phoneNumber")