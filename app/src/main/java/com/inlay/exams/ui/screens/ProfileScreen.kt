package com.inlay.exams.ui.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.inlay.exams.data.database.dataModels.ApplicantExamResult
import com.inlay.exams.ui.theme.GreenMedium
import com.inlay.exams.ui.theme.OrangeMedium
import com.inlay.exams.ui.viewModel.profile.ProfileViewModel

@Composable
fun ProfileScreen(viewModel: ProfileViewModel) {

    val applicant by viewModel.applicant.collectAsState()

    applicant?.let {
        viewModel.getFullExamInfoById(it.applicantId)
        viewModel.calculateAverageScore(it.applicantId)
    }

    val fullExamsResult by viewModel.fullExamResultById.collectAsState()
    val averageScore by viewModel.averageScore.collectAsState()
    averageScore?.let {

    }
    val status = if (fullExamsResult.size < 3 || averageScore == null) "TBD"
    else if (averageScore!! >= 150) "Enrolled" else "Denied"
    val statusColor =
        if (averageScore == null) Color.Black else if (averageScore!! >= 150) GreenMedium else Color.Red

    ConstraintLayout(modifier = Modifier.fillMaxHeight()) {
        val (applicantNameText, applicantName, statusView, statusText, scoreText, score, infoText, examsText, lazyColumn, logoutButton) = createRefs()

        Text(text = "Welcome!",
            style = typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.constrainAs(applicantNameText) {
                top.linkTo(parent.top, margin = 32.dp)
                start.linkTo(parent.start, margin = 16.dp)
                end.linkTo(parent.end, margin = 16.dp)
                width = Dimension.wrapContent
            })

        Text(text = "${applicant?.firstName} ${applicant?.lastName}",
            fontWeight = FontWeight.Bold,
            style = typography.headlineLarge,
            modifier = Modifier.constrainAs(applicantName) {
                top.linkTo(applicantNameText.bottom)
                start.linkTo(parent.start, margin = 16.dp)
                end.linkTo(parent.end, margin = 16.dp)
            })

        Text(text = "Status:",
            style = typography.titleMedium,
            modifier = Modifier.constrainAs(statusView) {
                top.linkTo(applicantName.bottom, margin = 16.dp)
                start.linkTo(parent.start, margin = 16.dp)
            })

        Text(text = status, color = statusColor, modifier = Modifier.constrainAs(statusText) {
            top.linkTo(applicantName.bottom, margin = 16.dp)
            start.linkTo(statusView.end, margin = 4.dp)
        })

        Text(text = "Average score:",
            style = typography.titleMedium,
            modifier = Modifier.constrainAs(scoreText) {
                top.linkTo(statusText.bottom, margin = 16.dp)
                start.linkTo(parent.start, margin = 16.dp)
            })

        if (fullExamsResult.size >= 3) {
            Text(text = "$averageScore",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(score) {
                    top.linkTo(statusText.bottom, margin = 16.dp)
                    start.linkTo(scoreText.end, margin = 4.dp)
                })
        }

        Text(text = "Your Exams: ",
            style = typography.headlineSmall,
            modifier = Modifier.constrainAs(examsText) {
                top.linkTo(scoreText.bottom, margin = 32.dp)
                start.linkTo(parent.start, margin = 16.dp)
            })

        if (fullExamsResult.isEmpty()) {
            Text(text = "No Exams passed! \nPlease, proceed to \nExams tab.",
                style = typography.headlineLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.constrainAs(infoText) {
                    top.linkTo(examsText.bottom, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                    bottom.linkTo(logoutButton.top, margin = 16.dp)
                    width = Dimension.matchParent
                    height = Dimension.wrapContent
                })
        } else {
            LazyColumn(Modifier.constrainAs(lazyColumn) {
                top.linkTo(examsText.bottom, margin = 16.dp)
                start.linkTo(parent.start, margin = 16.dp)
                end.linkTo(parent.end, margin = 16.dp)
                bottom.linkTo(logoutButton.top, margin = 16.dp)
                width = Dimension.wrapContent
                height = Dimension.fillToConstraints
            }) {
                items(items = fullExamsResult, itemContent = {
                    ExamsListItem(it)
                })
            }
        }

        Button(onClick = {
            viewModel.logout()
        }, modifier = Modifier.constrainAs(logoutButton) {
            bottom.linkTo(parent.bottom, margin = 16.dp)
            start.linkTo(parent.start, margin = 16.dp)
            end.linkTo(parent.end, margin = 16.dp)
        }, elevation = ButtonDefaults.buttonElevation(10.dp)
        ) {
            Text(text = "Logout", modifier = Modifier.padding(4.dp))
        }
//            }
//        }
    }
}

@Composable
fun ExamsListItem(applicantExamResult: ApplicantExamResult) {
    val scoreColor = if (applicantExamResult.score >= 180) GreenMedium
    else if (applicantExamResult.score >= 150) OrangeMedium else Color.Red

    Card(
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Row {
            Text(
                text = "${applicantExamResult.score}",
                style = typography.headlineMedium,
                color = scoreColor,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterVertically)
            )
            Column {
                Text(
                    text = applicantExamResult.examName,
                    fontWeight = FontWeight.Bold,
                    style = typography.titleMedium,
                    modifier = Modifier
                        .padding(start = 4.dp, top = 8.dp, bottom = 4.dp)
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                )
                Text(
                    text = "${applicantExamResult.teacherFirstName} ${applicantExamResult.teacherLastName}",
                    style = typography.bodyMedium,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}