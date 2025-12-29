package com.example.growingsnake.logic

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text

/**
 * Composable that displays game instructions with high visibility formatting.
 * Shows controls and rules for the Snake game optimized for TV screens.
 *
 * @param modifier Modifier for the instructions container
 */
@Composable
fun GameInstructions(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = "Game Controls:",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            ),
            color = Color(0xFFFFEA00)
        )
        
        InstructionItem("🎮 Use D-Pad arrows to control the snake direction")
        InstructionItem("⏸️ Press MENU button to pause/resume the game")
        InstructionItem("🍎 Eat the red food to grow longer and score points")
        InstructionItem("⚠️ Avoid hitting the walls or yourself")
        InstructionItem("🏆 The longer you survive, the higher your score!")
    }
}

@Composable
private fun InstructionItem(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge.copy(
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 32.sp
        ),
        color = Color(0xFFE0E0E0)
    )
}