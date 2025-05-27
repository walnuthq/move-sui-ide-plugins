package org.move.jetbrains.psi

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.tree.IElementType
import org.move.jetbrains.MoveLanguage

class MoveTokenType(debugName: String) : IElementType(debugName, MoveLanguage)

object MoveTokenTypes {
    @JvmField val WHITE_SPACE = MoveTokenType("WHITE_SPACE")
    @JvmField val LINE_COMMENT = MoveTokenType("LINE_COMMENT")
    @JvmField val BLOCK_COMMENT = MoveTokenType("BLOCK_COMMENT")
    @JvmField val STRING_LITERAL = MoveTokenType("STRING_LITERAL")
    @JvmField val NUMBER = MoveTokenType("NUMBER")
    @JvmField val IDENTIFIER = MoveTokenType("IDENTIFIER")
    @JvmField val KEYWORD = MoveTokenType("KEYWORD")
    
    // Punctuation
    @JvmField val LBRACE = MoveTokenType("LBRACE")
    @JvmField val RBRACE = MoveTokenType("RBRACE")
    @JvmField val LBRACKET = MoveTokenType("LBRACKET")
    @JvmField val RBRACKET = MoveTokenType("RBRACKET")
    @JvmField val LPAREN = MoveTokenType("LPAREN")
    @JvmField val RPAREN = MoveTokenType("RPAREN")
    @JvmField val COMMA = MoveTokenType("COMMA")
    @JvmField val DOT = MoveTokenType("DOT")
    @JvmField val SEMICOLON = MoveTokenType("SEMICOLON")
    @JvmField val COLON = MoveTokenType("COLON")
    @JvmField val BAD_CHARACTER = MoveTokenType("BAD_CHARACTER")

    object Factory {
        fun createElement(node: ASTNode): PsiElement {
            return MoveElement(node)
        }
    }
}