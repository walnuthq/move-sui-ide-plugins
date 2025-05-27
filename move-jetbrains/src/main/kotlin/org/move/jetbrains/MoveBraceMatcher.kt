package org.move.jetbrains

import com.intellij.lang.BracePair
import com.intellij.lang.PairedBraceMatcher
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IElementType
import org.move.jetbrains.psi.MoveTokenTypes

class MoveBraceMatcher : PairedBraceMatcher {
    override fun getPairs(): Array<BracePair> = arrayOf(
        BracePair(MoveTokenTypes.LBRACE, MoveTokenTypes.RBRACE, true),
        BracePair(MoveTokenTypes.LBRACKET, MoveTokenTypes.RBRACKET, false),
        BracePair(MoveTokenTypes.LPAREN, MoveTokenTypes.RPAREN, false)
    )

    override fun isPairedBracesAllowedBeforeType(lbraceType: IElementType, contextType: IElementType?): Boolean = true

    override fun getCodeConstructStart(file: PsiFile?, openingBraceOffset: Int): Int = openingBraceOffset
}