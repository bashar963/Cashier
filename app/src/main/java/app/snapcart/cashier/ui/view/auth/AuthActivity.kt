package app.snapcart.cashier.ui.view.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.snapcart.cashier.ui.theme.CashierTheme
import app.snapcart.cashier.ui.view.auth.fragments.LoginFragment
import app.snapcart.cashier.ui.view.auth.fragments.OTPFragment
import app.snapcart.cashier.ui.view.register.RegisterActivity
import app.snapcart.cashier.utils.ApiSuccess

import app.snapcart.cashier.utils.AuthScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : ComponentActivity() {

    private val viewModel: AuthViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            LaunchedEffect(key1 = viewModel.loginApiResponse.collectAsState().value) {
                if (viewModel.loginApiResponse.value is ApiSuccess) {
                    navController.navigate(route = AuthScreen.OTPScreen.route)
                    // just demo this function should be run privately
                    viewModel.startTimer(30)
                }
            }
            LaunchedEffect(key1 = viewModel.verifyOTPApiResponse.collectAsState().value) {
                if (viewModel.verifyOTPApiResponse.value is ApiSuccess) {
                    startActivity(Intent(this@AuthActivity, RegisterActivity::class.java))
                }
            }

            CashierTheme {
                NavHost(
                    navController = navController,
                    startDestination = AuthScreen.MainScreen.route
                ) {
                    composable(route = AuthScreen.MainScreen.route) {
                        LoginFragment(
                            this@AuthActivity,
                            onBackClicked = { finish() }
                        ) {
                            viewModel.submitPhoneNumber()
                        }
                    }
                    composable(route = AuthScreen.OTPScreen.route) {
                        OTPFragment(
                            this@AuthActivity,
                            onPhoneEdit = { navController.popBackStack() },
                            onResend = {
                                viewModel.resend()
                            },
                            onSubmit = { otp ->
                                viewModel.submitOTP(otp = otp)
                            }
                        )
                    }
                }
            }
        }
    }
}
