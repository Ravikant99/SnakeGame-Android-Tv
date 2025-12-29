package com.example.growingsnake

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.growingsnake.ui.GameScreen
import com.example.growingsnake.ui.InstructionsScreen
import com.example.growingsnake.ui.MainMenuScreen

/**
 * Navigation controller for the Snake Game.
 * Manages navigation between Main Menu, Game, and Instructions screens.
 */
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "mainMenu"
    ) {
        composable("mainMenu") {
            MainMenuScreen(
                onStartGame = { navController.navigate("game") },
                onShowInstructions = { navController.navigate("instructions") }
            )
        }

        composable("game") {
            GameScreen(
                onBackPressed = { navController.popBackStack() }
            )
        }

        composable("instructions") {
            InstructionsScreen(onBack = { navController.popBackStack() })
        }
    }
}