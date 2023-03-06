package app.snapcart.cashier.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle

import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import app.snapcart.cashier.ui.theme.TextFieldBackgroundColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CashierTextField(
    modifier: Modifier = Modifier,
    value: String,
    showError: Boolean = false,
    errorMessage: String? = null,
    onValueChange: (String) -> Unit,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    leadingComposable: @Composable (() -> Unit)? = null,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = TextFieldDefaults.filledShape,
    colors: TextFieldColors = TextFieldDefaults.textFieldColors(
        disabledTextColor = Color.Transparent,
        containerColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        errorIndicatorColor = Color.Transparent,
    )
    ) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(TextFieldBackgroundColor),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ){
        if( leadingComposable !=null){
            leadingComposable()
        }
        TextField(
            modifier = modifier.fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,
            label = label,
            leadingIcon = leadingIcon,
            placeholder = placeholder,
            supportingText = supportingText,
            visualTransformation = visualTransformation,
            singleLine = singleLine,
            isError = isError,
            keyboardOptions = keyboardOptions,
            keyboardActions =keyboardActions,
            trailingIcon = trailingIcon,
            maxLines = maxLines,
            interactionSource = interactionSource,
            shape = shape,
            textStyle = textStyle,
            enabled = enabled,
            readOnly = readOnly,
            colors = colors
        )
    }
    if (showError && !errorMessage.isNullOrEmpty()) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = errorMessage,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.error
        )
    }

}