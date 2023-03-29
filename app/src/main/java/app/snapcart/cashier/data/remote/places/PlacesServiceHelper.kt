package app.snapcart.cashier.data.remote.places

import app.snapcart.cashier.data.models.places.Address

interface PlacesServiceHelper {
    suspend fun fetchAddresses(query:String) : Result<List<Address>>
}