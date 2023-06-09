package app.snapcart.cashier.ui.view.auth.fragments

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalMinimumTouchTargetEnforcement
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import app.snapcart.cashier.R
import app.snapcart.cashier.ui.theme.Roboto
import app.snapcart.cashier.ui.theme.TextFieldPlaceHolderColor
import app.snapcart.cashier.ui.view.auth.AuthViewModel
import app.snapcart.cashier.ui.widgets.CashierButton
import app.snapcart.cashier.ui.widgets.CashierTextField
import app.snapcart.cashier.ui.widgets.MainAppBar
import app.snapcart.cashier.utils.Constants
import app.snapcart.cashier.utils.PhoneNumberVisualTransformation
import java.util.*

@Composable
fun LoginFragment(
    owner: ViewModelStoreOwner,
    onBackClicked: () -> Unit,
    onSubmit: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    Scaffold(
        topBar = {
            MainAppBar(
                labelText = stringResource(id = R.string.login_or_register),
                onBackClicked = onBackClicked
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = innerPadding)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    focusManager.clearFocus(force = true)
                }
        ) {
            MainContent(owner = owner, onSubmit = onSubmit)
        }
    }
}

@Composable
fun MainContent(
    owner: ViewModelStoreOwner,
    onSubmit: () -> Unit
) {
    val viewModel: AuthViewModel = ViewModelProvider(owner)[AuthViewModel::class.java]
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        MainSubTitle()
        Spacer(modifier = Modifier.height(24.dp))
        PhoneNumberField(viewModel)
        Spacer(modifier = Modifier.weight(1.0f))
        TermsCheckBox(viewModel)
        Spacer(modifier = Modifier.height(16.dp))
        CashierButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = if (viewModel.loginLoading) { {} } else onSubmit,
            enabled = viewModel.tAndCAccepted && viewModel.isValidPhoneNumber == true
        ) {
            if (viewModel.loginLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            } else {
                Text(text = stringResource(id = R.string.continue_text).uppercase(Locale.getDefault()))
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun PhoneNumberField(viewModel: AuthViewModel) {
    val focusManager = LocalFocusManager.current
    val clearButton: @Composable (() -> Unit)? =
        if (viewModel.phoneNumber.isNotEmpty()) {
            {
                IconButton(
                    onClick = { viewModel.setNumber("") }
                ) {
                    Icon(imageVector = Icons.Default.Clear, contentDescription = "clear")
                }
            }
        } else {
            null
        }

    CashierTextField(
        leadingComposable = {
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                painterResource(R.drawable.indonesia_flag_icon),
                modifier = Modifier
                    .size(24.dp)
                    .clip(RoundedCornerShape(6.dp)),
                contentDescription = "indonesia_flag",
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = Constants.ID_PHONE_CODE,
                color = TextFieldPlaceHolderColor
            )
        },
        placeholder = {
            Text(
                text = Constants.ID_PHONE_MASK,
                color = TextFieldPlaceHolderColor
            )
        },
        visualTransformation = PhoneNumberVisualTransformation(),
        singleLine = true,
        isError = viewModel.isValidPhoneNumber == false,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Phone,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus(true) }
        ),
        value = viewModel.phoneNumber,
        onValueChange = { viewModel.setNumber(it) },
        trailingIcon = clearButton,
        showError = viewModel.isValidPhoneNumber == false,
        errorMessage = stringResource(id = R.string.invalid_phone_number)
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun TermsCheckBox(viewModel: AuthViewModel) {
    Row(
        modifier = Modifier
            .padding(0.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
            Checkbox(
                checked = viewModel.tAndCAccepted,
                onCheckedChange = {
                    viewModel.setTAndC(it)
                }
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        FlowRow(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.by_registering_agreed_to),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = stringResource(id = R.string.terms_onditions),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = stringResource(id = R.string.as_well_as),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = stringResource(id = R.string.privacy_policy),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = stringResource(id = R.string.defined_by_snapcart),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
fun MainSubTitle() {
    Text(
        buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontFamily = Roboto,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Medium
                )
            ) {
                append(stringResource(id = R.string.login))
            }
            withStyle(
                style = SpanStyle(
                    fontFamily = Roboto,
                    fontSize = 14.sp,
                    color = Color(0xFF9B9B9B),
                    fontWeight = FontWeight.Normal
                )
            ) {
                append(stringResource(id = R.string.or))
            }
            withStyle(
                style = SpanStyle(
                    fontFamily = Roboto,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Medium
                )
            ) {
                append(stringResource(id = R.string.register))
            }
            withStyle(
                style = SpanStyle(
                    fontFamily = Roboto,
                    fontSize = 14.sp,
                    color = Color(0xFF9B9B9B),
                    fontWeight = FontWeight.Normal
                )
            ) {
                append(stringResource(id = R.string.simply_by_using_your_phone_number))
            }
        }
    )
}
