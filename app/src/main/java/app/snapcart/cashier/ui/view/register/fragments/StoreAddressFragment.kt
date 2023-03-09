package app.snapcart.cashier.ui.view.register.fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material3.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import app.snapcart.cashier.R
import app.snapcart.cashier.ui.view.register.RegisterViewModel
import app.snapcart.cashier.ui.widgets.CashierTextField
import app.snapcart.cashier.ui.widgets.MainAppBar

@Composable
fun StoreAddressFragment(
    owner: ViewModelStoreOwner,
    onBackClicked: () -> Unit,
    onAddressSelected: (String) -> Unit
) {
    val viewModel: RegisterViewModel = ViewModelProvider(owner)[RegisterViewModel::class.java]
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    Scaffold(
        topBar = {
            MainAppBar(
                labelText = stringResource(id = R.string.find_address),
                onBackClicked = onBackClicked
            )
        }
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
                .padding(paddingValues),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            CashierTextField(
                modifier = Modifier.padding(horizontal = 16.dp),
                value = viewModel.searchText,
                onValueChange = { viewModel.searchQuery(it) },
                label = {
                    Text(text = stringResource(id = R.string.find_address))
                },
                leadingComposable = {
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Divider(thickness = 4.dp)
            LazyColumn {
                items(viewModel.fetchedAddressesMockOptions.size) {
                    AddressItem(address = viewModel.fetchedAddressesMockOptions[it], onAddressSelected = { address ->
                        viewModel.searchQuery(address)
                        onAddressSelected.invoke(address)
                    })
                }
            }
        }
    }
}

@Composable
fun AddressItem(
    address: String,
    onAddressSelected: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .clickable {
                onAddressSelected.invoke(address)
            }
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
        ) {
            Icon(
                painterResource(id = R.drawable.ic_location),
                contentDescription = "location",
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .padding(6.dp)
                    .size(20.dp)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = address,
                fontSize = 15.sp
            )
            Spacer(modifier = Modifier.height(12.dp))
            Divider()
        }
    }
}
