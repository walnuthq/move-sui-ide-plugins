package org.move.jetbrains

import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

object MoveFileType : LanguageFileType(MoveLanguage) {
    override fun getName(): String = "Move"
    override fun getDescription(): String = "Move language file"
    override fun getDefaultExtension(): String = "move"
    override fun getIcon(): Icon? = MoveIcons.FILE
}