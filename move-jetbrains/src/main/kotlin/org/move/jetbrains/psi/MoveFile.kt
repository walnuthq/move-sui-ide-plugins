package org.move.jetbrains.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import org.move.jetbrains.MoveFileType
import org.move.jetbrains.MoveLanguage

class MoveFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, MoveLanguage) {
    override fun getFileType(): FileType = MoveFileType
    override fun toString(): String = "Move File"
}