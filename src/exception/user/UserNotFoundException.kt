package exception.user

open class UserByPhoneNumberNotFoundException(phoneNumber: String) : Exception("there is no user with $phoneNumber")