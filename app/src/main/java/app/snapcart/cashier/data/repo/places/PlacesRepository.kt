package app.snapcart.cashier.data.repo.places

import app.snapcart.cashier.data.models.places.Address
import app.snapcart.cashier.data.remote.places.PlacesServiceHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PlacesRepository @Inject constructor(private val placesServiceHelper: PlacesServiceHelper) {


    fun fetchAddresses(query:String): Flow<Result<List<Address>>> {
        return flow {
            val result = placesServiceHelper.fetchAddresses(query = query)

            // in case of success cache access token
            if (result.isSuccess) {
                // do something with it maybe
                result.apply {
                }
            }

            emit(result)
        }.flowOn(Dispatchers.IO)
    }

}