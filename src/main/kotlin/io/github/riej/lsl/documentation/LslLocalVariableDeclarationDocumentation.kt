package io.github.riej.lsl.documentation

import com.intellij.lang.documentation.DocumentationMarkup
import com.intellij.lang.documentation.DocumentationSettings
import com.intellij.openapi.editor.richcopy.HtmlSyntaxInfoUtil
import io.github.riej.lsl.syntax.LslSyntaxHighlighter
import io.github.riej.lsl.psi.LslLocalVariableDeclaration

object LslLocalVariableDeclarationDocumentation {
    fun generateDoc(element: LslLocalVariableDeclaration): String {
        val sb = StringBuilder()

        sb.append(DocumentationMarkup.DEFINITION_START)
        HtmlSyntaxInfoUtil.appendStyledSpan(
            sb,
            LslSyntaxHighlighter.TYPENAME,
            element.typeName.text,
            DocumentationSettings.getHighlightingSaturation(true)
        )
        sb.append(" ${element.getIdentifier()?.text}")
        if (element.expression != null) {

            sb.append(" = ${element.expression!!.text}")
        }
        HtmlSyntaxInfoUtil.appendStyledSpan(
            sb,
            LslSyntaxHighlighter.SEMICOLON,
            ";",
            DocumentationSettings.getHighlightingSaturation(true)
        )
        sb.append(DocumentationMarkup.DEFINITION_END)

        val description = DocumentationUtils.commentsToDescription(element)
        if (description.isNotBlank()) {
            sb.append(DocumentationMarkup.CONTENT_START)
            sb.append(description)
            sb.append(DocumentationMarkup.CONTENT_END)
        }

        return sb.toString()
    }
}