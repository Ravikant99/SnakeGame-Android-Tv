package com.example.growingsnake.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Border
import androidx.tv.material3.Button
import androidx.tv.material3.ButtonDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.example.growingsnake.logic.GameInstructions
import com.example.growingsnake.ui.theme.FocusBorder

/**
 * Main menu screen with navigation options.
 * Displays title and buttons for starting the game or viewing instructions.
 *
 * @param onStartGame Callback for when "Start Game" is clicked
 * @param onShowInstructions Callback for when "How to Play" is clicked
 * @param modifier Modifier for the screen
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun MainMenuScreen(
    onStartGame: () -> Unit,
    onShowInstructions: () -> Unit,
    modifier: Modifier = Modifier
) {
    val startButtonFocusRequester = remember { FocusRequester() }
    var startButtonFocused by remember { mutableStateOf(false) }
    var instructionsButtonFocused by remember { mutableStateOf(false) }
    
    LaunchedEffect(Unit) {
        startButtonFocusRequester.requestFocus()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "🐍 Snake Game",
            color = Color(0xFF00E5FF),
            style = MaterialTheme.typography.displayLarge.copy(
                fontSize = 72.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 64.dp)
        )

        Button(
            onClick = onStartGame,
            modifier = Modifier
                .width(280.dp)
                .padding(12.dp)
                .focusRequester(startButtonFocusRequester)
                .onFocusChanged { startButtonFocused = it.isFocused },
            colors = ButtonDefaults.colors(
                containerColor = Color(0xFF1E88E5),
                contentColor = Color.White,
                focusedContainerColor = Color(0xFF2196F3),
                focusedContentColor = Color.White
            ),
            border = ButtonDefaults.border(
                focusedBorder = Border(
                    border = BorderStroke(
                        width = 4.dp,
                        color = FocusBorder
                    )
                )
            ),
            scale = ButtonDefaults.scale(
                focusedScale = 1.1f
            )
        ) {
            Text(
                text = "Start Game",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        Button(
            onClick = onShowInstructions,
            modifier = Modifier
                .width(280.dp)
                .padding(12.dp)
                .onFocusChanged { instructionsButtonFocused = it.isFocused },
            colors = ButtonDefaults.colors(
                containerColor = Color(0xFF1E88E5),
                contentColor = Color.White,
                focusedContainerColor = Color(0xFF2196F3),
                focusedContentColor = Color.White
            ),
            border = ButtonDefaults.border(
                focusedBorder = Border(
                    border = BorderStroke(
                        width = 4.dp,
                        color = FocusBorder
                    )
                )
            ),
            scale = ButtonDefaults.scale(
                focusedScale = 1.1f
            )
        ) {
            Text(
                text = "How to Play",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}