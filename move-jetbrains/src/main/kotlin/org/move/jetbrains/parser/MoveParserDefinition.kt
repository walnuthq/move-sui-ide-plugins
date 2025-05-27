package org.move.jetbrains.parser

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import org.move.jetbrains.MoveLanguage
import org.move.jetbrains.lexer.MoveLexer
import org.move.jetbrains.psi.MoveFile
import org.move.jetbrains.psi.MoveTokenTypes

class MoveParserDefinition : ParserDefinition {
    override fun createLexer(project: Project?): Lexer = MoveLexer()

    override fun createParser(project: Project?): PsiParser = MoveParser()

    override fun getFileNodeType(): IFileElementType = FILE

    override fun getCommentTokens(): TokenSet = COMMENTS

    override fun getStringLiteralElements(): TokenSet = STRINGS

    override fun createElement(node: ASTNode): PsiElement = MoveTokenTypes.Factory.createElement(node)

    override fun createFile(viewProvider: FileViewProvider): PsiFile = MoveFile(viewProvider)

    companion object {
        val FILE = IFileElementType("MOVE_FILE", MoveLanguage)
        val COMMENTS = TokenSet.create(MoveTokenTypes.LINE_COMMENT, MoveTokenTypes.BLOCK_COMMENT)
        val STRINGS = TokenSet.create(MoveTokenTypes.STRING_LITERAL)
    }
}