package app.snapcart.cashier.data.models.auth

data class OtpResponse(val message: String, val retryAtSeconds: Long, val otpLength: Int)
