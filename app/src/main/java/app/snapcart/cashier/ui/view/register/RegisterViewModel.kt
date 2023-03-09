package app.snapcart.cashier.ui.view.register

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(): ViewModel() {

    private val addressesMockOptions = listOf("Option 1 Option 1 Option 1 Option 1 Option 1 Option 1 Option 1 Option 1", "Option 2", "Option 3", "Option 4", "Option 5")
    val fetchedAddressesMockOptions = mutableListOf<String>()

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

    // find address vars
    var searchText by mutableStateOf("")
        private set

    fun searchQuery(query:String){
        searchText = query
        fetchedAddressesMockOptions.clear()
        val fetched = addressesMockOptions.filter  {
            it.contains(query)
        }
        fetchedAddressesMockOptions.addAll(fetched)
    }

    fun submitAddress() {
        storeAddress = searchText
    }
}