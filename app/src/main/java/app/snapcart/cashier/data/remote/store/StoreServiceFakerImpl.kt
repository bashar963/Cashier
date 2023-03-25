package app.snapcart.cashier.data.remote.store

import app.snapcart.cashier.data.models.store.Store
import app.snapcart.cashier.data.models.store.CreateStoreRequest
import kotlinx.coroutines.delay

class StoreServiceFakerImpl :
    StoreService {

    override suspend fun createStore(request: CreateStoreRequest): Result<String> {
        delay(2000)
        return Result.success("1")
    }

    override suspend fun getOwnedStore(): Result<Store> {
        delay(2000)
        return Result.success(
            Store("", "", "", 1.0, 1.0, "", "")
        )
    }
}
