package app.snapcart.cashier.ui.view.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.snapcart.cashier.ui.theme.CashierTheme
import app.snapcart.cashier.ui.view.home.fragments.BonusFragment
import app.snapcart.cashier.ui.view.home.fragments.ProductsFragment
import app.snapcart.cashier.ui.view.home.fragments.PurchasesFragment
import app.snapcart.cashier.ui.view.home.fragments.ReportsFragment
import app.snapcart.cashier.ui.view.home.fragments.SalesFragment
import app.snapcart.cashier.ui.view.home.widgets.HomeAppBar
import app.snapcart.cashier.ui.view.home.widgets.HomeBottomBar
import app.snapcart.cashier.ui.view.home.widgets.HomeDrawer
import app.snapcart.cashier.ui.view.settings.SettingsActivity
import app.snapcart.cashier.utils.DrawerItem
import app.snapcart.cashier.utils.HomeScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val scaffoldState = rememberScaffoldState()
            val coroutineScope = rememberCoroutineScope()
            CashierTheme {
                Scaffold(
                    scaffoldState = scaffoldState,
                    drawerGesturesEnabled = true,
                    topBar = {
                        HomeAppBar {
                            coroutineScope.launch { scaffoldState.drawerState.open() }
                        }
                    },
                    bottomBar = { HomeBottomBar(navController) },
                    drawerContent = {
                        HomeDrawer {
                            coroutineScope.launch { scaffoldState.drawerState.close() }
                            when (it) {
                                DrawerItem.HelpCenter -> {}
                                DrawerItem.LogOut -> {}
                                DrawerItem.Settings -> {
                                    startActivity(Intent(this@HomeActivity, SettingsActivity::class.java))
                                }
                            }
                        }
                    }
                ) { paddingValues ->
                    Content(
                        paddingValues = paddingValues,
                        navController = navController
                    )
                }
            }
        }
    }

    @Composable
    fun Content(
        paddingValues: PaddingValues,
        navController: NavHostController
    ) {
        Surface(modifier = Modifier.padding(paddingValues)) {
            NavHost(
                navController = navController,
                startDestination = HomeScreen.SalesScreen.route
            ) {
                composable(route = HomeScreen.SalesScreen.route) {
                    SalesFragment()
                }
                composable(route = HomeScreen.PurchasesScreen.route) {
                    PurchasesFragment()
                }
                composable(route = HomeScreen.ProductsScreen.route) {
                    ProductsFragment()
                }
                composable(route = HomeScreen.ReportsScreen.route) {
                    ReportsFragment()
                }
                composable(route = HomeScreen.BonusScreen.route) {
                    BonusFragment()
                }
            }
        }
    }

    @Preview(
        showBackground = true,
        device = Devices.NEXUS_5
    )
    @Composable
    fun HomeActivityPreview() {
        val navController = rememberNavController()
        val scaffoldState = rememberScaffoldState()
        val coroutineScope = rememberCoroutineScope()
        CashierTheme {
            Scaffold(
                scaffoldState = scaffoldState,
                drawerGesturesEnabled = true,
                topBar = {
                    HomeAppBar {
                        coroutineScope.launch { scaffoldState.drawerState.open() }
                    }
                },
                bottomBar = { HomeBottomBar(navController) },
                drawerContent = { HomeDrawer {} }
            ) { paddingValues ->
                Content(paddingValues, navController)
            }
        }
    }
}
