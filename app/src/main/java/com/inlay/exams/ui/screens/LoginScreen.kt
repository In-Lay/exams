package com.inlay.exams.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.inlay.exams.R
import com.inlay.exams.ui.viewModel.loginScreen.LoginScreenViewModel

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

    val image = painterResource(id = R.drawable.app_icon_image_256x256)
    Column(Modifier.padding(16.dp)) {

        Image(
            painter = image,
            contentDescription = "",
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        )
        Box(modifier = Modifier.padding(top = 16.dp)) {
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
                modifier = Modifier.fillMaxWidth()
            )
        }
        Box(modifier = Modifier.padding(top = 16.dp)) {
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
                modifier = Modifier.fillMaxWidth()
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Button(
                onClick = {
                    if (applicantName.value == "") nameSupportText.value = "Name is empty"
                    else if (applicantSecondName.value == "") secondNameSupportText.value =
                        "Second Name is empty"
                    else viewModel.register(applicantName.value, applicantSecondName.value)
                }, modifier = Modifier
                    .padding(16.dp)
                    .weight(1f)
            ) {
                Text(text = "Register")
            }

            Button(
                onClick = {
                    if (applicantName.value == "") nameSupportText.value = "Name is empty"
                    else if (applicantSecondName.value == "") secondNameSupportText.value =
                        "Second Name is empty"
                    else viewModel.login(applicantName.value, applicantSecondName.value)
                }, modifier = Modifier
                    .padding(16.dp)
                    .weight(1f)
            ) {
                Text(text = "Login")
            }
        }

        Text(
            text = loginErrorText,
            color = Color.Red,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}
