# 🐍 Snake Game for Android TV

A modern, high-contrast Snake game specifically designed and optimized for Android TV devices. Built with Jetpack Compose and Android TV Material3 components.

![Android TV](https://img.shields.io/badge/Platform-Android%20TV-green)
![Kotlin](https://img.shields.io/badge/Language-Kotlin-blue)
![Compose](https://img.shields.io/badge/UI-Jetpack%20Compose-orange)

## ✨ Features

- **🎮 TV-Optimized Controls**: Full D-pad support for seamless navigation
- **👁️ High Visibility**: Bright, high-contrast colors designed for TV viewing from a distance
- **🎯 Focus Management**: Clear visual indicators with animated borders and scaling
- **⏸️ Pause & Resume**: Pause the game anytime with the MENU button
- **📊 Score Tracking**: Real-time score display with game over screen
- **📖 Instructions**: Easy-to-read game instructions with large text
- **🎨 Modern UI**: Beautiful interface with rounded corners and smooth animations

## 🎨 Design Highlights

### Color Scheme
- **Bright Cyan** (#00E5FF) - Primary accent color
- **Vivid Blue** (#2979FF) - Button backgrounds
- **Bright Yellow** (#FFEB3B) - Focus indicators
- **Neon Green** (#00FF41, #39FF14) - Snake
- **Vivid Red** (#FF1744) - Food
- **Dark Theme** - Optimized for TV viewing

### UI Features
- **10% Scale Animation** on button focus
- **4dp Bright Borders** for focused elements
- **Large Text Sizes** (24sp - 72sp) for readability
- **Rounded Corners** (16dp) for modern look
- **Modal Popups** with semi-transparent backgrounds

## 🎯 Game Controls

| Button | Action |
|--------|--------|
| **D-Pad (↑ ↓ ← →)** | Control snake direction |
| **MENU** | Pause/Resume game |
| **BACK** | Return to main menu |
| **SELECT/OK** | Interact with buttons |

## 📱 Screenshots

### Main Menu
Bright, welcoming interface with large, easy-to-navigate buttons.

### Game Screen
- High-contrast snake (neon green)
- Bright red food
- Cyan grid border
- Yellow score display at the top

### Game Over Popup
- Centered modal with rounded corners
- Two buttons: "Play Again" and "Exit"
- Proper focus navigation with D-pad

### Instructions
- Large, readable text (24sp)
- Emoji icons for visual interest
- Bordered container for better readability

## 🛠️ Technical Details

### Architecture
- **MVVM Pattern**: Clean separation of concerns
- **Compose Navigation**: Smooth screen transitions
- **Immutable State**: Using data classes for game state
- **Coroutines**: For game loop and animations

### Key Components

```
app/src/main/java/com/example/growingsnake/
├── MainActivity.kt           # Entry point
├── Navigation.kt            # Navigation controller
├── ui/
│   ├── GameScreen.kt        # Main game UI and logic
│   ├── MainMenuScreen.kt    # Main menu UI
│   ├── InstructionScreen.kt # Instructions UI
│   └── theme/
│       ├── Color.kt         # High-contrast color definitions
│       ├── Theme.kt         # Material3 TV theme
│       └── Type.kt          # Typography
└── logic/
    ├── GameState.kt         # Game state data classes
    ├── GameLogic.kt         # Core game logic
    └── GameInstructions.kt  # Instructions component
```

### Dependencies
- Jetpack Compose
- Android TV Material3
- Navigation Compose
- Kotlin Coroutines

## 🚀 Getting Started

### Prerequisites
- Android Studio Hedgehog or newer
- Kotlin 1.9+
- Minimum SDK: 21 (Android 5.0)
- Target SDK: 34 (Android 14)

### Installation

1. Clone the repository:
```bash
git clone https://github.com/yourusername/GrowingSnake.git
cd GrowingSnake
```

2. Open the project in Android Studio

3. Wait for Gradle sync to complete

4. Run on an Android TV emulator or device:
   - Select "TV" device type in emulator
   - Or deploy to a physical Android TV device

### Building

```bash
./gradlew assembleDebug
```

The APK will be generated at:
```
app/build/outputs/apk/debug/app-debug.apk
```

## 🎮 How to Play

1. **Start the Game**: Select "Start Game" from the main menu
2. **Control the Snake**: Use D-pad arrows to change direction
3. **Eat Food**: Move the snake to the red circle to grow and score points
4. **Avoid Collisions**: Don't run into yourself
5. **Pause Anytime**: Press MENU to pause
6. **Game Over**: Choose "Play Again" or "Exit" when game ends

## 🏗️ Project Structure

```
GrowingSnake/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/example/growingsnake/
│   │       ├── res/
│   │       └── AndroidManifest.xml
│   ├── build.gradle.kts
│   └── proguard-rules.pro
├── gradle/
├── build.gradle.kts
├── settings.gradle.kts
└── README.md
```

## 🔧 Configuration

### Changing Grid Size
In `GameScreen.kt`, modify the default grid size:
```kotlin
fun GameScreen(
    gridSize: Int = 20, // Change this value (min: 6)
    ...
)
```

### Adjusting Game Speed
In `GameScreen.kt`, modify the game speed:
```kotlin
var gameSpeed by remember { mutableStateOf(150L) } // Milliseconds per move
```

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

**Your Name**
- GitHub: [@yourusername](https://github.com/yourusername)

## 🙏 Acknowledgments

- Built with [Jetpack Compose](https://developer.android.com/jetpack/compose)
- Uses [Android TV Material3](https://developer.android.com/design/ui/tv)
- Inspired by classic Snake games

## 📞 Support

If you have any questions or issues, please open an issue on GitHub.

---

Made with ❤️ for Android TV

