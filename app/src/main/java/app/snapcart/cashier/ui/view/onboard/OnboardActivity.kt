package app.snapcart.cashier.ui.view.onboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import app.snapcart.cashier.ui.theme.CashierTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
            .apply {
                setKeepOnScreenCondition{
                    return@setKeepOnScreenCondition false
                }
                setOnExitAnimationListener{
                    it.remove()

                }
            }

        setContent{
            CashierTheme {
                Surface {

                }
            }
        }
    }
}