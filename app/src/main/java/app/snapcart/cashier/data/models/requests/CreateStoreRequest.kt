package app.snapcart.cashier.data.models.requests

data class CreateStoreRequest(
    val ownerName: String,
    val storeName: String,
    val address: String,
    val lat: Double,
    val lng: Double,
    val outsidePhoto: String,
    val insidePhoto: String
)
