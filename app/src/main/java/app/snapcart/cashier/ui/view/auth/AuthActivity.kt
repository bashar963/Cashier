package app.snapcart.cashier.ui.view.auth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.snapcart.cashier.ui.theme.CashierTheme
import app.snapcart.cashier.ui.view.auth.fragments.MainFragment
import app.snapcart.cashier.ui.view.auth.fragments.OTPFragment

import app.snapcart.cashier.utils.AuthScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : ComponentActivity() {

    private val viewModel: AuthViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            CashierTheme {
                NavHost(
                    navController = navController,
                    startDestination = AuthScreen.MainScreen.route
                )
                {
                    composable(route = AuthScreen.MainScreen.route){
                        MainFragment(
                            this@AuthActivity,
                            onBackClicked = { finish() },
                        ){
                            navController.navigate(route = AuthScreen.OTPScreen.route)
                            // just demo this function should be run privately
                            viewModel.startTimer(30)
                        }
                    }
                    composable(route = AuthScreen.OTPScreen.route){
                        OTPFragment(
                            this@AuthActivity,
                            onPhoneEdit = { navController.popBackStack() },
                            onResend = {},
                            onSubmit = {},
                            )
                    }
                }
            }
        }
    }

}