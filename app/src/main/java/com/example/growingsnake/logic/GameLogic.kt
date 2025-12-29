package com.example.growingsnake.logic

/**
 * Handles the core game logic for the Snake game.
 *
 * @property gridSize Size of the game grid
 */
class GameLogic(private val gridSize: Int) {
    /**
     * Updates the game state based on the current direction and game rules.
     * Handles snake movement, collision detection, food consumption, and scoring.
     *
     * @param currentState The current game state
     * @return Updated game state
     */
    fun updateGameState(currentState: GameState): GameState {
        if (currentState.isGameOver || currentState.isPaused) return currentState

        val head = currentState.head
        val newHead = when (currentState.currentDirection) {
            Direction.UP -> head.copy(y = (head.y - 1).mod(gridSize))
            Direction.DOWN -> head.copy(y = (head.y + 1).mod(gridSize))
            Direction.LEFT -> head.copy(x = (head.x - 1).mod(gridSize))
            Direction.RIGHT -> head.copy(x = (head.x + 1).mod(gridSize))
        }

        val isGameOver = newHead in currentState.snake.drop(1)

        val ateFood = newHead == currentState.food
        val newSnake = if (ateFood) {
            listOf(newHead) + currentState.snake
        } else {
            listOf(newHead) + currentState.snake.dropLast(1)
        }

        val newFood = if (ateFood) currentState.generateFood() else currentState.food
        val newScore = if (ateFood) currentState.score + 10 else currentState.score

        return currentState.copy(
            snake = newSnake,
            food = newFood,
            isGameOver = isGameOver,
            score = newScore
        )
    }
}