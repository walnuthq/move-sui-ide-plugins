# How to Run

  1. Run in Development Mode (Easiest for testing)

  cd move-jetbrains
  ./gradlew build
  ./gradlew runIde

  The first run downloads:
  - Gradle 8.5
  - IntelliJ IDEA Community Edition
  - All plugin dependencies


 It is built in:
  build/distributions/move-jetbrains-0.1.0.zip

When rebuilding bulding just use:

```
./gradlew buildPlugin
```

  To try the plugin, you now have two options:

  1. Run in development mode:
  ./gradlew runIde
  2. Install the plugin in your existing IntelliJ IDEA:
    - Open IntelliJ IDEA
    - Go to Settings → Plugins → ⚙️ → Install Plugin from Disk
    - Select /Users/djtodorovic/projects/crypto/MOVE/move-on-aptos/move-jetbrains/build/distributions/move-jetbrains-0.1.0.zip
    - Restart the IDE

  The plugin currently provides:
  - Syntax highlighting for .move files
  - Comment support (line and block comments)
  - Brace matching
  - Basic language support

  Note: LSP integration has been temporarily disabled to ensure a successful build. It can be re-enabled once the proper dependencies and API
  compatibility are resolved.

  Prerequisites

  - Java 17+ for building (brew install openjdk@17)
  - Node.js for the LSP server
  - IntelliJ IDEA Ultimate 2023.2+ (or compatible JetBrains IDE)

  The plugin provides syntax highlighting, LSP integration, code completion, and error detection for Move language files (.move).
