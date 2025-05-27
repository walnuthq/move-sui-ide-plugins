# Move Language Support for JetBrains IDEs

This plugin provides Move language support for JetBrains IDEs (IntelliJ IDEA Ultimate, WebStorm, PhpStorm, PyCharm Professional, etc.).

## Features

- **Syntax Highlighting**: Full syntax highlighting for `.move` files
- **Language Server Protocol (LSP) Integration**: Connects to the move-lsp server for advanced features
- **Code Completion**: Intelligent code completion for Move keywords and constructs
- **Error Highlighting**: Real-time error detection and highlighting
- **Comment Support**: Line and block comment handling
- **Brace Matching**: Automatic brace, bracket, and parentheses matching

## Requirements

- IntelliJ IDEA Ultimate 2023.2 or later (or other JetBrains IDE with LSP support)
- Node.js (for running the move-lsp server)
- Java 17 or later (for building the plugin)

## Installation

### From Source

1. Clone this repository
2. Navigate to the `move-jetbrains` directory
3. Build the plugin:
   ```bash
   ./gradlew buildPlugin
   ```
4. Install the generated plugin file from `build/distributions/move-jetbrains-*.zip` in your IDE

### From JetBrains Marketplace

_Coming soon - plugin will be published to the marketplace_

## Known Issues

- When running with IntelliJ IDEA 2023.3.4, you may see a GradleJvmSupportMatrix error. This is a bug in the bundled Gradle plugin and does not affect the Move plugin functionality. You can safely ignore this error or disable the Gradle plugin if not needed.

## Development

### Prerequisites

- Java 17 or later
- Gradle (included via wrapper)

### Building

```bash
./gradlew build
```

### Running in Development

```bash
./gradlew runIde
```

### Testing

```bash
./gradlew test
```

## LSP Server Integration

This plugin automatically integrates with the move-lsp server located in the `../move-lsp/` directory relative to current dir. The LSP server provides:

- Code completion
- Diagnostic information (errors and warnings)
- Semantic highlighting
- Go to definition (planned)
- Find references (planned)

## Project Structure

```
move-jetbrains/
├── src/main/kotlin/org/move/jetbrains/
│   ├── MoveLanguage.kt              # Language definition
│   ├── MoveFileType.kt              # File type registration
│   ├── parser/                      # Parser implementation
│   ├── lexer/                       # Lexical analysis
│   ├── psi/                         # Program Structure Interface
│   ├── highlight/                   # Syntax highlighting
│   ├── lsp/                         # LSP integration
│   └── services/                    # IDE services
├── src/main/resources/
│   ├── META-INF/plugin.xml          # Plugin configuration
│   ├── icons/                       # Plugin icons
│   └── messages/                    # Localization
├── build.gradle.kts                 # Build configuration
└── README.md                        # This file
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## License

This project is licensed under the same license as the parent Move project.

## Related Projects

- [move-lsp](../move-lsp/): Language Server Protocol implementation for Move
- [move-vscode](../move-vscode/): Visual Studio Code extension for Move
