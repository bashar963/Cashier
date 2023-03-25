package app.snapcart.cashier.data.models.auth

sealed class AuthExceptions : Exception()

class LoginDeniedExceptions : AuthExceptions()
class IncorrectOTPExceptions : AuthExceptions()
