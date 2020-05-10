package auth.validation

import auth.dto.LoginRequestDto
import auth.dto.RegistrationDto
import org.valiktor.functions.isNotBlank
import org.valiktor.validate

interface AuthValidation {
    fun registrationValidation(registrationDto: RegistrationDto)
    fun loginValidation(loginRequestDto: LoginRequestDto)
}

class AuthValidationImpl : AuthValidation {
    override fun registrationValidation(registrationDto: RegistrationDto) {
        validate(registrationDto) {
            validate(RegistrationDto::phoneNumber).isNotBlank()
            validate(RegistrationDto::password).isNotBlank()
        }
    }

    override fun loginValidation(loginRequestDto: LoginRequestDto) {
        validate(loginRequestDto) {
            validate(LoginRequestDto::password).isNotBlank()
            validate(LoginRequestDto::phoneNumber).isNotBlank()
        }
    }
}