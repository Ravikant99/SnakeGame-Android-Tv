# Contributing to Snake Game for Android TV

First off, thank you for considering contributing to this Android TV Snake Game! 🎉

## How Can I Contribute?

### Reporting Bugs

Before creating bug reports, please check existing issues to avoid duplicates. When you create a bug report, include as many details as possible:

- **Use a clear and descriptive title**
- **Describe the exact steps to reproduce the problem**
- **Provide specific examples**
- **Describe the behavior you observed and what you expected**
- **Include screenshots or screen recordings if possible**
- **Note your Android TV device model and Android version**

### Suggesting Enhancements

Enhancement suggestions are tracked as GitHub issues. When creating an enhancement suggestion, include:

- **A clear and descriptive title**
- **A detailed description of the proposed feature**
- **Explain why this enhancement would be useful**
- **List any alternative solutions you've considered**

### Pull Requests

1. **Fork the repository** and create your branch from `main`
2. **Follow the coding style** used in the project
3. **Add documentation** for any new features
4. **Test your changes** on an Android TV emulator or device
5. **Ensure no linter errors** exist
6. **Update README.md** if needed
7. **Submit a pull request**

## Development Setup

### Prerequisites

- Android Studio Hedgehog or newer
- Kotlin 1.9+
- Android TV emulator or physical device
- Git

### Getting Started

```bash
# Clone your fork
git clone https://github.com/YOUR_USERNAME/GrowingSnake.git
cd GrowingSnake

# Add upstream remote
git remote add upstream https://github.com/ORIGINAL_OWNER/GrowingSnake.git

# Create a branch for your changes
git checkout -b feature/your-feature-name
```

### Code Style Guidelines

#### Kotlin

- Follow [Kotlin Coding Conventions](https://kotlinlang.org/docs/coding-conventions.html)
- Use meaningful variable and function names
- Keep functions small and focused
- Add KDoc comments for public APIs

Example:
```kotlin
/**
 * Updates the game state based on the current direction.
 *
 * @param currentState The current game state
 * @return Updated game state with new snake position
 */
fun updateGameState(currentState: GameState): GameState {
    // Implementation
}
```

#### Compose

- Use descriptive composable names
- Keep composables small and reusable
- Document complex UI logic
- Use `remember` for state that shouldn't recompose

Example:
```kotlin
/**
 * Displays the game board with snake and food.
 *
 * @param gridSize Size of the game grid
 * @param gameState Current game state
 * @param modifier Modifier for styling
 */
@Composable
fun GameBoard(
    gridSize: Int,
    gameState: GameState,
    modifier: Modifier = Modifier
) {
    // Implementation
}
```

### Testing

Before submitting a pull request:

1. **Test on Android TV emulator**
   - Open AVD Manager in Android Studio
   - Create/use a TV device profile
   - Run the app and test all features

2. **Test D-pad navigation**
   - Verify all buttons are focusable
   - Ensure focus indicators are visible
   - Test navigation flow between screens

3. **Test game mechanics**
   - Snake movement in all directions
   - Food consumption and scoring
   - Collision detection
   - Pause/resume functionality

4. **Check for linter errors**
   - Run `./gradlew lint` in terminal
   - Fix any warnings or errors

### Project Structure

```
app/src/main/java/com/example/growingsnake/
├── MainActivity.kt           # Entry point
├── Navigation.kt            # Navigation setup
├── ui/                      # UI components
│   ├── GameScreen.kt        # Game UI and logic
│   ├── MainMenuScreen.kt    # Main menu
│   ├── InstructionScreen.kt # Instructions
│   └── theme/              # Theme files
└── logic/                   # Game logic
    ├── GameState.kt         # State management
    ├── GameLogic.kt         # Core logic
    └── GameInstructions.kt  # Instructions component
```

### Commit Messages

- Use present tense ("Add feature" not "Added feature")
- Use imperative mood ("Move cursor to..." not "Moves cursor to...")
- Limit first line to 72 characters
- Reference issues and pull requests

Examples:
```
Add pause menu animation
Fix snake collision detection bug
Update README with new screenshots
Refactor game state management
```

### Areas for Contribution

Here are some areas where contributions would be especially valuable:

#### Features
- [ ] Different difficulty levels
- [ ] High score persistence
- [ ] Sound effects and music
- [ ] Multiple snake skins/themes
- [ ] Power-ups (speed boost, invincibility, etc.)
- [ ] Multiplayer support
- [ ] Leaderboards

#### UI/UX
- [ ] More animations
- [ ] Additional color themes
- [ ] Settings screen
- [ ] Haptic feedback support
- [ ] Achievement system

#### Technical
- [ ] Unit tests for game logic
- [ ] UI tests with Compose Testing
- [ ] Performance optimizations
- [ ] Accessibility improvements
- [ ] Localization support

## Questions?

Feel free to open an issue with the `question` label if you need help or clarification.

## License

By contributing, you agree that your contributions will be licensed under the MIT License.

---

Thank you for contributing! 🚀

