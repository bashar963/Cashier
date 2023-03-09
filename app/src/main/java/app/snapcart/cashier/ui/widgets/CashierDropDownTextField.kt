package app.snapcart.cashier.ui.widgets

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import app.snapcart.cashier.ui.theme.TextFieldPlaceHolderColor

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CashierDropDownTextField(
    value:String,
    labelText:String,
    onValueSelected:(String) -> Unit,
    expanded:Boolean,
    onExpandedChange: (Boolean) -> Unit ,
    onDismissRequest: () -> Unit ,
    options: List<String> ,
) {
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = onExpandedChange,
    ) {
        CashierTextField(
            value = value,
            readOnly = true,
            singleLine = true,
            onValueChange = {},
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
            ),
            label = {
                Text(
                    text = labelText,
                    color = TextFieldPlaceHolderColor,
                )
            },
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = onDismissRequest,
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    onClick = {
                        onValueSelected.invoke(selectionOption)
                    },
                )
                {Text(text = selectionOption)}
            }
        }
    }
}