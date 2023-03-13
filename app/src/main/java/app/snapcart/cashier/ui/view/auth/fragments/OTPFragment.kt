package app.snapcart.cashier.ui.view.auth.fragments

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import app.snapcart.cashier.R
import app.snapcart.cashier.ui.theme.Roboto
import app.snapcart.cashier.ui.view.auth.AuthViewModel
import app.snapcart.cashier.ui.widgets.CashierButton
import app.snapcart.cashier.ui.widgets.OtpView
import java.util.*

@Composable
fun OTPFragment(
    owner: ViewModelStoreOwner,
    onPhoneEdit: () -> Unit,
    onResend: () -> Unit,
    onSubmit: (code: String) -> Unit
) {
    val viewModel: AuthViewModel = ViewModelProvider(owner)[AuthViewModel::class.java]
    val timer = viewModel.countdownTime.collectAsState()
    val otpValue = remember { mutableStateOf("") }
    val otpError = remember { mutableStateOf<String?>(null) }
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .padding(horizontal = 24.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                focusManager.clearFocus(force = true)
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        CashierLogo()
        Spacer(modifier = Modifier.height(16.dp))
        OTPSubTitle()
        Spacer(modifier = Modifier.height(16.dp))
        PhoneNumberView(phoneNumber = viewModel.phoneNumber, onPhoneEdit = onPhoneEdit)
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = stringResource(id = R.string.enter_code).uppercase(Locale.getDefault()),
            style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onPrimary)
        )
        Spacer(modifier = Modifier.height(32.dp))
        OtpViewComposable(otpValue = otpValue, focusManager = focusManager, otpError = otpError)
        Spacer(modifier = Modifier.weight(1.0f))
        CashierButton(
            onClick = {
                onSubmit.invoke(otpValue.value)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onBackground,
                disabledContainerColor = MaterialTheme.colorScheme.tertiary,
                disabledContentColor = MaterialTheme.colorScheme.onTertiary
            )
        ) {
            Text(
                text = stringResource(id = R.string.submit).uppercase(Locale.getDefault()),
                color = MaterialTheme.colorScheme.primary
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        TimeView(timerFinished = viewModel.timerFinished, onResend = onResend, timer = timer.value)
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun CashierLogo() {
    Image(
        painterResource(R.drawable.cachier_logo_white),
        modifier = Modifier.size(width = 220.dp, height = 220.dp),
        contentDescription = "Cashier logo"
    )
}

@Composable
fun TimeView(
    timerFinished: Boolean,
    timer: String,
    onResend: () -> Unit
) {
    if (timerFinished) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onResend.invoke()
                },
            text = stringResource(id = R.string.resend).uppercase(Locale.getDefault()),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
    } else {
        Text(
            text = "${stringResource(id = R.string.resend_code_in)} $timer",
            style = MaterialTheme.typography.bodySmall.copy(
                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = .9f)
            )
        )
    }
}

@Composable
fun OtpViewComposable(
    otpValue: MutableState<String>,
    focusManager: FocusManager,
    otpError: MutableState<String?>
) {
    OtpView(
        otpText = otpValue.value,
        onOtpTextChange = {
            otpValue.value = it
        },
        containerSize = 54.dp,
        keyboardActions = KeyboardActions(onDone = {
            focusManager.clearFocus(force = true)
        }),
        charColor = MaterialTheme.colorScheme.onPrimary
    )
    if (!otpError.value.isNullOrEmpty()) {
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = otpError.value!!,
            style = MaterialTheme.typography.bodySmall.copy(
                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = .9f)
            )
        )
    }
}

@Composable
fun PhoneNumberView(
    phoneNumber: String,
    onPhoneEdit: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = phoneNumber,
            style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.onPrimary)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Icon(
            modifier = Modifier
                .size(20.dp)
                .clickable {
                    onPhoneEdit.invoke()
                },
            tint = MaterialTheme.colorScheme.onPrimary,
            imageVector = Icons.Rounded.Edit,
            contentDescription = "Edit"
        )
    }
}

@Composable
fun OTPSubTitle() {
    Text(
        buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontFamily = Roboto,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Normal
                )
            ) {
                append(stringResource(id = R.string.an))
                withStyle(
                    style = SpanStyle(
                        fontFamily = Roboto,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append(stringResource(id = R.string.sms))
                }
                append(stringResource(id = R.string.with_the))
                withStyle(
                    style = SpanStyle(
                        fontFamily = Roboto,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append(stringResource(id = R.string.verification_code))
                }
                append(stringResource(id = R.string.has_been_sent_to_your_registered))
                withStyle(
                    style = SpanStyle(
                        fontFamily = Roboto,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append(stringResource(id = R.string.phone_number))
                }
            }
        },
        textAlign = TextAlign.Center
    )
}
