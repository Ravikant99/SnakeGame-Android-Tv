package com.example.growingsnake.logic

import androidx.compose.runtime.Immutable

/**
 * Represents a point on the game grid.
 *
 * @property x X coordinate (column)
 * @property y Y coordinate (row)
 */
@Immutable
data class Point(val x: Int, val y: Int) {
    override fun toString(): String = "($x,$y)"
}

/**
 * Direction enum for snake movement.
 */
enum class Direction {
    UP, DOWN, LEFT, RIGHT
}

/**
 * Immutable state representing the current game state.
 *
 * @property snake List of points representing the snake's body (head is first)
 * @property food Position of the food
 * @property currentDirection Current direction of snake movement
 * @property gridSize Size of the game grid
 * @property isGameOver Whether the game has ended
 * @property score Current game score
 * @property isPaused Whether the game is paused
 */
data class GameState(
    val snake: List<Point>,
    val food: Point,
    val currentDirection: Direction,
    val gridSize: Int = 20,
    val isGameOver: Boolean = false,
    val score: Int = 0,
    val isPaused: Boolean = false
) {
    val head: Point get() = snake.first()

    init {
        require(gridSize > 5) { "Grid size must be at least 6" }
        require(snake.isNotEmpty()) { "Snake must have at least one segment" }
        require(snake.all { isValidPoint(it) }) {
            "All snake segments must be within grid bounds"
        }
        require(isValidPoint(food)) {
            "Food must be within grid bounds"
        }
    }

    private fun isValidPoint(point: Point): Boolean {
        return point.x in 0 until gridSize && point.y in 0 until gridSize
    }

    fun generateFood(): Point {
        val freePositions = mutableListOf<Point>().apply {
            for (x in 0 until gridSize) {
                for (y in 0 until gridSize) {
                    val point = Point(x, y)
                    if (point !in snake) add(point)
                }
            }
        }

        return freePositions.randomOrNull()?.also {
            require(isValidPoint(it)) { "Generated food position is invalid" }
        } ?: run {
            println("Warning: No available positions for food!")
            Point(0, 0).also { require(isValidPoint(it)) }
        }
    }

    companion object {
        fun initial(gridSize: Int = 20): GameState {
            require(gridSize > 5) { "Grid size must be at least 6" }

            val center = gridSize / 2
            val initialSnake = listOf(
                Point(center, center),
                Point(center, center + 1),
                Point(center, center + 2)
            )

            return GameState(
                snake = initialSnake,
                food = Point(0, 0),
                currentDirection = Direction.UP,
                gridSize = gridSize
            ).let { initialState ->
                initialState.copy(food = initialState.generateFood())
            }
        }
    }
}