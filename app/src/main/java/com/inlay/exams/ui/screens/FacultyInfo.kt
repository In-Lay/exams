package com.inlay.exams.ui.screens

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.inlay.exams.ui.theme.GreenMedium
import com.inlay.exams.ui.uiModels.ApplicantWithAvgScoreUiModel
import com.inlay.exams.ui.viewModel.facultyInfo.FacultyInfoViewModel

@Composable
fun FacultyInfo(viewModel: FacultyInfoViewModel) {
    val applicantsWithAvgScore by viewModel.applicantsWithAvgScore.collectAsState()

    viewModel.getFaculty()
    val faculty by viewModel.faculty.collectAsState()

    ConstraintLayout(modifier = Modifier.fillMaxHeight()) {
        val (facultyText, applicantsText, lazyColumn) = createRefs()
        Text(text = faculty.name,
            style = typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.constrainAs(facultyText) {
                top.linkTo(parent.top, margin = 32.dp)
                start.linkTo(parent.start, margin = 32.dp)
                end.linkTo(parent.end, margin = 32.dp)
            })

        Text(text = "Our enrolled Applicants: ",
            style = typography.titleLarge,
            modifier = Modifier.constrainAs(applicantsText) {
                top.linkTo(facultyText.bottom, margin = 64.dp)
                start.linkTo(parent.start, margin = 16.dp)
            })

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(lazyColumn) {
                    top.linkTo(applicantsText.bottom, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                    width = Dimension.wrapContent
                    height = Dimension.fillToConstraints
                }) {

            items(applicantsWithAvgScore.sortedByDescending { it.averageScore }) {
                ApplicantsListItem(applicantWithAvgScore = it)
            }
        }
    }
}

@Composable
fun ApplicantsListItem(
    applicantWithAvgScore: ApplicantWithAvgScoreUiModel
) {
    Card(
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        modifier = Modifier
            .padding(start = 16.dp, bottom = 8.dp, top = 8.dp, end = 16.dp)
            .fillMaxSize(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (textName, textLastName, textAvgScore, divider) = createRefs()

            Text(text = "${applicantWithAvgScore.applicant?.firstName}",
                style = typography.headlineSmall,
                modifier = Modifier.constrainAs(textName) {
                    top.linkTo(parent.top, margin = 8.dp)
                    start.linkTo(parent.start, margin = 8.dp)
                    end.linkTo(parent.end, margin = 8.dp)
                })
            Text(text = "${applicantWithAvgScore.applicant?.lastName}",
                style = typography.headlineSmall,
                modifier = Modifier.constrainAs(textLastName) {
                    top.linkTo(textName.bottom)
                    start.linkTo(parent.start, margin = 8.dp)
                    end.linkTo(parent.end, margin = 8.dp)
                })
            Text(text = "Average score: ${applicantWithAvgScore.averageScore}",
                modifier = Modifier.constrainAs(textAvgScore) {
                    top.linkTo(textLastName.bottom, margin = 4.dp)
                    start.linkTo(parent.start, margin = 8.dp)
                    end.linkTo(parent.end, margin = 8.dp)
                    bottom.linkTo(divider.top, margin = 8.dp)
                })
            Divider(color = GreenMedium,
                thickness = 4.dp,
                modifier = Modifier.constrainAs(divider) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start, margin = 8.dp)
                    end.linkTo(parent.end, margin = 8.dp)
                })
        }
    }
}