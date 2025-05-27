package org.move.jetbrains.completion

import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.PsiElement
import com.intellij.util.ProcessingContext
import org.move.jetbrains.MoveIcons
import org.move.jetbrains.MoveLanguage

class MoveCompletionContributor : CompletionContributor() {
    init {
        // Add completion for Move keywords
        extend(
            CompletionType.BASIC,
            PlatformPatterns.psiElement().withLanguage(MoveLanguage),
            object : CompletionProvider<CompletionParameters>() {
                override fun addCompletions(
                    parameters: CompletionParameters,
                    context: ProcessingContext,
                    result: CompletionResultSet
                ) {
                    val element = parameters.position
                    val prefix = result.prefixMatcher.prefix
                    
                    // Add keyword completions based on context
                    if (shouldSuggestTopLevelKeywords(element)) {
                        addTopLevelKeywords(result)
                    }
                    
                    if (shouldSuggestStatementKeywords(element)) {
                        addStatementKeywords(result)
                    }
                    
                    if (shouldSuggestTypeKeywords(element)) {
                        addTypeKeywords(result)
                    }
                    
                    if (shouldSuggestSpecKeywords(element)) {
                        addSpecKeywords(result)
                    }
                }
            }
        )
    }
    
    private fun shouldSuggestTopLevelKeywords(element: PsiElement): Boolean {
        // TODO: Implement proper context detection
        // For now, suggest top-level keywords everywhere
        return true
    }
    
    private fun shouldSuggestStatementKeywords(element: PsiElement): Boolean {
        // TODO: Implement proper context detection for statement-level keywords
        return true
    }
    
    private fun shouldSuggestTypeKeywords(element: PsiElement): Boolean {
        // TODO: Implement proper context detection for type keywords
        return true
    }
    
    private fun shouldSuggestSpecKeywords(element: PsiElement): Boolean {
        // TODO: Implement proper context detection for specification keywords
        return true
    }
    
    private fun addTopLevelKeywords(result: CompletionResultSet) {
        val topLevelKeywords = listOf(
            KeywordInfo("module", "module <name> {\n    \$END\$\n}", "Define a module"),
            KeywordInfo("script", "script {\n    \$END\$\n}", "Define a script"),
            KeywordInfo("address", "address <name> {\n    \$END\$\n}", "Define an address block")
        )
        
        topLevelKeywords.forEach { keyword ->
            result.addElement(
                LookupElementBuilder.create(keyword.name)
                    .withIcon(MoveIcons.FILE)
                    .withTypeText(keyword.description)
                    .withInsertHandler { context, _ ->
                        if (keyword.template != null) {
                            insertTemplate(context, keyword.template)
                        }
                    }
                    .bold()
            )
        }
    }
    
    private fun addStatementKeywords(result: CompletionResultSet) {
        val statementKeywords = listOf(
            KeywordInfo("public", null, "Public visibility"),
            KeywordInfo("entry", null, "Entry function modifier"),
            KeywordInfo("fun", "fun <name>() {\n    \$END\$\n}", "Define a function"),
            KeywordInfo("struct", "struct <name> {\n    \$END\$\n}", "Define a struct"),
            KeywordInfo("const", "const <NAME>: <type> = ", "Define a constant"),
            KeywordInfo("let", "let <name> = ", "Variable binding"),
            KeywordInfo("if", "if (<condition>) {\n    \$END\$\n}", "If statement"),
            KeywordInfo("else", "else {\n    \$END\$\n}", "Else clause"),
            KeywordInfo("while", "while (<condition>) {\n    \$END\$\n}", "While loop"),
            KeywordInfo("loop", "loop {\n    \$END\$\n}", "Infinite loop"),
            KeywordInfo("return", "return ", "Return from function"),
            KeywordInfo("abort", "abort ", "Abort execution"),
            KeywordInfo("break", null, "Break from loop"),
            KeywordInfo("continue", null, "Continue to next iteration"),
            KeywordInfo("move", null, "Move value"),
            KeywordInfo("copy", null, "Copy value"),
            KeywordInfo("use", "use ", "Import declaration"),
            KeywordInfo("friend", "friend ", "Friend declaration"),
            KeywordInfo("native", "native fun <name>();", "Native function declaration"),
            KeywordInfo("mut", "&mut ", "Mutable reference"),
            KeywordInfo("ref", "& ", "Immutable reference"),
            KeywordInfo("global", "global<<Type>>", "Global storage operation"),
            KeywordInfo("local", null, "Local variable")
        )
        
        statementKeywords.forEach { keyword ->
            result.addElement(
                LookupElementBuilder.create(keyword.name)
                    .withIcon(MoveIcons.FILE)
                    .withTypeText(keyword.description)
                    .withInsertHandler { context, _ ->
                        if (keyword.template != null) {
                            insertTemplate(context, keyword.template)
                        }
                    }
            )
        }
    }
    
    private fun addTypeKeywords(result: CompletionResultSet) {
        val typeKeywords = listOf(
            KeywordInfo("has", null, "Resource ability"),
            KeywordInfo("store", null, "Store ability"),
            KeywordInfo("drop", null, "Drop ability"),
            KeywordInfo("key", null, "Key ability"),
            KeywordInfo("acquires", null, "Acquires global resource"),
            KeywordInfo("as", null, "Type alias"),
            KeywordInfo("true", null, "Boolean true"),
            KeywordInfo("false", null, "Boolean false")
        )
        
        typeKeywords.forEach { keyword ->
            result.addElement(
                LookupElementBuilder.create(keyword.name)
                    .withIcon(MoveIcons.FILE)
                    .withTypeText(keyword.description)
            )
        }
    }
    
    private fun addSpecKeywords(result: CompletionResultSet) {
        val specKeywords = listOf(
            KeywordInfo("spec", "spec <name> {\n    \$END\$\n}", "Specification block"),
            KeywordInfo("schema", "schema <name> {\n    \$END\$\n}", "Define a schema"),
            KeywordInfo("invariant", "invariant ", "Define an invariant"),
            KeywordInfo("ensures", "ensures ", "Post-condition"),
            KeywordInfo("requires", "requires ", "Pre-condition"),
            KeywordInfo("aborts_if", "aborts_if ", "Abort condition"),
            KeywordInfo("aborts_with", "aborts_with ", "Abort code specification"),
            KeywordInfo("succeeds_if", "succeeds_if ", "Success condition"),
            KeywordInfo("modifies", "modifies ", "Modifies clause"),
            KeywordInfo("include", "include ", "Include schema"),
            KeywordInfo("apply", "apply ", "Apply schema"),
            KeywordInfo("pragma", "pragma ", "Pragma directive"),
            KeywordInfo("forall", "forall ", "Universal quantifier"),
            KeywordInfo("exists", "exists ", "Existential quantifier"),
            KeywordInfo("choose", "choose ", "Choice operator"),
            KeywordInfo("min", "min ", "Minimum operator"),
            KeywordInfo("define", "define ", "Define helper function")
        )
        
        specKeywords.forEach { keyword ->
            result.addElement(
                LookupElementBuilder.create(keyword.name)
                    .withIcon(MoveIcons.FILE)
                    .withTypeText(keyword.description)
                    .withInsertHandler { context, _ ->
                        if (keyword.template != null) {
                            insertTemplate(context, keyword.template)
                        }
                    }
            )
        }
    }
    
    private fun insertTemplate(context: InsertionContext, template: String) {
        val document = context.document
        val startOffset = context.startOffset
        val tailOffset = context.tailOffset
        
        // Delete the keyword that was just inserted
        document.deleteString(startOffset, tailOffset)
        
        // Insert the template
        val processedTemplate = template.replace("\$END\$", "")
        document.insertString(startOffset, processedTemplate)
        
        // Position cursor at $END$ marker location
        val endMarkerPos = template.indexOf("\$END\$")
        if (endMarkerPos != -1) {
            context.editor.caretModel.moveToOffset(startOffset + endMarkerPos)
        }
    }
    
    private data class KeywordInfo(
        val name: String,
        val template: String?,
        val description: String
    )
}