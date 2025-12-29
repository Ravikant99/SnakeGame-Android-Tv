package com.example.growingsnake.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Button
import androidx.tv.material3.ButtonDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.OutlinedButton
import androidx.tv.material3.OutlinedButtonDefaults
import androidx.tv.material3.Text
import com.example.growingsnake.logic.Direction
import com.example.growingsnake.logic.GameLogic
import com.example.growingsnake.logic.GameState
import com.example.growingsnake.ui.theme.FocusBorder
import com.example.growingsnake.ui.theme.FoodRed
import com.example.growingsnake.ui.theme.SnakeGreen
import com.example.growingsnake.ui.theme.SnakeHeadGreen
import kotlinx.coroutines.delay

/**
 * Main game screen composable that handles the Snake game logic and rendering.
 *
 * @param modifier Modifier for the game screen
 * @param gridSize Size of the game grid (default: 20x20)
 * @param onBackPressed Callback for when the back button is pressed
 */
@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    gridSize: Int = 20,
    onBackPressed: () -> Unit = {}
) {
    val focusRequester = remember { FocusRequester() }
    var gameState by remember { mutableStateOf(GameState.initial(gridSize)) }
    val gameLogic = remember { GameLogic(gridSize) }
    var gameSpeed by remember { mutableStateOf(150L) }

    LaunchedEffect(gameState.isGameOver, gameState.isPaused) {
        while (!gameState.isGameOver && !gameState.isPaused) {
            delay(gameSpeed)
            gameState = gameLogic.updateGameState(gameState)
        }
    }

    val onKeyEvent: (androidx.compose.ui.input.key.KeyEvent) -> Boolean = { event ->
        when {
            event.type == KeyEventType.KeyDown -> {
                when (event.key) {
                    Key.DirectionUp -> if (gameState.currentDirection != Direction.DOWN) {
                        gameState = gameState.copy(currentDirection = Direction.UP)
                    }
                    Key.DirectionDown -> if (gameState.currentDirection != Direction.UP) {
                        gameState = gameState.copy(currentDirection = Direction.DOWN)
                    }
                    Key.DirectionLeft -> if (gameState.currentDirection != Direction.RIGHT) {
                        gameState = gameState.copy(currentDirection = Direction.LEFT)
                    }
                    Key.DirectionRight -> if (gameState.currentDirection != Direction.LEFT) {
                        gameState = gameState.copy(currentDirection = Direction.RIGHT)
                    }
                    Key.Menu -> {
                        gameState = gameState.copy(isPaused = !gameState.isPaused)
                    }
                    Key.Back -> {
                        onBackPressed()
                    }
                    Key.Enter -> {
                        if (gameState.isGameOver) {
                            gameState = GameState.initial(gridSize)
                        }
                    }
                    else -> false
                }
                true
            }
            else -> false
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .onKeyEvent(onKeyEvent)
            .focusRequester(focusRequester)
            .focusable()
    ) {
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Score: ${gameState.score}",
                style = TextStyle(
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFFEA00)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            GameBoard(
                gridSize = gridSize,
                gameState = gameState,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(16.dp)
                    .border(3.dp, Color(0xFF00E5FF))
            )

            if (gameState.isGameOver) {
                GameStatusPopup(
                    title = "Game Over",
                    score = gameState.score,
                    primaryButtonText = "Play Again",
                    onButtonClick = { gameState = GameState.initial(gridSize) },
                    secondaryButtonText = "Exit",
                    onSecondaryButtonClick = onBackPressed
                )
            } else if (gameState.isPaused) {
                GameStatusPopup(
                    title = "Game Paused",
                    primaryButtonText = "Resume",
                    onButtonClick = { gameState = gameState.copy(isPaused = false) },
                    secondaryButtonText = "Exit",
                    onSecondaryButtonClick = onBackPressed
                )
            }
        }
    }
}

/**
 * Composable that renders the game board using Canvas.
 * Draws the snake, food, and grid border with optimized TV visibility.
 *
 * @param gridSize Size of the game grid
 * @param gameState Current state of the game
 * @param modifier Modifier for the canvas
 */
@Composable
fun GameBoard(
    gridSize: Int,
    gameState: GameState,
    modifier: Modifier = Modifier
) {
    val borderColor = Color(0xFF00E5FF).copy(alpha = 0.5f)

    Canvas(modifier = modifier.fillMaxSize()) {
        val cellSize = minOf(size.width, size.height) / gridSize

        val xOffset = (size.width - (cellSize * gridSize)) / 2
        val yOffset = (size.height - (cellSize * gridSize)) / 2

        drawCircle(
            color = FoodRed,
            radius = cellSize / 2,
            center = Offset(
                xOffset + gameState.food.x * cellSize + cellSize / 2,
                yOffset + gameState.food.y * cellSize + cellSize / 2
            )
        )

        gameState.snake.forEachIndexed { index, point ->
            val color = if (index == 0) SnakeHeadGreen else SnakeGreen
            drawCircle(
                color = color,
                radius = cellSize / 2,
                center = Offset(
                    xOffset + point.x * cellSize + cellSize / 2,
                    yOffset + point.y * cellSize + cellSize / 2
                )
            )
        }

        drawRect(
            color = borderColor,
            topLeft = Offset(xOffset, yOffset),
            size = Size(cellSize * gridSize, cellSize * gridSize),
            style = Stroke(width = 4f)
        )
    }
}

/**
 * Modal popup for game over and pause states.
 * Includes two buttons with proper focus management and D-pad navigation.
 *
 * @param title Title text to display (e.g., "Game Over", "Game Paused")
 * @param score Optional score to display
 * @param primaryButtonText Text for the primary action button
 * @param onButtonClick Callback for primary button click
 * @param secondaryButtonText Text for the secondary action button
 * @param onSecondaryButtonClick Callback for secondary button click
 */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun GameStatusPopup(
    title: String,
    score: Int? = null,
    primaryButtonText: String,
    onButtonClick: () -> Unit,
    secondaryButtonText: String,
    onSecondaryButtonClick: () -> Unit = {}
) {
    val primaryFocusRequester = remember { FocusRequester() }
    val secondaryFocusRequester = remember { FocusRequester() }
    
    LaunchedEffect(Unit) {
        primaryFocusRequester.requestFocus()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.85f))
            .onKeyEvent { event ->
                if (event.type == KeyEventType.KeyDown) {
                    when (event.key) {
                        Key.DirectionRight, Key.DirectionDown -> {
                            secondaryFocusRequester.requestFocus()
                            true
                        }
                        Key.DirectionLeft, Key.DirectionUp -> {
                            primaryFocusRequester.requestFocus()
                            true
                        }
                        else -> false
                    }
                } else {
                    false
                }
            }
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .background(
                    Color(0xFF1E1E1E),
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp)
                )
                .border(
                    width = 3.dp,
                    color = Color(0xFF00E5FF),
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp)
                )
                .padding(48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = Color(0xFF00E5FF)
            )

            score?.let {
                Text(
                    text = "Score: $score",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontSize = 36.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = Color(0xFFFFEA00),
                    modifier = Modifier.padding(vertical = 24.dp)
                )
            }

            Row(
                modifier = Modifier.padding(top = 32.dp),
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                Button(
                    onClick = onButtonClick,
                    modifier = Modifier
                        .defaultMinSize(minWidth = 180.dp, minHeight = 56.dp)
                        .focusRequester(primaryFocusRequester),
                    colors = ButtonDefaults.colors(
                        containerColor = Color(0xFF1E88E5),
                        contentColor = Color.White,
                        focusedContainerColor = Color(0xFF2196F3),
                        focusedContentColor = Color.White
                    ),
                    border = ButtonDefaults.border(
                        focusedBorder = androidx.tv.material3.Border(
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
                        text = primaryButtonText,
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                OutlinedButton(
                    onClick = onSecondaryButtonClick,
                    modifier = Modifier
                        .defaultMinSize(minWidth = 180.dp, minHeight = 56.dp)
                        .focusRequester(secondaryFocusRequester),
                    colors = OutlinedButtonDefaults.colors(
                        containerColor = Color.Transparent,
                        contentColor = Color(0xFFFF5252),
                        focusedContainerColor = Color(0xFFFF5252).copy(alpha = 0.2f),
                        focusedContentColor = Color(0xFFFF5252)
                    ),
                    border = OutlinedButtonDefaults.border(
                        border = androidx.tv.material3.Border(
                            border = BorderStroke(
                                width = 2.dp,
                                color = Color(0xFFFF5252)
                            )
                        ),
                        focusedBorder = androidx.tv.material3.Border(
                            border = BorderStroke(
                                width = 4.dp,
                                color = FocusBorder
                            )
                        )
                    ),
                    scale = OutlinedButtonDefaults.scale(
                        focusedScale = 1.1f
                    )
                ) {
                    Text(
                        text = secondaryButtonText,
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }
    }
}
