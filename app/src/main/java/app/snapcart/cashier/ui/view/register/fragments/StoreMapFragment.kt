package app.snapcart.cashier.ui.view.register.fragments


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import app.snapcart.cashier.R
import app.snapcart.cashier.ui.view.register.RegisterViewModel
import app.snapcart.cashier.ui.widgets.CashierButton
import app.snapcart.cashier.ui.widgets.CashierTextField
import app.snapcart.cashier.ui.widgets.MainAppBar
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun StoreMapFragment(
    owner: ViewModelStoreOwner,
    onBackClicked: ()->Unit,
    onConfirm: ()->Unit,
) {
    val viewModel: RegisterViewModel = ViewModelProvider(owner)[RegisterViewModel::class.java]

    val jakarta  = LatLng(-6.2297465, 106.829518)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(jakarta, 18f)
    }
    Scaffold(
        topBar = {
            MainAppBar(
                labelText = stringResource(id = R.string.confirm_address),
                onBackClicked = onBackClicked
            )
        },
        bottomBar = {
            CashierButton(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .fillMaxWidth(),
                onClick = onConfirm,
            ) {
               Text(text = stringResource(id = R.string.confirm))
            }
        },
    ){ paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
            ,
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
        ){
            Spacer(modifier = Modifier.height(16.dp))
            CashierTextField(
                value = viewModel.searchText,
                onValueChange = { viewModel.searchQuery(it) },
                readOnly = true,
                leadingComposable = {
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                    )
                },
            )
            Spacer(modifier = Modifier.height(32.dp))
            GoogleMap(
                modifier = Modifier
                    .fillMaxHeight(0.8f)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .border(
                        2.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(16.dp)
                    ),
                cameraPositionState = cameraPositionState,
                uiSettings = MapUiSettings(
                    compassEnabled = false,
                    indoorLevelPickerEnabled = false,
                    mapToolbarEnabled = false,
                    myLocationButtonEnabled = false,
                    rotationGesturesEnabled = false,
                    scrollGesturesEnabled= false,
                    scrollGesturesEnabledDuringRotateOrZoom = false,
                    tiltGesturesEnabled = false,
                    zoomControlsEnabled = false,
                    zoomGesturesEnabled= false,
                )
            ) {
                Marker(
                    state = MarkerState(position = jakarta),
                    title = "Jakarta",
                    snippet = viewModel.searchText
                )
            }
        }
    }
}