package app.snapcart.cashier.data.remote.places

import app.snapcart.cashier.data.models.places.Address
import app.snapcart.cashier.utils.toMap
import com.google.gson.Gson
import org.json.JSONObject


import javax.inject.Inject

class PlacesServiceImpl
@Inject constructor(private val placesService: PlacesService) :
    PlacesServiceHelper {

    override suspend fun fetchAddresses(query: String): Result<List<Address>> {

        val addresses = mutableListOf<Address>()
        val response = placesService.fetchAddresses(query = query)
        if(response.isSuccessful){
            val data =  JSONObject(Gson().toJson(response.body())).toMap()

            if(!data.containsKey("error")){

            }
        }

        return  Result.success(addresses)
    }
}