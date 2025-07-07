# iOS Example for Kotlin Multiplatform Library

This is an example iOS application that demonstrates how to use the Kotlin Multiplatform library.

## Building and Running

### Prerequisites
- Xcode 13.0 or later
- CocoaPods
- Kotlin Multiplatform Mobile plugin for IntelliJ IDEA or Android Studio

### Steps to build and run

1. Build the Kotlin framework for iOS:
   ```
   ./gradlew :example-ios:buildXcode
   ```

2. Install CocoaPods dependencies:
   ```
   cd example-ios && pod install && cd ..
   ```

3. Open the Xcode project:
   ```
   open example-ios/example-ios.xcodeproj
   ```

4. Run the app on a simulator:
   - In Xcode, select a simulator from the device dropdown in the top toolbar (e.g., "iPhone 14 Pro")
   - Click the "Run" button (triangle play icon) or press Cmd+R
   - The app will build and launch in the selected simulator
   - You should see the Fibonacci sequence displayed in a table view

## How it works

The iOS example app uses the Kotlin Multiplatform library to generate Fibonacci numbers. The library is integrated using CocoaPods, and the Swift code accesses the Kotlin code through the generated framework.

The main components are:
- `AppDelegate.swift`: Sets up the application
- `ViewController.swift`: Displays the Fibonacci numbers in a table view
- `Podfile`: Configures the CocoaPods integration
- `build.gradle.kts`: Configures the Kotlin Multiplatform build

The iOS-specific implementation of the Fibonacci sequence starts with 3 and 4, while the Android implementation starts with 1 and 2, demonstrating platform-specific behavior in a shared codebase.
