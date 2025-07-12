#!/bin/bash
# update-library.sh
# Script to update both Android AAR and iOS Framework for the React Native component

# Print a message with a timestamp
log() {
  echo "[$(date '+%Y-%m-%d %H:%M:%S')] $1"
}

# Check if a command exists
command_exists() {
  command -v "$1" >/dev/null 2>&1
}

# Check for required tools
check_requirements() {
  log "Checking requirements..."
  
  if ! command_exists gradle && ! command_exists ./gradlew; then
    log "Error: Gradle is not installed and no Gradle wrapper found."
    exit 1
  fi
  
  if ! command_exists npm; then
    log "Error: npm is not installed."
    exit 1
  fi
  
  if ! command_exists pod; then
    log "Warning: CocoaPods is not installed. iOS updates will not work."
  fi
}

# Build the library for Android
build_android() {
  log "Building library for Android..."
  ./gradlew :library:assembleRelease
  
  if [ $? -ne 0 ]; then
    log "Error: Failed to build Android library."
    exit 1
  fi
  
  log "Android library built successfully."
}

# Generate the iOS Framework
generate_ios_framework() {
  log "Generating iOS Framework..."
  ./gradlew :library:generateDummyFramework
  
  if [ $? -ne 0 ]; then
    log "Error: Failed to generate iOS Framework."
    exit 1
  fi
  
  log "iOS Framework generated successfully."
}

# Build the React Native component
build_react_native_component() {
  log "Building React Native component..."
  cd react-native-cmp
  npm run prepare
  
  if [ $? -ne 0 ]; then
    log "Error: Failed to build React Native component."
    cd ..
    exit 1
  fi
  
  cd ..
  log "React Native component built successfully."
}

# Update the example app's iOS project
update_example_ios() {
  if command_exists pod; then
    log "Updating example app's iOS project..."
    cd react-native-cmp/example/ios
    pod install
    
    if [ $? -ne 0 ]; then
      log "Error: Failed to update example app's iOS project."
      cd ../../..
      exit 1
    fi
    
    cd ../../..
    log "Example app's iOS project updated successfully."
  else
    log "Skipping iOS update as CocoaPods is not installed."
  fi
}

# Main function
main() {
  log "Starting library update process..."
  
  check_requirements
  
  # Create a backup of the current state
  log "Creating backup of current state..."
  mkdir -p backups
  timestamp=$(date '+%Y%m%d_%H%M%S')
  
  if [ -f "library/build/outputs/aar/library-release.aar" ]; then
    cp "library/build/outputs/aar/library-release.aar" "backups/library-release-${timestamp}.aar"
  fi
  
  # Build for both platforms
  build_android
  generate_ios_framework
  
  # Update React Native component and example app
  build_react_native_component
  update_example_ios
  
  log "Library update complete for both Android and iOS!"
  log "You can now test the changes in the example app."
  log "For Android: cd react-native-cmp/example && npx react-native run-android"
  log "For iOS: cd react-native-cmp/example && npx react-native run-ios"
}

# Run the main function
main