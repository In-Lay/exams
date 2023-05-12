package com.inlay.exams.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.inlay.exams.di.getOrCreateLoginScope
import com.inlay.exams.ui.screens.ExamResultCard
import com.inlay.exams.ui.screens.FacultyInfo
import com.inlay.exams.ui.screens.LoginScreen
import com.inlay.exams.ui.screens.ProfileScreen
import com.inlay.exams.ui.theme.ExamsTheme
import com.inlay.exams.ui.viewModel.LoginViewModel
import com.inlay.exams.ui.viewModel.exams.ExamsViewModel
import com.inlay.exams.ui.viewModel.facultyInfo.FacultyInfoViewModel
import com.inlay.exams.ui.viewModel.loginScreen.LoginScreenViewModel
import com.inlay.exams.ui.viewModel.profile.ProfileViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val examsViewModel: ExamsViewModel by viewModel()
    private val facultyInfoViewModel: FacultyInfoViewModel by viewModel()
    private val profileViewModel: ProfileViewModel by viewModel()
    private val loginScreenViewModel: LoginScreenViewModel by viewModel()
    private val loginViewModel: LoginViewModel = getOrCreateLoginScope().get()


    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val loginScreenState = loginViewModel.loginScreenState

            val screens = if (!loginScreenState.value) listOf(
                Screen.LoginScreen, Screen.FacultyInfo, Screen.Exams
            )
            else listOf(Screen.ProfileScreen, Screen.FacultyInfo, Screen.Exams)


            ExamsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    Scaffold(bottomBar = {
                        BottomAppBar {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry?.destination
                            screens.forEach { screen ->
                                NavigationBarItem(icon = {
                                    Icon(
                                        screen.icon, contentDescription = null
                                    )
                                },
                                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                    label = { Text(stringResource(screen.resourceId)) },
                                    onClick = {
                                        navController.navigate(screen.route) {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true

                                            restoreState = true
                                        }
                                    })
                            }
                        }
                    }) {
                        NavHost(
                            navController,
                            startDestination = Screen.FacultyInfo.route,
                            Modifier.padding(it)
                        ) {
                            if (loginScreenState.value) {
                                composable(Screen.ProfileScreen.route) {
                                    ProfileScreen(
                                        profileViewModel
                                    )
                                }
                            } else {
                                composable(Screen.LoginScreen.route) {
                                    LoginScreen(
                                        loginScreenViewModel
                                    )
                                }
                            }
                            composable(Screen.FacultyInfo.route) {
                                FacultyInfo(
                                    facultyInfoViewModel
                                )
                            }
                            composable(Screen.Exams.route) {
                                ExamResultCard(examsViewModel)
                            }
                        }
                    }
                }
            }
        }
    }
}