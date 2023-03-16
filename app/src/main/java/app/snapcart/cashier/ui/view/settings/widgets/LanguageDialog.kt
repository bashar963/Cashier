package app.snapcart.cashier.ui.view.settings.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import app.snapcart.cashier.R
import app.snapcart.cashier.utils.Language
import app.snapcart.cashier.utils.LocaleUtils

@Composable
fun LanguageDialog(
    onDismissRequest: () -> Unit
) {
    val currentLocale = Locale.current
    val context = LocalContext.current
    AlertDialog(
        onDismissRequest = onDismissRequest,
        buttons = {},
        title = {
            Text(
                text = stringResource(id = R.string.language_dialog_select_language),
                modifier = Modifier.padding(horizontal = 16.dp),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                enumValues<Language>().forEach {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = currentLocale.language == it.language,
                            onClick = {
                                LocaleUtils.setLocale(context = context, it.language)
                                onDismissRequest.invoke()
                            }
                        )
                        Text(
                            text = stringResource(id = it.languageName),
                            color = MaterialTheme.colorScheme.onBackground,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    )
}
