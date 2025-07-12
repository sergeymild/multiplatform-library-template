# Guide: How to Update the iOS Framework in React Native Library

This guide explains how to update the iOS Framework in the React Native library when you make changes to the Kotlin Multiplatform library.

## Overview of the Setup

The project consists of:
1. A Kotlin Multiplatform library (`library` module) that generates an iOS Framework
2. A React Native component (`react-native-cmp`) that depends on this library
3. An example React Native app that uses the component

## Step-by-Step Guide to Update the iOS Framework

### 1. Make Changes to the Kotlin Multiplatform Library

First, make your changes to the Kotlin Multiplatform library code in the `library` module. This could include:
- Updating UI components in the `commonMain` source set
- Adding new functionality
- Fixing bugs
- Updating dependencies

### 2. Generate the iOS Framework

After making changes, you need to generate a new iOS Framework:

```bash
# From the project root directory
./gradlew :library:generateDummyFramework
```

This command will:
- Create a dummy framework at `library/build/cocoapods/framework/library.framework`
- This framework is needed for CocoaPods to properly set up the project

### 3. Update the React Native Component's iOS Project

The React Native component uses CocoaPods to integrate the iOS Framework:

```ruby
# In react-native-cmp/Cmp.podspec
s.dependency "library"
```

This tells CocoaPods that the React Native component depends on the library framework.

### 4. Update the Example App's iOS Project

After generating the framework, you need to update the example app's iOS project:

```bash
# From the react-native-cmp/example/ios directory
cd react-native-cmp/example/ios
pod install
```

This command will:
- Install the updated framework into the example app
- Update the Xcode project to use the new framework

### 5. Test the Changes in the Example App

To verify that the changes are working correctly, run the example app:

```bash
# From the react-native-cmp directory
cd example
# For iOS
npx react-native run-ios
```

## Automation Options

### Option 1: Script to Automate the Process

You can create a shell script to automate the entire process:

```bash
#!/bin/bash
# update-ios-framework.sh

# Generate the iOS Framework
echo "Generating iOS Framework..."
./gradlew :library:generateDummyFramework

# Update the example app's iOS project
echo "Updating example app's iOS project..."
cd react-native-cmp/example/ios
pod install
cd ../../..

echo "iOS Framework update complete!"
```

Make the script executable:
```bash
chmod +x update-ios-framework.sh
```

### Option 2: Add a Gradle Task to the Root Project

You can add a task to the root project's build.gradle file:

```gradle
// In root build.gradle
task updateReactNativeIOSFramework {
  dependsOn ':library:generateDummyFramework'
  doLast {
    exec {
      workingDir 'react-native-cmp/example/ios'
      commandLine 'pod', 'install'
    }
    println "iOS Framework update complete!"
  }
}
```

Then run:
```bash
./gradlew updateReactNativeIOSFramework
```

## Potential Issues and Solutions

### 1. CocoaPods Not Finding the Framework

If CocoaPods can't find the framework, make sure:
- The framework has been generated correctly
- The path in the Podfile is correct
- The library.podspec file is correctly configured

### 2. Framework Version Mismatch

If you update the library version in `library/build.gradle.kts`, make sure to update the version in:
- `library/build.gradle.kts` in the `cocoapods` section
- `library.podspec` if it references a specific version

### 3. iOS Deployment Target Compatibility

Ensure that the iOS deployment target in the library matches or is lower than the deployment target in the React Native component and example app.

### 4. Xcode Build Errors

If you encounter Xcode build errors:
- Check that the framework was generated for all required architectures (arm64, x86_64)
- Verify that the Kotlin/Native version is compatible with your Xcode version
- Try cleaning the build folder and rebuilding

### 5. Combined Android and iOS Updates

When updating both Android and iOS, you can use a combined script:

```bash
#!/bin/bash
# update-library.sh

# Build the library for Android
echo "Building library for Android..."
./gradlew :library:assembleRelease

# Generate the iOS Framework
echo "Generating iOS Framework..."
./gradlew :library:generateDummyFramework

# Build the React Native component
echo "Building React Native component..."
cd react-native-cmp
npm run prepare
cd ..

# Update the example app's iOS project
echo "Updating example app's iOS project..."
cd react-native-cmp/example/ios
pod install
cd ../../..

echo "Library update complete for both Android and iOS!"
```

## Conclusion

By following this guide, you can ensure that your React Native component always uses the latest version of your Kotlin Multiplatform library's iOS Framework. The process is slightly different from updating the Android AAR, but the automated scripts can help streamline both processes.