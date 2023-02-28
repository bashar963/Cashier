package app.snapcart.cashier.ui.view.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState

import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import app.snapcart.cashier.ui.theme.CashierTheme
import app.snapcart.cashier.ui.view.onboard.OnboardActivity
import app.snapcart.cashier.ui.view.splash.widgets.SplashContent


class EntryActivity : ComponentActivity() {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            if(!viewModel.isLoading.collectAsState().value){
                startActivity(Intent(this,OnboardActivity::class.java))
                finish()
            }
            CashierTheme(isStatusBarVisible = false) {
                SplashContent()
            }
        }
    }
}


@Preview(
    showBackground = true,
    device = Devices.NEXUS_5,
)
@Composable
fun DefaultPreview() {
    CashierTheme {
        SplashContent()
    }
}