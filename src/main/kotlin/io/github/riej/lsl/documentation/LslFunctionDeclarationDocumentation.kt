package io.github.riej.lsl.documentation

import com.intellij.lang.documentation.DocumentationMarkup
import com.intellij.lang.documentation.DocumentationSettings
import com.intellij.openapi.editor.richcopy.HtmlSyntaxInfoUtil
import io.github.riej.lsl.syntax.LslSyntaxHighlighter
import io.github.riej.lsl.psi.LslFunctionDeclaration

object LslFunctionDeclarationDocumentation {
    fun generateDoc(element: LslFunctionDeclaration): String {
        val sb = StringBuilder()

        sb.append(DocumentationMarkup.DEFINITION_START)
        if (element.typeName != null) {
            HtmlSyntaxInfoUtil.appendStyledSpan(
                sb,
                LslSyntaxHighlighter.TYPENAME,
                element.typeName!!.text,
                DocumentationSettings.getHighlightingSaturation(true)
            )
            sb.append(" ")
        }
        sb.append(element.getIdentifier().text)

        DocumentationUtils.documentateArguments(element.argumentList, sb)

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