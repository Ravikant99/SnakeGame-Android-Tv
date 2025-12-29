package com.example.growingsnake.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
 * Instructions screen showing how to play the game.
 * Displays game controls and rules with high visibility for TV screens.
 *
 * @param onBack Callback for when the back button is pressed
 */
@OptIn(ExperimentalTvMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun InstructionsScreen(onBack: () -> Unit) {
    val focusRequester = remember { FocusRequester() }
    var buttonFocused by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(48.dp)
            .onKeyEvent { event ->
                if (event.type == KeyEventType.KeyDown && event.key == Key.Back) {
                    onBack()
                    true
                } else {
                    false
                }
            }
    ) {
        Text(
            text = "How to Play",
            color = Color(0xFF00E5FF),
            style = MaterialTheme.typography.headlineLarge.copy(
                fontSize = 56.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp),
            textAlign = TextAlign.Center
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Color(0xFF1E1E1E),
                    shape = RoundedCornerShape(16.dp)
                )
                .border(
                    width = 2.dp,
                    color = Color(0xFF00E5FF).copy(alpha = 0.5f),
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(32.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            GameInstructions()
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onBack,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 32.dp)
                .focusRequester(focusRequester)
                .onFocusChanged { buttonFocused = it.isFocused },
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
                text = "Back to Menu",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
        
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
    }
}