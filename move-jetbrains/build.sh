#!/bin/bash

# Build script for Move JetBrains plugin

set -e

echo "Building Move JetBrains plugin..."

# Clean previous builds
echo "Cleaning previous builds..."
./gradlew clean

# Run tests
echo "Running tests..."
./gradlew test

# Build the plugin
echo "Building plugin..."
./gradlew buildPlugin

# Check if build was successful
if [ -f "build/distributions/move-jetbrains-0.1.0.zip" ]; then
    echo "✅ Build successful!"
    echo "Plugin file: build/distributions/move-jetbrains-0.1.0.zip"
    echo ""
    echo "To install:"
    echo "1. Open IntelliJ IDEA Ultimate 2023.2 or later"
    echo "2. Go to File > Settings > Plugins"
    echo "3. Click the gear icon and select 'Install Plugin from Disk...'"
    echo "4. Select the generated zip file"
    echo "5. Restart the IDE"
else
    echo "❌ Build failed!"
    exit 1
fi