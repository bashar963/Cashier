package app.snapcart.cashier.ui.view.register

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.snapcart.cashier.R
import app.snapcart.cashier.data.models.places.Address
import app.snapcart.cashier.data.models.store.CreateStoreRequest
import app.snapcart.cashier.data.repo.places.PlacesRepository
import app.snapcart.cashier.data.repo.store.StoreRepository
import app.snapcart.cashier.data.repo.user.UserRepository
import app.snapcart.cashier.utils.CashierStringProvider
import app.snapcart.cashier.utils.Debouncer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URLEncoder
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel
@Inject constructor(
    private val userRepository: UserRepository,
    private val storeRepository: StoreRepository,
    private val pacesRepository: PlacesRepository,
    private val cashierStringProvider: CashierStringProvider
) :
    ViewModel() {

    private val  debouncer = Debouncer(viewModelScope.coroutineContext)

    val fetchedAddresses = mutableListOf<Address>()
    var selectedAddress by mutableStateOf<Address?>(null)
        private set

    // Store data vars
    var fullName by mutableStateOf("")
    var storeName by mutableStateOf("")
    var storeAddress by mutableStateOf("")
    var noteToCourier by mutableStateOf("")
    var province by mutableStateOf("")
    var city by mutableStateOf("")
    var hasImageOutside by mutableStateOf(false)
    var imageUriOutside by mutableStateOf<Uri?>(null)
    var hasImageInside by mutableStateOf(false)
    var imageUriInside by mutableStateOf<Uri?>(null)
    var isEnabledRegisterButton by mutableStateOf(false)

    // find address vars
    var searchText by mutableStateOf("")
        private set

    // / Network vars
    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _registerApiResponse = MutableStateFlow<Result<String>?>(null)
    val registerApiResponse = _registerApiResponse.asStateFlow()

    // Store data validations
    var fullNameError by mutableStateOf("")
    var storeNameError by mutableStateOf("")
    var storeAddressError by mutableStateOf("")
    var noteError by mutableStateOf("")
    var provinceError by mutableStateOf("")
    var cityError by mutableStateOf("")

    fun validateFullName() {
        fullNameError = if (fullName.split(' ').size > 1 && fullName.split(' ')[1].isNotEmpty()) {
            ""
        } else {
            cashierStringProvider.getString(R.string.store_data_full_name_error)
        }
        shouldEnabledRegisterButton()
    }
    fun validateStoreName() {
        storeNameError = if (storeName.isNotEmpty()) {
            ""
        } else {
            cashierStringProvider.getString(R.string.store_data_store_name_error)
        }
        shouldEnabledRegisterButton()
    }

    fun validateStoreAddress() {
        storeAddressError = if (storeAddress.isNotEmpty()) {
            ""
        } else {
            cashierStringProvider.getString(R.string.store_data_store_address_error)
        }
        shouldEnabledRegisterButton()
    }
    fun validateNote() {
        noteError = if (noteToCourier.isNotEmpty()) {
            ""
        } else {
            cashierStringProvider.getString(R.string.store_data_note_error)
        }
        shouldEnabledRegisterButton()
    }

    fun validateProvince() {
        provinceError = if (province.isNotEmpty()) {
            ""
        } else {
            cashierStringProvider.getString(R.string.store_data_province_error)
        }
        shouldEnabledRegisterButton()
    }

    fun validateCity() {
        cityError = if (city.isNotEmpty()) {
            ""
        } else {
            cashierStringProvider.getString(R.string.store_data_city_error)
        }
        shouldEnabledRegisterButton()
    }
    fun shouldEnabledRegisterButton() {
        isEnabledRegisterButton =
            city.isNotEmpty() &&
            province.isNotEmpty() &&
            noteToCourier.isNotEmpty() &&
            storeAddress.isNotEmpty() &&
            storeName.isNotEmpty() &&
            (fullName.split(' ').size > 1 && fullName.split(' ')[1].isNotEmpty()) &&
            imageUriInside != null &&
            imageUriOutside != null
    }


    fun searchQuery(query: String) {
        searchText = query
        debouncer.debounce{
            fetchedAddresses.clear()
            if(query.length <= 1){
                return@debounce
            }
            pacesRepository.fetchAddresses(withContext(Dispatchers.IO) {
                URLEncoder.encode(query, "UTF-8")
            })
                .collect{ data->
                    data.onSuccess { addresses ->
                        fetchedAddresses.addAll(addresses)
                    }
                }
        }
    }

    fun submitAddress() {
        storeAddress = searchText
    }

    fun onAddressSelected(address: Address) {
        selectedAddress = address
    }

    fun register() = viewModelScope.launch {
        val storeRequest = CreateStoreRequest(
            ownerName = fullName,
            storeName = storeName,
            address = storeAddress,
            lat = 1.0, // TODO get from location later
            lng = 1.0, // TODO get from location later
            insidePhoto = imageUriInside.toString(), // TODO ask how to provide the image
            outsidePhoto = imageUriOutside.toString() // TODO ask how to provide the image
        )
        _loading.value = true
        userRepository.createProfile(name = fullName)
            .last().onSuccess {
                storeRepository.createStore(storeRequest)
                    .onSuccess { data ->
                        _registerApiResponse.value = Result.success(data)
                    }
            }
        _loading.value = false
    }


}
