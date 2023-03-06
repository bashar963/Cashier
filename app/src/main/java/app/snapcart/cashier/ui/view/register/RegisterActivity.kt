package app.snapcart.cashier.ui.view.register

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.snapcart.cashier.ui.theme.CashierTheme
import app.snapcart.cashier.ui.view.register.fragments.StoreAddressFragment
import app.snapcart.cashier.ui.view.register.fragments.StoreMapFragment
import app.snapcart.cashier.ui.view.register.fragments.StoreProfileFragment
import app.snapcart.cashier.ui.view.register.fragments.SuccessFragment
import app.snapcart.cashier.utils.RegisterScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity: ComponentActivity() {

    private val viewModel: RegisterViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            CashierTheme{
                NavHost(
                    navController = navController,
                    startDestination = RegisterScreen.SuccessScreen.route,
                ){
                    composable(route = RegisterScreen.SuccessScreen.route){
                        SuccessFragment{
                            navController.navigate(RegisterScreen.StoreProfileScreen.route)
                        }
                    }
                    composable(route = RegisterScreen.StoreProfileScreen.route){
                        StoreProfileFragment(this@RegisterActivity,{
                            navController.popBackStack()
                        }){

                        }
                    }
                    composable(route = RegisterScreen.StoreAddressScreen.route){
                        StoreAddressFragment()
                    }
                    composable(route = RegisterScreen.StoreMapScreen.route){
                        StoreMapFragment()
                    }
                }
            }
        }
    }
}