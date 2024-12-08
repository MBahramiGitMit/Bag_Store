package com.mbahrami.bagstore.ui.screen.auth

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Key
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.mbahrami.bagstore.R

@Composable
fun AuthFormContent(
    fullName: () -> String,
    onFullNameChange: (String) -> Unit,
    email: () -> String,
    onEmailChange: (String) -> Unit,
    password: () -> String,
    onPasswordChange: (String) -> Unit,
    confirmPassword: () -> String,
    onConfirmPasswordChange: (String) -> Unit,
    onSingInClick: () -> Unit,
    onSingUpClick: () -> Unit
) {
    val scrollState = rememberScrollState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.verticalScroll(scrollState)
    ) {
        Image(
            modifier = Modifier
                .clip(CircleShape)
                .padding(32.dp)
                .size(60.dp),
            painter = painterResource(R.drawable.ic_icon_app),
            contentDescription = null
        )
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .shadow(elevation = 4.dp, shape = MaterialTheme.shapes.large)
                .background(color = MaterialTheme.colorScheme.surface),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var isSignIn: Boolean by remember { mutableStateOf(true) }

            AnimatedVisibility(!isSignIn) {
                SignUpForm(
                    fullName = fullName,
                    onFullNameChange = onFullNameChange,
                    email = email,
                    onEmailChange = onEmailChange,
                    password = password,
                    onPasswordChange = onPasswordChange,
                    confirmPassword = confirmPassword,
                    onConfirmPasswordChange = onConfirmPasswordChange,
                    onSingUpClick = onSingUpClick,
                    showSignIn = { isSignIn = true }
                )
            }

            AnimatedVisibility(isSignIn) {
                SignInForm(
                    email = email,
                    onEmailChange = onEmailChange,
                    password = password,
                    onPasswordChange = onPasswordChange,
                    onSingInClick = onSingInClick,
                    showSignUp = { isSignIn = false }
                )
            }
        }
    }
}

@Composable
fun SignInForm(
    email: () -> String,
    onEmailChange: (String) -> Unit,
    password: () -> String,
    onPasswordChange: (String) -> Unit,
    onSingInClick: () -> Unit,
    showSignUp: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = "SignIn",
            style = MaterialTheme.typography.displayMedium,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )

        val focusManager = LocalFocusManager.current
        EmailField(
            email = email,
            onEmailChange = onEmailChange,
            focusManager = focusManager
        )

        PasswordField(
            password = password,
            onPasswordChange = onPasswordChange,
            focusManager = focusManager,
            isLastComponent = true
        )

        Spacer(Modifier.height(8.dp))

        Button(
            modifier = Modifier.height(OutlinedTextFieldDefaults.MinHeight),
            onClick = onSingInClick
        ) { Text("Sign In") }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Don't have an account?")
            TextButton(onClick = {
                showSignUp()
                onPasswordChange("")
            }) { Text("Sign Up") }
        }

        Spacer(Modifier.height(16.dp))
    }
}

@Composable
fun SignUpForm(
    fullName: () -> String,
    onFullNameChange: (String) -> Unit,
    email: () -> String,
    onEmailChange: (String) -> Unit,
    password: () -> String,
    onPasswordChange: (String) -> Unit,
    confirmPassword: () -> String,
    onConfirmPasswordChange: (String) -> Unit,
    onSingUpClick: () -> Unit,
    showSignIn: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = "SignUp",
            style = MaterialTheme.typography.displayMedium,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )

        val focusManager = LocalFocusManager.current
        FullNameField(
            fullName = fullName,
            onFullNameChange = onFullNameChange,
            focusManager
        )

        EmailField(
            email = email,
            onEmailChange = onEmailChange,
            focusManager
        )

        PasswordField(
            password = password,
            onPasswordChange = onPasswordChange,
            focusManager = focusManager
        )

        PasswordField(
            password = confirmPassword,
            onPasswordChange = onConfirmPasswordChange,
            focusManager = focusManager,
            isConfirmation = true,
            isLastComponent = true
        )

        Spacer(Modifier.height(8.dp))

        Button(
            modifier = Modifier.height(OutlinedTextFieldDefaults.MinHeight),
            onClick = onSingUpClick
        ) { Text("Register Account") }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Already have an account?")
            TextButton(onClick = {
                showSignIn()
                onPasswordChange("")
                onConfirmPasswordChange("")
            }) { Text("Sign In") }
        }

        Spacer(Modifier.height(16.dp))
    }
}

@Composable
private fun FullNameField(
    fullName: () -> String,
    onFullNameChange: (newValue: String) -> Unit,
    focusManager: FocusManager
) {
    OutlinedTextField(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .fillMaxWidth()
            .focusProperties { next },
        value = fullName(),
        onValueChange = onFullNameChange,
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Person,
                contentDescription = null
            )
        },
        label = { Text("fullName") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            capitalization = KeyboardCapitalization.Words,
            keyboardType = KeyboardType.Text
        ),
        keyboardActions = KeyboardActions(onNext = {
            focusManager.moveFocus(FocusDirection.Next)
        })
    )
}

@Composable
private fun EmailField(
    email: () -> String,
    onEmailChange: (newValue: String) -> Unit,
    focusManager: FocusManager
) {
    OutlinedTextField(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .fillMaxWidth()
            .focusProperties { next },
        value = email(),
        onValueChange = onEmailChange,
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Email,
                contentDescription = null
            )
        },
        label = { Text("Email") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Email
        ),
        keyboardActions = KeyboardActions(onNext = {
            focusManager.moveFocus(FocusDirection.Next)
        })
    )
}

@Composable
private fun PasswordField(
    password: () -> String,
    onPasswordChange: (newValue: String) -> Unit,
    isConfirmation: Boolean = false,
    isLastComponent: Boolean = false,
    focusManager: FocusManager
) {
    var isVisible: Boolean by remember { mutableStateOf(false) }

    OutlinedTextField(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .fillMaxWidth()
            .focusProperties { next },
        value = password(),
        onValueChange = onPasswordChange,
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Key,
                contentDescription = null
            )
        },
        trailingIcon = {
            IconButton(onClick = { isVisible = !isVisible }) {
                Icon(
                    imageVector = if (isVisible)
                        Icons.Rounded.VisibilityOff
                    else
                        Icons.Rounded.Visibility,
                    contentDescription = null
                )
            }
        },
        visualTransformation = if (isVisible)
            VisualTransformation.None
        else
            PasswordVisualTransformation(),
        label = { Text(if (!isConfirmation) "Password" else "Confirm Password") },
        placeholder = {
            if (isConfirmation) {
                Text("Enter Your Password Again")
            }
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            imeAction = if (isLastComponent) ImeAction.Done else ImeAction.Next,
            keyboardType = KeyboardType.Password
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                focusManager.moveFocus(FocusDirection.Next)
            },
            onDone = { focusManager.clearFocus(force = true) })
    )
}

fun displayToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}