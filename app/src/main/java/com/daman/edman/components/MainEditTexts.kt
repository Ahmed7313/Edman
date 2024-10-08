package com.aramex.mypos.Presentation.Components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daman.edman.ui.theme.borderColor


@Composable
fun MainEditText(
    modifier: Modifier = Modifier,
    text: String,
    label: String? = null,
    onTextChange: (String) -> Unit,
    isError: Boolean,
    isPassword: Boolean = false,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardType: KeyboardOptions = KeyboardOptions.Default,
    textAlign: Boolean = false,
    readOnly: Boolean = false,
    enabled: Boolean = true,
    imeAction: ImeAction = ImeAction.Done, // New argument to specify IME action
    onImeAction: (() -> Unit)? = null,  // Optional callback for custom action on IME action
    focusRequester: FocusRequester = remember { FocusRequester() } // New focus requester
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused = interactionSource.collectIsFocusedAsState().value
    var passwordVisible by rememberSaveable { mutableStateOf(false) } // State to toggle password visibility
    val visualTransformation =
        if (!passwordVisible && isPassword) PasswordVisualTransformation()
        else VisualTransformation.None

    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = text,
        onValueChange = onTextChange,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .focusRequester(focusRequester), // Add focusRequester to control focus
        interactionSource = interactionSource,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = borderColor,
        ),
        trailingIcon = {
            if (isPassword) {
                val image =
                    if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                val description = if (passwordVisible) "Hide password" else "Show password"

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, contentDescription = description)
                }
            } else {
                trailingIcon?.invoke()
            }
        },
        isError = isError,
        readOnly = readOnly,
        label = {
            AutoSizeText(
                text = label ?: "",
                color = Color.Gray,
                fontFamily = FontFamily.SansSerif,
                fontSize = 12.sp
            )
        },
        keyboardOptions = keyboardType.copy(
            keyboardType = if (isPassword) KeyboardType.Password else keyboardType.keyboardType,
            imeAction = imeAction // Set the IME action
        ),
        visualTransformation = visualTransformation,
        shape = RoundedCornerShape(15.dp),
        keyboardActions = KeyboardActions(
            onNext = {
                onImeAction?.invoke() // Call custom callback for next action
                focusManager.moveFocus(FocusDirection.Next) // Move to next field
            },
            onDone = {
                onImeAction?.invoke() // Call custom callback for done action
                keyboardController?.hide()
            }
        ),
        textStyle = if (textAlign) LocalTextStyle.current.copy(textAlign = TextAlign.Center) else LocalTextStyle.current,
    )
}

