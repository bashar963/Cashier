package app.snapcart.cashier.ui.view.home.widgets

import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import app.snapcart.cashier.ui.theme.BottomBarItemNotSelected
import app.snapcart.cashier.utils.BottomNavItem

@Composable
fun HomeBottomBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.Sales,
        BottomNavItem.Purchases,
        BottomNavItem.Products,
        BottomNavItem.Reports,
        BottomNavItem.Bonus
    )
    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.background
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        repeat(items.size) {
            val item = items[it]
            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(id = item.icon),
                        modifier = Modifier.size(20.dp),
                        contentDescription = stringResource(id = item.title),
                        tint = if (currentRoute == item.screenRoute) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            BottomBarItemNotSelected
                        }
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = item.title),
                        fontSize = 10.sp,
                        maxLines = 1,
                        color = if (currentRoute == item.screenRoute) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            BottomBarItemNotSelected
                        }
                    )
                },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Black.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.screenRoute,
                onClick = {
                    navController.navigate(item.screenRoute) {
                        navController.graph.startDestinationRoute?.let { screenRoute ->
                            popUpTo(screenRoute) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
