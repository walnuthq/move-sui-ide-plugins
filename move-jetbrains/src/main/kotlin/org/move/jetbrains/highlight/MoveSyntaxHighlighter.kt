package org.move.jetbrains.highlight

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType
import org.move.jetbrains.lexer.MoveLexer
import org.move.jetbrains.psi.MoveTokenTypes

class MoveSyntaxHighlighter : SyntaxHighlighterBase() {
    override fun getHighlightingLexer(): Lexer = MoveLexer()

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {
        return when (tokenType) {
            MoveTokenTypes.KEYWORD -> arrayOf(KEYWORD)
            MoveTokenTypes.STRING_LITERAL -> arrayOf(STRING)
            MoveTokenTypes.NUMBER -> arrayOf(NUMBER)
            MoveTokenTypes.LINE_COMMENT, MoveTokenTypes.BLOCK_COMMENT -> arrayOf(COMMENT)
            MoveTokenTypes.IDENTIFIER -> arrayOf(IDENTIFIER)
            MoveTokenTypes.LBRACE, MoveTokenTypes.RBRACE -> arrayOf(BRACES)
            MoveTokenTypes.LBRACKET, MoveTokenTypes.RBRACKET -> arrayOf(BRACKETS)
            MoveTokenTypes.LPAREN, MoveTokenTypes.RPAREN -> arrayOf(PARENTHESES)
            MoveTokenTypes.COMMA -> arrayOf(COMMA)
            MoveTokenTypes.DOT -> arrayOf(DOT)
            MoveTokenTypes.SEMICOLON -> arrayOf(SEMICOLON)
            MoveTokenTypes.BAD_CHARACTER -> arrayOf(BAD_CHARACTER)
            else -> emptyArray()
        }
    }

    companion object {
        val KEYWORD = TextAttributesKey.createTextAttributesKey("MOVE_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD)
        val STRING = TextAttributesKey.createTextAttributesKey("MOVE_STRING", DefaultLanguageHighlighterColors.STRING)
        val NUMBER = TextAttributesKey.createTextAttributesKey("MOVE_NUMBER", DefaultLanguageHighlighterColors.NUMBER)
        val COMMENT = TextAttributesKey.createTextAttributesKey("MOVE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
        val IDENTIFIER = TextAttributesKey.createTextAttributesKey("MOVE_IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER)
        val BRACES = TextAttributesKey.createTextAttributesKey("MOVE_BRACES", DefaultLanguageHighlighterColors.BRACES)
        val BRACKETS = TextAttributesKey.createTextAttributesKey("MOVE_BRACKETS", DefaultLanguageHighlighterColors.BRACKETS)
        val PARENTHESES = TextAttributesKey.createTextAttributesKey("MOVE_PARENTHESES", DefaultLanguageHighlighterColors.PARENTHESES)
        val COMMA = TextAttributesKey.createTextAttributesKey("MOVE_COMMA", DefaultLanguageHighlighterColors.COMMA)
        val DOT = TextAttributesKey.createTextAttributesKey("MOVE_DOT", DefaultLanguageHighlighterColors.DOT)
        val SEMICOLON = TextAttributesKey.createTextAttributesKey("MOVE_SEMICOLON", DefaultLanguageHighlighterColors.SEMICOLON)
        val BAD_CHARACTER = TextAttributesKey.createTextAttributesKey("MOVE_BAD_CHARACTER", DefaultLanguageHighlighterColors.INVALID_STRING_ESCAPE)
    }
}