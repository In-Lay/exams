package com.inlay.exams.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.inlay.exams.R
import com.inlay.exams.ui.viewModel.loginScreen.LoginScreenViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    viewModel: LoginScreenViewModel
) {
    val applicantName = remember { mutableStateOf("") }

    val applicantSecondName = remember { mutableStateOf("") }

    val nameSupportText = remember { mutableStateOf("") }
    val secondNameSupportText = remember { mutableStateOf("") }

    val loginErrorText by viewModel.loginErrorText.collectAsState()

    val imageSource = painterResource(id = R.drawable.app_icon_image_256x256)
    ConstraintLayout {
        val (image, fieldName, fieldLastName, buttonRegister, buttonLogin, textError) = createRefs()

        Image(
            painter = imageSource,
            contentDescription = "",
            modifier = Modifier
                .padding(16.dp)
                .constrainAs(image) {
                    top.linkTo(parent.top, margin = 32.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                    bottom.linkTo(fieldName.top, margin = 16.dp)
                    width = Dimension.wrapContent
                    height = Dimension.wrapContent
                }
        )

        OutlinedTextField(
            value = applicantName.value,
            onValueChange = { applicantName.value = it },
            label = {
                Text(text = "Name")
            },
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            supportingText = { Text(text = nameSupportText.value, color = Color.Red) },
            modifier = Modifier.constrainAs(fieldName) {
                bottom.linkTo(fieldLastName.top, margin = 16.dp)
                start.linkTo(parent.start, margin = 16.dp)
                end.linkTo(parent.end, margin = 16.dp)
                width = Dimension.matchParent
                height = Dimension.wrapContent
            }
        )

        OutlinedTextField(
            value = applicantSecondName.value,
            onValueChange = { applicantSecondName.value = it },
            label = {
                Text(text = "Second Name")
            },
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            supportingText = { Text(text = secondNameSupportText.value, color = Color.Red) },
            modifier = Modifier.constrainAs(fieldLastName) {
                bottom.linkTo(buttonRegister.top, margin = 16.dp)
                start.linkTo(parent.start, margin = 16.dp)
                end.linkTo(parent.end, margin = 16.dp)
                width = Dimension.matchParent
                height = Dimension.wrapContent
            }
        )

        Button(
            onClick = {
                if (applicantName.value.isEmpty()) nameSupportText.value = "Name is empty"
                else if (applicantSecondName.value.isEmpty()) secondNameSupportText.value =
                    "Second Name is empty"
                else viewModel.register(applicantName.value, applicantSecondName.value)
            }, modifier = Modifier
                .padding(16.dp)
                .constrainAs(buttonRegister) {
                    start.linkTo(parent.start, margin = 16.dp)
                    bottom.linkTo(textError.top, margin = 16.dp)
                    end.linkTo(buttonLogin.start, margin = 16.dp)
                }
        ) {
            Text(text = "Register")
        }

        Button(
            onClick = {
                if (applicantName.value.isEmpty()) nameSupportText.value = "Name is empty"
                else if (applicantSecondName.value.isEmpty()) secondNameSupportText.value =
                    "Second Name is empty"
                else viewModel.login(applicantName.value, applicantSecondName.value)
            }, modifier = Modifier
                .padding(16.dp)
                .constrainAs(buttonLogin) {
                    end.linkTo(parent.end, margin = 16.dp)
                    bottom.linkTo(textError.top, margin = 16.dp)
                    start.linkTo(buttonRegister.end, margin = 16.dp)
                }
        ) {
            Text(text = "Login")
        }

        Text(
            text = loginErrorText,
            color = Color.Red,
            textAlign = TextAlign.Center,
            style = typography.bodyLarge,
            modifier = Modifier
                .constrainAs(textError) {
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                    width = Dimension.matchParent
                    height = Dimension.wrapContent
                }
        )
    }
}
