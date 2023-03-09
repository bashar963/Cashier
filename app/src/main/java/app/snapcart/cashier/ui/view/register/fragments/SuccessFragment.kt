package app.snapcart.cashier.ui.view.register.fragments

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.snapcart.cashier.R
import app.snapcart.cashier.ui.widgets.CashierButton
import java.util.*

@Composable
fun SuccessFragment(onContinue: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.weight(0.4f))
        Image(
            painterResource(R.drawable.check_mark_circle_red),
            modifier = Modifier.size(width = 210.dp, height = 210.dp),
            contentDescription = "Check mark",
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(id = R.string.registration_successful),
            color = MaterialTheme.colorScheme.secondary,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = stringResource(id = R.string.registration_successful_subtitle),
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.weight(1f))
        CashierButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = onContinue,
        ) {
            Text(
                text = stringResource(id = R.string.fill_in_store_profile).uppercase(Locale.getDefault()),
                textAlign = TextAlign.Center,
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}