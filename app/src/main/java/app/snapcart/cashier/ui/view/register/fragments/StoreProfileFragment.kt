package app.snapcart.cashier.ui.view.register.fragments


import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
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
import app.snapcart.cashier.utils.CashierDropDownTextField
import app.snapcart.cashier.utils.CashierTextField
import app.snapcart.cashier.utils.ComposeFileProvider
import app.snapcart.cashier.utils.MainAppBar
import coil.compose.AsyncImage


@Composable
fun StoreProfileFragment(
    owner: ViewModelStoreOwner,
    onBackClicked: ()->Unit,
    onFinish: ()->Unit,
) {
    val viewModel: RegisterViewModel = ViewModelProvider(owner)[RegisterViewModel::class.java]
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    val options = listOf("Option 1", "Option 2", "Option 3", "Option 4", "Option 5")
    var expandedProvince by remember { mutableStateOf(false) }
    var expandedCity by remember { mutableStateOf(false) }


    Scaffold(
        topBar = {
            MainAppBar(
                labelText=  stringResource(id = R.string.store_profile),
                onBackClicked = onBackClicked
            )
        },
        bottomBar = {
                    CashierButton(
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 12.dp)
                            .fillMaxWidth(),
                        onClick = onFinish,
                    ) {
                        Text(text = stringResource(id = R.string.finish))
                    }
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    focusManager.clearFocus(force = true)
                }
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            CashierTextField(
                value = viewModel.fullName,
                onValueChange = {
                    viewModel.fullName = it
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next,
                ),
                label = {
                        Text(
                            text = stringResource(id = R.string.full_name),
                            fontSize = 13.sp,
                            color = TextFieldPlaceHolderColor,
                        )
                },
            )
            Spacer(modifier = Modifier.height(12.dp))
            CashierTextField(
                value = viewModel.storeName,
                onValueChange = {
                    viewModel.storeName = it
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next,
                ),
                label = {
                    Text(
                        text = stringResource(id = R.string.store_name),
                        fontSize = 13.sp,
                        color = TextFieldPlaceHolderColor,
                    )
                },
            )
            Spacer(modifier = Modifier.height(12.dp))
            CashierTextField(
                value = viewModel.storeAddress,
                onValueChange = {
                    viewModel.storeAddress = it
                },
                singleLine = false,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next,
                ),
                label = {
                    Text(
                        text = stringResource(id = R.string.store_address),
                        fontSize = 13.sp,
                        color = TextFieldPlaceHolderColor,
                    )
                },
            )
            Spacer(modifier = Modifier.height(12.dp))
            CashierTextField(
                value = viewModel.noteToCourier,
                onValueChange = {
                    viewModel.noteToCourier = it
                },
                singleLine = false,
                leadingIcon = {
                    Icon(
                        painterResource(id = R.drawable.ic_note_add),
                        tint = TextFieldPlaceHolderColor,
                        contentDescription = "",)
                },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next,
                ),
                label = {
                    Text(
                        text = stringResource(id = R.string.add_note_to_courier),
                        fontSize = 13.sp,
                        color = TextFieldPlaceHolderColor,
                    )
                },
            )
            Spacer(modifier = Modifier.height(12.dp))
            CashierDropDownTextField(
                value = viewModel.province,
                labelText = stringResource(id = R.string.province),
                onValueSelected = {
                    viewModel.province =it
                    expandedProvince =false
                },
                expanded = expandedProvince,
                onExpandedChange = { expandedProvince = !expandedProvince },
                onDismissRequest = { expandedProvince = false },
                options = options,
            )
            Spacer(modifier = Modifier.height(12.dp))
            CashierDropDownTextField(
                value = viewModel.city,
                labelText = stringResource(id = R.string.city),
                onValueSelected = {
                    viewModel.city =it
                    expandedCity =false
                },
                expanded = expandedCity,
                onExpandedChange = { expandedCity = !expandedCity },
                onDismissRequest = { expandedCity = false },
                options = options,
            )
            Spacer(modifier = Modifier.height(24.dp))
            Divider()
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.store_take_image_note),
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.outside),
                textAlign = TextAlign.Center,
            )
            ImagePicker(
                hasImage = viewModel.hasImageOutside,
                imageUri = viewModel.imageUriOutside,
                onCreateImageFile = { viewModel.imageUriOutside = it },
                onTakePictureResult = { viewModel.hasImageOutside =it },
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.inside),
                textAlign = TextAlign.Center,
            )
            ImagePicker(
                hasImage = viewModel.hasImageInside,
                imageUri = viewModel.imageUriInside,
                onCreateImageFile = { viewModel.imageUriInside = it },
                onTakePictureResult = { viewModel.hasImageInside =it },
            )
        }
    }
}

@Composable
fun ImagePicker(
    modifier: Modifier = Modifier,
    hasImage: Boolean ,
    onTakePictureResult: (Boolean) -> Unit,
    onCreateImageFile: (Uri?) -> Unit,
    imageUri: Uri?,
){
    val context = LocalContext.current

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = onTakePictureResult,
    )

    Box(
        modifier = modifier,
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
                contentDescription = "Selected image",
            )
        }
        else{
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
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(
                    painterResource(id = R.drawable.ic_camera),
                    modifier= Modifier.fillMaxSize(.6f),
                    tint = TextFieldPlaceHolderColor,
                    contentDescription = "Open camera",)
            }
        }

    }
}

//@Preview(
//    showBackground = true,
//    device = Devices.NEXUS_5,
//)
//@Composable
//fun StoreProfileFragmentPreview() {
//    CashierTheme {
//        StoreProfileFragment({}){
//
//        }
//    }
//}