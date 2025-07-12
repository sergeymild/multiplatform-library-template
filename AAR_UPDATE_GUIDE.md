# Guide: How to Update the AAR in React Native Library

This guide explains how to update the Android Archive (AAR) file in the React Native library when you make changes to the Kotlin Multiplatform library.

## Overview of the Setup

The project consists of:
1. A Kotlin Multiplatform library (`library` module) that generates AAR files for Android
2. A React Native component (`react-native-cmp`) that depends on this library
3. An example React Native app that uses the component

## Step-by-Step Guide to Update the AAR

### 1. Make Changes to the Kotlin Multiplatform Library

First, make your changes to the Kotlin Multiplatform library code in the `library` module. This could include:
- Updating UI components in the `commonMain` source set
- Adding new functionality
- Fixing bugs
- Updating dependencies

### 2. Build the Library to Generate a New AAR

After making changes, you need to build the library to generate a new AAR file:

```bash
# From the project root directory
./gradlew :library:assembleRelease
```

This command will:
- Compile the library code
- Generate a new AAR file at `library/build/outputs/aar/library-release.aar`

### 3. How the AAR is Copied to the React Native Component

The React Native component has a Gradle task set up to automatically copy the AAR file:

```gradle
// In react-native-cmp/android/build.gradle
task copyLibraryAar(type: Copy) {
  from "${project.projectDir}/../../library/build/outputs/aar/library-release.aar"
  into "${project.projectDir}/libs"
}

// Make sure the copyLibraryAar task runs before the build task
preBuild.dependsOn copyLibraryAar
```

This task copies the AAR file from the library's build directory to the React Native component's `libs` directory.

### 4. Build the React Native Component

To ensure the React Native component uses the updated AAR, build the component:

```bash
# From the react-native-cmp directory
cd react-native-cmp
npm run prepare  # This runs the bob build command defined in package.json
```

### 5. Test the Changes in the Example App

To verify that the changes are working correctly, run the example app:

```bash
# From the react-native-cmp directory
cd example
# For Android
npx react-native run-android
# For iOS
npx react-native run-ios
```

## Automation Options

### Option 1: Script to Automate the Process

You can create a shell script to automate the entire process:

```bash
#!/bin/bash
# update-aar.sh

# Build the library
echo "Building library..."
./gradlew :library:assembleRelease

# Build the React Native component
echo "Building React Native component..."
cd react-native-cmp
npm run prepare
cd ..

echo "AAR update complete!"
```

Make the script executable:
```bash
chmod +x update-aar.sh
```

### Option 2: Add a Gradle Task to the Root Project

You can add a task to the root project's build.gradle file:

```gradle
// In root build.gradle
task updateReactNativeAAR {
  dependsOn ':library:assembleRelease'
  doLast {
    exec {
      workingDir 'react-native-cmp'
      commandLine 'npm', 'run', 'prepare'
    }
    println "AAR update complete!"
  }
}
```

Then run:
```bash
./gradlew updateReactNativeAAR
```

## Potential Issues and Solutions

### 1. Version Mismatch

If you update the library version in `library/build.gradle.kts`, make sure to update any references to that version in other files.

### 2. Dependency Conflicts

If you add new dependencies to the library, make sure they don't conflict with dependencies in the React Native component or example app.

### 3. Compose Version Compatibility

Ensure that the Compose versions used in the library and React Native component are compatible.

### 4. iOS Framework Updates

Remember that for iOS, you need to update the framework separately:

```bash
./gradlew :library:generateDummyFramework
cd react-native-cmp/example/ios
pod install
```

## Conclusion

By following this guide, you can ensure that your React Native component always uses the latest version of your Kotlin Multiplatform library. The automated Gradle tasks make this process relatively seamless, but it's important to test thoroughly after each update to ensure everything works as expected.