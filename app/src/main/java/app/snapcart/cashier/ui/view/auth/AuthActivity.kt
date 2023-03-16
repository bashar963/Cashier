package app.snapcart.cashier.ui.view.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.snapcart.cashier.ui.theme.CashierTheme
import app.snapcart.cashier.ui.view.auth.fragments.LoginFragment
import app.snapcart.cashier.ui.view.auth.fragments.OTPFragment
import app.snapcart.cashier.ui.view.register.RegisterActivity
import app.snapcart.cashier.utils.ApiError
import app.snapcart.cashier.utils.ApiException
import app.snapcart.cashier.utils.ApiIdle
import app.snapcart.cashier.utils.ApiLoading
import app.snapcart.cashier.utils.ApiSuccess

import app.snapcart.cashier.utils.AuthScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : ComponentActivity() {

    private val viewModel: AuthViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            when (viewModel.apiResponse.collectAsState().value) {
                is ApiError -> {}
                is ApiException -> {}
                is ApiIdle -> {}
                is ApiLoading -> {}
                is ApiSuccess -> {}
            }
            val navController = rememberNavController()
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
                            navController.navigate(route = AuthScreen.OTPScreen.route)
                            // just demo this function should be run privately
                            viewModel.startTimer(30)
                        }
                    }
                    composable(route = AuthScreen.OTPScreen.route) {
                        OTPFragment(
                            this@AuthActivity,
                            onPhoneEdit = { navController.popBackStack() },
                            onResend = { },
                            onSubmit = {
                                startActivity(Intent(this@AuthActivity, RegisterActivity::class.java))
                            }
                        )
                    }
                }
            }
        }
    }
}
