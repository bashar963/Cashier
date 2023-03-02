package app.snapcart.cashier.ui.view.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber.CountryCodeSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(): ViewModel()  {

    var phoneNumber by mutableStateOf("")
         private set
    var isValidPhoneNumber by mutableStateOf<Boolean?>(null)
        private set
    var tAndCAccepted by mutableStateOf(false)
         private set
    var timerFinished by mutableStateOf(false)
        private set

    private val phoneNumberUtil: PhoneNumberUtil = PhoneNumberUtil.getInstance()

    private val _countdownTime = MutableStateFlow("")
    val countdownTime = _countdownTime.asStateFlow()


     fun startTimer(seconds:Int) {
        viewModelScope.launch {
            timerFinished = false
            val timer = (seconds downTo 0)
                .asSequence()
                .asFlow()
                .onEach { delay(1_000) }


            timer.collect {
                timerFinished = it == 0
                val formatted = "${(it / 60).toString().padStart(2, '0')}:${(it % 60).toString().padStart(2, '0')}"
                _countdownTime.value = formatted
            }
        }

    }

    fun setNumber(newPhoneNumber :String){
        phoneNumber = newPhoneNumber



        isValidPhoneNumber = try {
            val phone: PhoneNumber = phoneNumberUtil.parse(
                "+62${newPhoneNumber}",
                CountryCodeSource.UNSPECIFIED.name,
            )
            phoneNumberUtil.isValidNumberForRegion(phone,"ID")
        } catch (_:Exception){
            false
        }

    }
    fun setTAndC(accepted:Boolean){
        tAndCAccepted= accepted
    }

}