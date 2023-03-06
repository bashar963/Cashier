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

}