package app.snapcart.cashier.ui.view.home.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import app.snapcart.cashier.R
import app.snapcart.cashier.ui.widgets.UserAvatar
import app.snapcart.cashier.utils.DrawerItem

@Composable
fun HomeDrawer(
    onItemClick: (DrawerItem) -> Unit
) {
    val items = listOf(
        DrawerItem.HelpCenter,
        DrawerItem.Settings,
        DrawerItem.LogOut
    )
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        TopSection()
        Spacer(modifier = Modifier.height(8.dp))
        repeat(items.size) {
            val item = items[it]
            DrawerWidget(item) {
                onItemClick.invoke(item)
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painterResource(id = R.drawable.ic_powered_snapcart),
            modifier = Modifier
                .width(160.dp),
            contentDescription = "Powered by SnapCart"
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun TopSection() {
    Box(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.primary)
            .fillMaxWidth()
            .wrapContentHeight()

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            UserAvatar()
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "User store name",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "User full name",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun DrawerWidget(item: DrawerItem, onClick: () -> Unit) {
    Spacer(modifier = Modifier.height(8.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick.invoke()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Spacer(modifier = Modifier.width(16.dp))
        Icon(
            painterResource(id = item.icon),
            modifier = Modifier
                .padding(vertical = 8.dp)
                .size(24.dp),
            contentDescription = stringResource(id = item.title)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = stringResource(id = item.title))
        Spacer(modifier = Modifier.width(16.dp))
    }
    Spacer(modifier = Modifier.height(8.dp))
}
