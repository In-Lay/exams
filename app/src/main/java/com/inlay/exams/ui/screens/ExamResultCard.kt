package com.inlay.exams.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.inlay.exams.data.database.entities.Exam
import com.inlay.exams.data.database.entities.Teacher
import com.inlay.exams.ui.theme.GreenMedium
import com.inlay.exams.ui.theme.OrangeMedium
import com.inlay.exams.ui.viewModel.exams.ExamsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExamResultCard(viewModel: ExamsViewModel) {
    val applicant by viewModel.applicant.collectAsState()

    val isLogged = viewModel.isLogged

    if (!isLogged.value && applicant == null) {
        Box(modifier = Modifier.padding(top = 250.dp, start = 16.dp, end = 16.dp)) {
            Text(
                text = "You are not Logged. \nPlease, Log in or Register \nin Profile tab",
                style = typography.headlineSmall,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
                    .align(Alignment.Center)
            )
        }
    } else {
        viewModel.getAllExams()
        viewModel.getPassedExams(applicant!!.applicantId)
        viewModel.getAvailableExams()

        val teachers by viewModel.teachers.collectAsState()

        val availableExams by viewModel.availableExams.collectAsState()

        val passedExams by viewModel.passedExams.collectAsState()
        val avgScore by viewModel.avgScore.collectAsState()

        var selectedExam by remember { mutableStateOf<Exam?>(null) }
        var selectedTeacher by remember { mutableStateOf<Teacher?>(null) }


        val score by viewModel.gradeScore.collectAsState()

        val scoreColor = if (score >= 180) GreenMedium
        else if (score >= 150) OrangeMedium else if (score == 0) Color.Black else Color.Red

        val examDropdownExpanded = remember { mutableStateOf(false) }
        val teacherDropdownExpanded = remember { mutableStateOf(false) }

        val buttonState = selectedExam != null

        Column(modifier = Modifier.padding(16.dp)) {
            Row {
                Text(
                    text = "Applicant:",
                    modifier = Modifier.padding(start = 4.dp, top = 4.dp, bottom = 16.dp),
                    style = typography.headlineSmall
                )
                Text(
                    text = "${applicant!!.firstName} ${applicant!!.lastName}",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(bottom = 16.dp, start = 4.dp, top = 4.dp)
                        .fillMaxWidth(),
                    style = typography.headlineSmall
                )
            }

            val examsFieldShape = if (examDropdownExpanded.value) RoundedCornerShape(8.dp).copy(
                bottomEnd = CornerSize(0.dp), bottomStart = CornerSize(0.dp)
            )
            else RoundedCornerShape(8.dp)

            val teachersFieldShape =
                if (teacherDropdownExpanded.value) RoundedCornerShape(8.dp).copy(
                    bottomEnd = CornerSize(0.dp), bottomStart = CornerSize(0.dp)
                )
                else RoundedCornerShape(8.dp)


            MaterialTheme(
                shapes = MaterialTheme.shapes.copy(extraSmall = RoundedCornerShape(16.dp))
            ) {
                Column {
                    Card(
                        elevation = CardDefaults.cardElevation(10.dp),
                        modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
                    ) {
                        ExposedDropdownMenuBox(
                            expanded = examDropdownExpanded.value, onExpandedChange = {
                                examDropdownExpanded.value = it
                            }, modifier = Modifier.fillMaxWidth()
                        ) {
                            TextField(
                                value = selectedExam?.name ?: "Select Exam",
                                textStyle = TextStyle.Default.copy(
                                    fontSize = 18.sp, fontWeight = FontWeight.Light
                                ),
                                label = {
                                    Text(
                                        "Select Exam",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 12.sp,
                                        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                                    )
                                },
                                onValueChange = {},
                                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = examDropdownExpanded.value) },
                                readOnly = true,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .menuAnchor(),
                                colors = ExposedDropdownMenuDefaults.textFieldColors(
                                    focusedIndicatorColor = Transparent,
                                    unfocusedIndicatorColor = Transparent
                                ),
                                shape = examsFieldShape
                            )

                            ExposedDropdownMenu(
                                expanded = examDropdownExpanded.value,
                                onDismissRequest = { examDropdownExpanded.value = false }
                            ) {
                                availableExams.forEach { exam ->
                                    DropdownMenuItem(
                                        text = {
                                            Text(
                                                text = exam.name
                                            )
                                        },
                                        onClick = {
                                            examDropdownExpanded.value = false
                                            selectedExam = exam
                                        },
                                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                                    )
                                }
                            }
                        }
                    }

                    Card(
                        elevation = CardDefaults.cardElevation(10.dp),
                        modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
                    ) {
                        ExposedDropdownMenuBox(expanded = teacherDropdownExpanded.value,
                            onExpandedChange = { teacherDropdownExpanded.value = it }) {
                            TextField(value = selectedTeacher?.let { "${it.firstName} ${it.lastName}" }
                                ?: "Select Teacher",
                                textStyle = TextStyle.Default.copy(
                                    fontSize = 18.sp, fontWeight = FontWeight.Light
                                ),
                                onValueChange = { },
                                label = {
                                    Text(
                                        "Select Teacher",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 12.sp,
                                        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                                    )
                                },
                                readOnly = true,
                                colors = ExposedDropdownMenuDefaults.textFieldColors(
                                    focusedIndicatorColor = Transparent,
                                    unfocusedIndicatorColor = Transparent
                                ),
                                shape = teachersFieldShape,
                                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = examDropdownExpanded.value) },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .menuAnchor()
                            )

                            ExposedDropdownMenu(
                                expanded = teacherDropdownExpanded.value,
                                onDismissRequest = { teacherDropdownExpanded.value = false },
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                teachers.forEach { teacher ->
                                    DropdownMenuItem(
                                        text = { Text(text = "${teacher.firstName} ${teacher.lastName}") },
                                        onClick = {
                                            teacherDropdownExpanded.value = false
                                            selectedTeacher = teacher
                                        },
                                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                                    )
                                }
                            }
                        }
                    }
                }
            }


            if (passedExams.size < 3) {
                Row {
                    Text(
                        text = "Your score is:",
                        color = scoreColor,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    )

                    Text(
                        text = "$score", fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    )
                }

                Button(
                    onClick = {
                        viewModel.getPassedExams(applicant!!.applicantId)
                        viewModel.issueGrade(
                            applicant!!, selectedExam!!, selectedTeacher
                        )
                    },
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(top = 16.dp),
                    enabled = buttonState,
                    elevation = ButtonDefaults.buttonElevation(14.dp)
                ) {
                    Text("Issue Grade", modifier = Modifier.padding(4.dp))
                }
            } else {
                Row {
                    Text(
                        text = "Your average grade is: ",
                        modifier = Modifier
                            .padding(top = 16.dp)
                    )
                    Text(
                        text = "$avgScore",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(top = 16.dp)
                    )
                }
                Button(
                    onClick = { viewModel.calculateAverage(applicant!!.applicantId) },
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(top = 32.dp),
                    elevation = ButtonDefaults.buttonElevation(14.dp)
                ) {
                    Text("Get average grade", modifier = Modifier.padding(4.dp))
                }
            }
        }
    }
}