package app.snapcart.cashier.ui.view.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.snapcart.cashier.data.models.auth.OtpResponse
import app.snapcart.cashier.data.models.auth.VerifyOtpResponse
import app.snapcart.cashier.data.repo.auth.AuthRepository
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber.CountryCodeSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val phoneNumberUtil: PhoneNumberUtil
) : ViewModel() {

    var phoneNumber by mutableStateOf("")
        private set
    var isValidPhoneNumber by mutableStateOf<Boolean?>(null)
        private set
    var tAndCAccepted by mutableStateOf(false)
        private set
    var timerFinished by mutableStateOf(false)
        private set
    var loginLoading by mutableStateOf(false)
        private set
    var verifyOTPLoading by mutableStateOf(false)
        private set
    var otpLength by mutableStateOf(4)
        private set

    private val _countdownTime = MutableStateFlow("")
    val countdownTime = _countdownTime.asStateFlow()

    private val _loginApiResponse = MutableStateFlow<Result<OtpResponse>?>(null)
    val loginApiResponse = _loginApiResponse.asStateFlow()

    private val _verifyOTPApiResponse = MutableStateFlow<Result<VerifyOtpResponse>?>(null)
    val verifyOTPApiResponse = _verifyOTPApiResponse.asStateFlow()

    private fun startTimer(seconds: Long) {
        viewModelScope.launch {
            timerFinished = false
            val timer = (seconds downTo 0)
                .asSequence()
                .asFlow()
                .onEach { delay(1_000) }

            timer.collect {
                timerFinished = it == 0L
                val formatted = "${(it / 60).toString().padStart(2, '0')}:${(it % 60).toString().padStart(2, '0')}"
                _countdownTime.value = formatted
            }
        }
    }

    fun setNumber(newPhoneNumber: String) {
        phoneNumber = newPhoneNumber
        isValidPhoneNumber = try {
            val phone: PhoneNumber = phoneNumberUtil.parse(
                "+62$newPhoneNumber",
                CountryCodeSource.UNSPECIFIED.name
            )
            phoneNumberUtil.isValidNumberForRegion(phone, "ID")
        } catch (_: Exception) {
            false
        }
    }

    fun setTAndC(accepted: Boolean) {
        tAndCAccepted = accepted
    }

    fun submitPhoneNumber() {
        viewModelScope.launch {
            loginLoading = true
            val response = authRepository.getOtp(phoneNumber)
            response.onSuccess { data ->
                val seconds = data.retryAtSeconds
                startTimer(seconds)
                otpLength = data.otpLength
                _loginApiResponse.value = response
            }.onFailure {
                // TODO
            }
            loginLoading = false
        }
    }

    fun submitOTP(otp: String) {
        viewModelScope.launch {
            verifyOTPLoading = true
            _verifyOTPApiResponse.value = authRepository.verifyOTP(otp).last()
            verifyOTPLoading = false
        }
    }

    fun resend() {
        viewModelScope.launch {
            verifyOTPLoading = true
            val response = authRepository.getOtp(phoneNumber)
            verifyOTPLoading = false
            response.onSuccess { data ->
                val seconds = data.retryAtSeconds
                otpLength = data.otpLength
                startTimer(seconds = seconds)
            }.onFailure {
                // TODO something
            }
        }
    }
}
