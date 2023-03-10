package app.snapcart.cashier.ui.view.settings

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.snapcart.cashier.BuildConfig
import app.snapcart.cashier.R
import app.snapcart.cashier.ui.theme.CashierTheme
import app.snapcart.cashier.ui.view.settings.widgets.ItemSetting
import app.snapcart.cashier.ui.view.settings.widgets.LanguageDialog
import app.snapcart.cashier.ui.view.settings.widgets.SettingSection
import app.snapcart.cashier.ui.view.settings.widgets.SwitchSetting
import app.snapcart.cashier.ui.widgets.MainAppBar

class SettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CashierTheme {
                SettingsContent {
                    finish()
                }
            }
        }
    }
}

@Composable
fun SettingsContent(
    onBackClick: () -> Unit
) {
    var notificationSwitch by remember { mutableStateOf(true) }
    var languageDialog by remember { mutableStateOf(false) }

    if (languageDialog) {
        LanguageDialog {
            languageDialog = false
        }
    }

    Scaffold(
        topBar = {
            MainAppBar(
                labelText = stringResource(id = R.string.drawer_title_settings),
                onBackClicked = onBackClick
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            SettingSection(title = stringResource(id = R.string.settings_title_general)) {
                SwitchSetting(
                    title = stringResource(id = R.string.settings_title_notifications),
                    checked = notificationSwitch,
                    onCheckedChange = {
                        notificationSwitch = it
                    }
                )
                ItemSetting(title = stringResource(id = R.string.settings_title_language)) {
                    languageDialog = true
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Divider()
            Spacer(modifier = Modifier.height(8.dp))
            SettingSection(title = stringResource(id = R.string.settings_title_about)) {
                ItemSetting(title = "${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})") {
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsPreview() {
    CashierTheme {
        SettingsContent {
        }
    }
}
