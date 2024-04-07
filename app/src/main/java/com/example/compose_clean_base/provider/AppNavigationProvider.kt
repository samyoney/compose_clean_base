package com.example.compose_clean_base.provider

import androidx.navigation.NavController
import com.example.compose_clean_base.presentation.destinations.SamScreenDestination
import com.example.compose_clean_base.provider.mask.NavigationProvider
import com.ramcosta.composedestinations.navigation.navigate

class AppNavigationProvider(
    private val navController: NavController
) : NavigationProvider {

    override fun openSam() {
        navController.popBackStack()
        navController.navigate(SamScreenDestination)
    }

    override fun navigateUp() {
        navController.navigateUp()
    }
}