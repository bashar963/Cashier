package app.snapcart.cashier.ui.view.register.fragments

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import app.snapcart.cashier.R
import app.snapcart.cashier.ui.theme.BoxBackgroundColor
import app.snapcart.cashier.ui.theme.TextFieldPlaceHolderColor
import app.snapcart.cashier.ui.view.register.RegisterViewModel
import app.snapcart.cashier.ui.widgets.CashierButton
import app.snapcart.cashier.ui.widgets.CashierTextField
import app.snapcart.cashier.utils.ComposeFileProvider
import app.snapcart.cashier.ui.widgets.MainAppBar
import coil.compose.AsyncImage
import java.util.Locale

@Composable
fun StoreProfileFragment(
    owner: ViewModelStoreOwner,
    onBackClicked: () -> Unit,
    onAddressClicked: () -> Unit,
    onFinish: () -> Unit
) {
    val viewModel: RegisterViewModel = ViewModelProvider(owner)[RegisterViewModel::class.java]
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    Scaffold(
        topBar = {
            MainAppBar(
                labelText = stringResource(id = R.string.store_profile),
                onBackClicked = onBackClicked
            )
        },
        bottomBar = {
            CashierButton(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp).fillMaxWidth(),
                enabled = viewModel.isEnabledRegisterButton,
                onClick = if (viewModel.loading.collectAsState().value) { {} } else onFinish
            ) {
                if (viewModel.loading.collectAsState().value) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                } else {
                    Text(text = stringResource(id = R.string.finish).uppercase(Locale.getDefault()))
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize()
                .clickable(interactionSource = interactionSource, indication = null) {
                    focusManager.clearFocus(force = true)
                }
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            StoreDetails(viewModel = viewModel, onAddressClicked = onAddressClicked)
            Spacer(modifier = Modifier.height(12.dp))
            AddressSelections(viewModel = viewModel)
            Spacer(modifier = Modifier.height(24.dp))
            Divider()
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.store_take_image_note),
                fontSize = 12.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))
            OutsideView(viewModel = viewModel)
            Spacer(modifier = Modifier.height(24.dp))
            InsideView(viewModel = viewModel)
        }
    }
}

@Composable
fun InsideView(
    viewModel: RegisterViewModel
) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(id = R.string.inside),
        textAlign = TextAlign.Center
    )
    ImagePicker(
        hasImage = viewModel.hasImageInside,
        imageUri = viewModel.imageUriInside,
        onCreateImageFile = {
            viewModel.imageUriInside = it
            viewModel.shouldEnabledRegisterButton()
        },
        onTakePictureResult = { viewModel.hasImageInside = it }
    )
}

@Composable
fun OutsideView(
    viewModel: RegisterViewModel
) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(id = R.string.outside),
        textAlign = TextAlign.Center
    )
    ImagePicker(
        hasImage = viewModel.hasImageOutside,
        imageUri = viewModel.imageUriOutside,
        onCreateImageFile = {
            viewModel.imageUriOutside = it
            viewModel.shouldEnabledRegisterButton()
        },
        onTakePictureResult = { viewModel.hasImageOutside = it }
    )
}

@Composable
fun AddressSelections(
    viewModel: RegisterViewModel
) {
    CashierTextField(
        value = viewModel.province,
        onValueChange = {
            viewModel.province = it
            viewModel.validateProvince()
        },
        showError = viewModel.provinceError.isNotEmpty(),
        errorMessage = viewModel.provinceError,
        isError = viewModel.provinceError.isNotEmpty(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        label = {
            Text(
                text = stringResource(id = R.string.province),
                fontSize = 13.sp,
                color = TextFieldPlaceHolderColor
            )
        }
    )
    Spacer(modifier = Modifier.height(12.dp))
    CashierTextField(
        value = viewModel.city,
        onValueChange = {
            viewModel.city = it
            viewModel.validateCity()
        },
        showError = viewModel.cityError.isNotEmpty(),
        errorMessage = viewModel.cityError,
        isError = viewModel.cityError.isNotEmpty(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        label = {
            Text(
                text = stringResource(id = R.string.city),
                fontSize = 13.sp,
                color = TextFieldPlaceHolderColor
            )
        }
    )
}

@Composable
fun StoreDetails(
    viewModel: RegisterViewModel,
    onAddressClicked: () -> Unit
) {
    FullNameView(viewModel = viewModel)
    Spacer(modifier = Modifier.height(12.dp))
    StoreNameView(viewModel = viewModel)
    Spacer(modifier = Modifier.height(12.dp))
    StoreAddressView(viewModel = viewModel, onAddressClicked = onAddressClicked)
    Spacer(modifier = Modifier.height(12.dp))
    NoteView(viewModel = viewModel)
}

@Composable
fun FullNameView(
    viewModel: RegisterViewModel
) {
    CashierTextField(
        value = viewModel.fullName,
        onValueChange = {
            viewModel.fullName = it
            viewModel.validateFullName()
        },
        showError = viewModel.fullNameError.isNotEmpty(),
        errorMessage = viewModel.fullNameError,
        isError = viewModel.fullNameError.isNotEmpty(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        label = {
            Text(
                text = stringResource(id = R.string.full_name),
                fontSize = 13.sp,
                color = TextFieldPlaceHolderColor
            )
        }
    )
}

@Composable
fun StoreNameView(
    viewModel: RegisterViewModel
) {
    CashierTextField(
        value = viewModel.storeName,
        onValueChange = {
            viewModel.storeName = it
            viewModel.validateStoreName()
        },
        showError = viewModel.storeNameError.isNotEmpty(),
        errorMessage = viewModel.storeNameError,
        isError = viewModel.storeNameError.isNotEmpty(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        label = {
            Text(
                text = stringResource(id = R.string.store_name),
                fontSize = 13.sp,
                color = TextFieldPlaceHolderColor
            )
        }
    )
}

@Composable
fun StoreAddressView(
    viewModel: RegisterViewModel,
    onAddressClicked: () -> Unit
) {
    CashierTextField(
        modifier = Modifier.clickable {
            onAddressClicked.invoke()
        },
        value = viewModel.storeAddress,
        readOnly = true,
        enabled = false,
        onValueChange = {
            viewModel.storeAddress = it
            viewModel.validateStoreAddress()
        },
        showError = viewModel.storeAddressError.isNotEmpty(),
        errorMessage = viewModel.storeAddressError,
        isError = viewModel.storeAddressError.isNotEmpty(),
        singleLine = false,
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        label = {
            Text(
                text = stringResource(id = R.string.store_address),
                fontSize = 13.sp,
                color = TextFieldPlaceHolderColor
            )
        }
    )
}

@Composable
fun NoteView(
    viewModel: RegisterViewModel
) {
    CashierTextField(
        value = viewModel.noteToCourier,
        onValueChange = {
            viewModel.noteToCourier = it
            viewModel.validateNote()
        },
        showError = viewModel.noteError.isNotEmpty(),
        errorMessage = viewModel.noteError,
        isError = viewModel.noteError.isNotEmpty(),
        singleLine = false,
        leadingIcon = {
            Icon(
                painterResource(id = R.drawable.ic_note_add),
                tint = TextFieldPlaceHolderColor,
                contentDescription = ""
            )
        },
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        label = {
            Text(
                text = stringResource(id = R.string.add_note_to_courier),
                fontSize = 13.sp,
                color = TextFieldPlaceHolderColor
            )
        }
    )
}

@Composable
fun ImagePicker(
    modifier: Modifier = Modifier,
    hasImage: Boolean,
    onTakePictureResult: (Boolean) -> Unit,
    onCreateImageFile: (Uri?) -> Unit,
    imageUri: Uri?
) {
    val context = LocalContext.current
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = onTakePictureResult
    )

    Box(
        modifier = modifier
    ) {
        if (hasImage && imageUri != null) {
            AsyncImage(
                model = imageUri,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .padding(horizontal = 8.dp, vertical = 8.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .clickable {
                        val uri = ComposeFileProvider.createImageFile(context)
                        onCreateImageFile.invoke(uri)
                        cameraLauncher.launch(uri)
                    },
                contentDescription = "Selected image"
            )
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .padding(horizontal = 8.dp, vertical = 8.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(BoxBackgroundColor)
                    .clickable {
                        val uri = ComposeFileProvider.createImageFile(context)
                        onCreateImageFile.invoke(uri)
                        cameraLauncher.launch(uri)
                    },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painterResource(id = R.drawable.ic_camera),
                    modifier = Modifier.fillMaxSize(.6f),
                    tint = TextFieldPlaceHolderColor,
                    contentDescription = "Open camera"
                )
            }
        }
    }
}
