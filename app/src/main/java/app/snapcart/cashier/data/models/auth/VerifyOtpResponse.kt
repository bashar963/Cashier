package app.snapcart.cashier.data.models.auth

data class VerifyOtpResponse(
    val message: String,
    val accessToken: String,
    val refreshToken: String,
    val userId: String
)
