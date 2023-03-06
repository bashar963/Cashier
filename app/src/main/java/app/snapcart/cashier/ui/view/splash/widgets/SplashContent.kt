package app.snapcart.cashier.ui.view.splash.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import app.snapcart.cashier.R


@Composable
fun SplashContent(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SplashBackground),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1.0f))
        Image(
            painterResource(R.drawable.cashier_logo),
            modifier = Modifier.padding(horizontal = 64.dp),
            contentDescription = "Cashier logo",
            contentScale = ContentScale.FillWidth,
        )
        Spacer(modifier = Modifier.weight(1.0f))
        Text(
            text = "v1.0",
            style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.primary),
        )
        Spacer(modifier = Modifier.height(32.dp))
    }
}