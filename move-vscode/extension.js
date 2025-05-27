const path = require('path');
const { workspace, ExtensionContext } = require('vscode');
const {
  LanguageClient,
  TransportKind
} = require('vscode-languageclient/node');

let client;

function activate(context) {
  // Path to the server module
  const serverModule = path.join(__dirname, '..', 'move-lsp', 'server.js');
  
  // The debug options for the server
  const debugOptions = { execArgv: ['--nolazy', '--inspect=6009'] };

  // Options to control the language client
  const serverOptions = {
    run: { module: serverModule, transport: TransportKind.ipc },
    debug: {
      module: serverModule,
      transport: TransportKind.ipc,
      options: debugOptions
    }
  };

  // Options to control the language client
  const clientOptions = {
    // Register the server for Move documents
    documentSelector: [
      { scheme: 'file', language: 'move' }
    ],
    synchronize: {
      // Notify the server about file changes
      fileEvents: workspace.createFileSystemWatcher('**/*.move')
    }
  };

  // Create and start the client
  client = new LanguageClient(
    'moveLSP',
    'Move Language Server',
    serverOptions,
    clientOptions
  );

  // Start the client. This will also launch the server
  client.start();
}

function deactivate() {
  if (!client) {
    return undefined;
  }
  return client.stop();
}

module.exports = {
  activate,
  deactivate
};