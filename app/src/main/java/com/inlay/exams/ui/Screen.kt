package com.inlay.exams.ui

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.inlay.exams.R

sealed class Screen(val route: String, @StringRes val resourceId: Int, val icon: ImageVector) {
    object LoginScreen : Screen("loginScreen", R.string.login_screen, Icons.Outlined.Home)
    object ProfileScreen : Screen("profileScreen", R.string.profile_screen, Icons.Outlined.Home)
    object FacultyInfo : Screen("facultyInfo", R.string.info, Icons.Outlined.Info)
    object Exams : Screen("examsScreen", R.string.app_name, Icons.Outlined.Search)
}
