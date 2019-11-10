package exception.user

open class UserByPhoneNumberNotFoundException(phoneNumber: String) : Exception(phoneNumber)