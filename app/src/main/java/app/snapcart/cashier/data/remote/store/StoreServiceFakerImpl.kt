package app.snapcart.cashier.data.remote.store

import app.snapcart.cashier.data.models.Store
import app.snapcart.cashier.data.models.requests.CreateStoreRequest
import app.snapcart.cashier.utils.Response
import app.snapcart.cashier.utils.StatusSuccess
import kotlinx.coroutines.delay

class StoreServiceFakerImpl :
    StoreService {

    override suspend fun createStore(request: CreateStoreRequest): Response<String> {
        delay(2000)
        return Response(body = "1", StatusSuccess)
    }

    override suspend fun getOwnedStore(): Response<Store> {
        delay(2000)
        return Response(
            body = Store("", "", "", 1.0, 1.0, "", ""),
            status = StatusSuccess
        )
    }
}
