# Move IDE Plugins (POC)

A collection of IDE plugins and language tools for the `Move` programming language, focusing to `Sui` blockchain platforms.

> ℹ️ **Info:** This repository is only a Proof of Concept implementation of the Move Language Server Protocol, along with sample editor extensions for VSCode and JetBrains. It was developed as part of a grant application submitted to the Sui Foundation.

## Demo

https://github.com/user-attachments/assets/3d14feba-4603-4b8e-9e6f-b433fd0f0d59

## Components

This repository contains three main components:

### 1. Move Language Server Protocol (LSP)
A language server implementation that provides IDE features through the Language Server Protocol.

**Features:**
- Syntax checking and error diagnostics
- Code completion
- Go to definition
- Hover information
- Document formatting

**Directory:** `move-lsp/`

### 2. VSCode Extension
Official Visual Studio Code extension for Move language support.

**Features:**
- Syntax highlighting
- LSP integration
- Move file icons
- Code snippets
- Integrated terminal commands

**Directory:** `move-vscode/`

### 3. JetBrains Plugin
IntelliJ IDEA plugin for Move language support (compatible with all JetBrains IDEs).

**Features:**
- Syntax highlighting
- Comment support (line and block)
- Brace matching
- Code completion
- File type association

**Directory:** `move-jetbrains/`

## Quick Start

### VSCode Extension
```bash
cd move-vscode
npm install
# Package the extension
vsce package
# Install the generated .vsix file in VSCode
```

### JetBrains Plugin
```bash
cd move-jetbrains
./gradlew build
# Find the plugin at: build/distributions/move-jetbrains-*.zip
```

### Language Server (Manual build)
```bash
cd move-lsp
npm install
# The LSP server requires a communication protocol flag
node server.js --stdio  # For testing with stdio
# Or use with the VSCode extension which handles this automatically
```

## Requirements

- **Node.js** 16+ (for LSP and VSCode extension)
- **Java** 17+ (for JetBrains plugin)
- **npm** or **yarn**
- **VSCode** (for VSCode extension)
- **IntelliJ IDEA** 2023.2+ (for JetBrains plugin)

## Development

Each component has its own development setup:

- **VSCode Extension**: See `move-vscode/README.md`
- **JetBrains Plugin**: See `move-jetbrains/HowToRun.md`
- **LSP Server**: See `move-lsp/README.md`

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request. For major changes, please open an issue first to discuss what you would like to change.

## License

This project is licensed under the Apache 2.0 License - see the LICENSE file for details.

## Related Projects

- [Move on Aptos](https://github.com/aptos-labs/aptos-core/tree/main/third_party/move)
- [Move Language](https://github.com/move-language/move)
- [Sui](https://github.com/MystenLabs/sui)

## Support

For issues and feature requests, please use the GitHub issue tracker.
