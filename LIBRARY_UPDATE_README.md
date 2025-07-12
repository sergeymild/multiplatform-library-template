# Kotlin Multiplatform Library Update Guide

This README provides an overview of the guides and scripts available for updating the Kotlin Multiplatform library in the React Native component.

## Overview

The project consists of:
1. A Kotlin Multiplatform library (`library` module) that generates:
   - Android Archive (AAR) files for Android
   - iOS Framework for iOS
2. A React Native component (`react-native-cmp`) that depends on this library
3. Example apps that use the component

## Available Guides and Scripts

### Guides

1. **[AAR Update Guide](AAR_UPDATE_GUIDE.md)**
   - Detailed instructions on how to update the Android AAR file
   - Step-by-step process for building and integrating the AAR
   - Automation options and troubleshooting tips

2. **[iOS Framework Update Guide](IOS_FRAMEWORK_UPDATE_GUIDE.md)**
   - Detailed instructions on how to update the iOS Framework
   - Step-by-step process for generating and integrating the Framework
   - Automation options and troubleshooting tips

### Scripts

1. **[update-library.sh](update-library.sh)**
   - Automated script to update both Android and iOS libraries
   - Handles building, copying, and integrating the libraries
   - Includes error handling and logging
   - Creates backups of previous versions

## Quick Start

To update both Android and iOS libraries in one go:

```bash
# Make sure the script is executable
chmod +x update-library.sh

# Run the script
./update-library.sh
```

## Manual Update Process

If you prefer to update the libraries manually or need more control over the process:

### For Android:

```bash
# Build the library
./gradlew :library:assembleRelease

# The AAR will be automatically copied to the React Native component
# when building the component or example app
```

### For iOS:

```bash
# Generate the iOS Framework
./gradlew :library:generateDummyFramework

# Update the example app's iOS project
cd react-native-cmp/example/ios
pod install
```

## Troubleshooting

If you encounter issues during the update process, refer to the troubleshooting sections in the respective guides:
- [Android Troubleshooting](AAR_UPDATE_GUIDE.md#potential-issues-and-solutions)
- [iOS Troubleshooting](IOS_FRAMEWORK_UPDATE_GUIDE.md#potential-issues-and-solutions)

## Contributing

If you improve the update process or scripts, please update the corresponding guides and this README to reflect the changes.