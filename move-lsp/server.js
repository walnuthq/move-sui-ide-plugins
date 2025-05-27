const {
  createConnection,
  TextDocuments,
  ProposedFeatures,
  TextDocumentSyncKind,
  CompletionItem,
  CompletionItemKind
} = require('vscode-languageserver/node');

const { TextDocument } = require('vscode-languageserver-textdocument');

// Create a connection for the server
const connection = createConnection(ProposedFeatures.all);

// Create a text document manager
const documents = new TextDocuments(TextDocument);

let hasConfigurationCapability = false;
let hasWorkspaceFolderCapability = false;

connection.onInitialize((params) => {
  const capabilities = params.capabilities;

  // Does the client support the `workspace/configuration` request?
  hasConfigurationCapability = !!(
    capabilities.workspace && !!capabilities.workspace.configuration
  );
  hasWorkspaceFolderCapability = !!(
    capabilities.workspace && !!capabilities.workspace.workspaceFolders
  );

  const result = {
    capabilities: {
      textDocumentSync: TextDocumentSyncKind.Incremental,
      // Tell the client that this server supports code completion
      completionProvider: {
        resolveProvider: true,
        triggerCharacters: ['.']
      }
    }
  };

  if (hasWorkspaceFolderCapability) {
    result.capabilities.workspace = {
      workspaceFolders: {
        supported: true
      }
    };
  }

  return result;
});

connection.onInitialized(() => {
  if (hasConfigurationCapability) {
    // Register for all configuration changes
    connection.client.register(ProposedFeatures.all.ConfigurationRequest.type);
  }
  if (hasWorkspaceFolderCapability) {
    connection.workspace.onDidChangeWorkspaceFolders(_event => {
      connection.console.log('Workspace folder change event received.');
    });
  }
});

// Define Move language keywords
const moveKeywords = [
  'module', 'script', 'struct', 'fun', 'public', 'friend',
  'const', 'let', 'if', 'else', 'while', 'loop', 'return', 'abort',
  'acquires', 'as', 'break', 'continue', 'copy', 'move', 'has',
  'use', 'native', 'entry', 'address', 'spec', 'pragma', 'invariant',
  'phantom', 'resource', 'init'
];

// Built-in types
const moveTypes = [
  'u8', 'u16', 'u32', 'u64', 'u128', 'u256',
  'bool', 'address', 'vector', 'signer'
];

// The content of a text document has changed
documents.onDidChangeContent(change => {
  validateDocument(change.document);
});

async function validateDocument(textDocument) {
  // Simple validation - could be expanded
  const text = textDocument.getText();
  const diagnostics = [];
  
  // Add validation logic here if needed
  
  // Send the computed diagnostics to VSCode
  connection.sendDiagnostics({ uri: textDocument.uri, diagnostics });
}

// This handler provides the initial list of the completion items
connection.onCompletion(
  (_textDocumentPosition) => {
    // Return keyword and type completions for Move
    const completions = [];
    
    // Add keywords
    moveKeywords.forEach(keyword => {
      completions.push({
        label: keyword,
        kind: CompletionItemKind.Keyword,
        data: { type: 'keyword', value: keyword }
      });
    });
    
    // Add types
    moveTypes.forEach(type => {
      completions.push({
        label: type,
        kind: CompletionItemKind.Class,
        data: { type: 'type', value: type }
      });
    });
    
    return completions;
  }
);

// This handler resolves additional information for the item selected in
// the completion list
connection.onCompletionResolve(
  (item) => {
    if (item.data) {
      if (item.data.type === 'keyword') {
        item.detail = 'Move keyword';
        item.documentation = `The '${item.data.value}' keyword in Move`;
      } else if (item.data.type === 'type') {
        item.detail = 'Move built-in type';
        item.documentation = `The '${item.data.value}' type in Move`;
      }
    }
    return item;
  }
);

// Make the text document manager listen on the connection
// for open, change and close text document events
documents.listen(connection);

// Listen on the connection
connection.listen();