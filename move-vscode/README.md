# Move Language VSCode Extension

A simple extension for Move language support in Visual Studio Code.

## Features

- Syntax highlighting for Move language (`.move` files)
- Basic language server capabilities
- Semantic highlighting for 'module' keyword
- Auto-completion for Move keywords

## Installation

### Development

1. Clone this repository
2. Open it in Visual Studio Code
3. Run `npm install` in both the `move-vscode` and `move-lsp` directories
4. Press F5 to start debugging and launch a new VSCode instance with the extension loaded

or

```bash
./package-extension.sh
# it will create:
move-vscode/move-language-0.1.0.vsix
```
Open VSCode and go to `View->Command Pallet->Install From VSIX->Navigat to the move-language-0.1.0.vsix`.

### Usage

The extension will automatically activate when you open a `.move` file.

## Requirements

- Visual Studio Code version 1.80.0 or higher

## Known Issues

This is a minimal implementation for demonstration purposes. It only highlights the 'module' keyword using semantic tokens and provides basic Move language features.
