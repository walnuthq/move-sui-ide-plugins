package org.move.jetbrains.lexer

import com.intellij.lexer.LexerBase
import com.intellij.psi.tree.IElementType
import org.move.jetbrains.psi.MoveTokenTypes

class MoveLexer : LexerBase() {
    private var buffer: CharSequence = ""
    private var startOffset = 0
    private var endOffset = 0
    private var currentOffset = 0
    private var currentTokenType: IElementType? = null

    override fun start(buffer: CharSequence, startOffset: Int, endOffset: Int, initialState: Int) {
        this.buffer = buffer
        this.startOffset = startOffset
        this.endOffset = endOffset
        this.currentOffset = startOffset
        advance()
    }

    override fun getState(): Int = 0

    override fun getTokenType(): IElementType? = currentTokenType

    override fun getTokenStart(): Int = startOffset

    override fun getTokenEnd(): Int = currentOffset

    override fun advance() {
        if (currentOffset >= endOffset) {
            currentTokenType = null
            return
        }

        startOffset = currentOffset
        val char = buffer[currentOffset]

        when {
            char.isWhitespace() -> {
                skipWhitespace()
                currentTokenType = MoveTokenTypes.WHITE_SPACE
            }
            char == '/' && currentOffset + 1 < endOffset && buffer[currentOffset + 1] == '/' -> {
                skipLineComment()
                currentTokenType = MoveTokenTypes.LINE_COMMENT
            }
            char == '/' && currentOffset + 1 < endOffset && buffer[currentOffset + 1] == '*' -> {
                skipBlockComment()
                currentTokenType = MoveTokenTypes.BLOCK_COMMENT
            }
            char == '"' -> {
                skipString()
                currentTokenType = MoveTokenTypes.STRING_LITERAL
            }
            char.isLetter() || char == '_' -> {
                skipIdentifier()
                val text = buffer.subSequence(startOffset, currentOffset).toString()
                currentTokenType = when (text) {
                    "module", "script", "public", "friend", "fun", "struct", "const", 
                    "let", "if", "else", "while", "loop", "return", "abort", "acquires",
                    "as", "break", "continue", "copy", "move", "has", "entry", "address",
                    "use", "true", "false", "mut", "ref", "spec", "invariant", "ensures",
                    "requires", "aborts_if", "modifies", "include", "apply", "pragma",
                    "native", "store", "drop", "key", "forall", "exists", "choose",
                    "min", "global", "local", "define", "schema", "aborts_with", "succeeds_if" -> MoveTokenTypes.KEYWORD
                    else -> MoveTokenTypes.IDENTIFIER
                }
            }
            char.isDigit() -> {
                skipNumber()
                currentTokenType = MoveTokenTypes.NUMBER
            }
            char in "{}[]().,;:" -> {
                currentOffset++
                currentTokenType = when (char) {
                    '{' -> MoveTokenTypes.LBRACE
                    '}' -> MoveTokenTypes.RBRACE
                    '[' -> MoveTokenTypes.LBRACKET
                    ']' -> MoveTokenTypes.RBRACKET
                    '(' -> MoveTokenTypes.LPAREN
                    ')' -> MoveTokenTypes.RPAREN
                    ',' -> MoveTokenTypes.COMMA
                    '.' -> MoveTokenTypes.DOT
                    ';' -> MoveTokenTypes.SEMICOLON
                    ':' -> MoveTokenTypes.COLON
                    else -> MoveTokenTypes.BAD_CHARACTER
                }
            }
            else -> {
                currentOffset++
                currentTokenType = MoveTokenTypes.BAD_CHARACTER
            }
        }
    }

    private fun skipWhitespace() {
        while (currentOffset < endOffset && buffer[currentOffset].isWhitespace()) {
            currentOffset++
        }
    }

    private fun skipLineComment() {
        currentOffset += 2
        while (currentOffset < endOffset && buffer[currentOffset] != '\n') {
            currentOffset++
        }
    }

    private fun skipBlockComment() {
        currentOffset += 2
        while (currentOffset < endOffset - 1) {
            if (buffer[currentOffset] == '*' && buffer[currentOffset + 1] == '/') {
                currentOffset += 2
                break
            }
            currentOffset++
        }
    }

    private fun skipString() {
        currentOffset++ // skip opening quote
        while (currentOffset < endOffset) {
            if (buffer[currentOffset] == '"') {
                currentOffset++
                break
            }
            if (buffer[currentOffset] == '\\' && currentOffset + 1 < endOffset) {
                currentOffset += 2 // skip escape sequence
            } else {
                currentOffset++
            }
        }
    }

    private fun skipIdentifier() {
        while (currentOffset < endOffset && 
               (buffer[currentOffset].isLetterOrDigit() || buffer[currentOffset] == '_')) {
            currentOffset++
        }
    }

    private fun skipNumber() {
        while (currentOffset < endOffset && buffer[currentOffset].isDigit()) {
            currentOffset++
        }
    }

    override fun getBufferSequence(): CharSequence = buffer

    override fun getBufferEnd(): Int = endOffset
}