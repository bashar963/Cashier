package app.snapcart.cashier.ui.view.onboard

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import app.snapcart.cashier.ui.theme.CashierTheme
import app.snapcart.cashier.ui.view.auth.AuthActivity
import app.snapcart.cashier.ui.view.onboard.widgets.OnboardContent

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CashierTheme {
                OnboardContent{
                    startActivity(Intent(this,AuthActivity::class.java))
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    device = Devices.NEXUS_5
)
@Composable
fun DefaultPreview() {
    CashierTheme {
        OnboardContent{}
    }
}
