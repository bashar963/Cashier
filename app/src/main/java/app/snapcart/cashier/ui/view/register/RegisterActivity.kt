package app.snapcart.cashier.ui.view.register

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.snapcart.cashier.ui.theme.CashierTheme
import app.snapcart.cashier.ui.view.home.HomeActivity
import app.snapcart.cashier.ui.view.register.fragments.StoreAddressFragment
import app.snapcart.cashier.ui.view.register.fragments.StoreMapFragment
import app.snapcart.cashier.ui.view.register.fragments.StoreProfileFragment
import app.snapcart.cashier.ui.view.register.fragments.SuccessFragment
import app.snapcart.cashier.utils.RegisterScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : ComponentActivity() {

    private val viewModel: RegisterViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            CashierTheme {
                NavHost(
                    navController = navController,
                    startDestination = RegisterScreen.SuccessScreen.route
                ) {
                    composable(route = RegisterScreen.SuccessScreen.route) {
                        SuccessFragment {
                            navController.navigate(RegisterScreen.StoreProfileScreen.route)
                        }
                    }
                    composable(route = RegisterScreen.StoreProfileScreen.route) {
                        StoreProfileFragment(
                            owner = this@RegisterActivity,
                            onBackClicked = { navController.popBackStack() },
                            onAddressClicked = { navController.navigate(RegisterScreen.StoreAddressScreen.route) }
                        ) {
                            val intent = Intent(this@RegisterActivity, HomeActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)
                        }
                    }
                    composable(route = RegisterScreen.StoreAddressScreen.route) {
                        StoreAddressFragment(
                            owner = this@RegisterActivity,
                            onBackClicked = { navController.popBackStack() }
                        ) {
                            navController.navigate(RegisterScreen.StoreMapScreen.route)
                        }
                    }
                    composable(route = RegisterScreen.StoreMapScreen.route) {
                        StoreMapFragment(
                            owner = this@RegisterActivity,
                            onBackClicked = { navController.popBackStack() }
                        ) {
                            viewModel.submitAddress()
                            navController.popBackStack(
                                route = RegisterScreen.StoreProfileScreen.route,
                                inclusive = false,
                                saveState = false
                            )
                        }
                    }
                }
            }
        }
    }
}
