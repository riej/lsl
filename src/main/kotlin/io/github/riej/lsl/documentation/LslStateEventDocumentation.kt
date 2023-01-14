package io.github.riej.lsl.documentation

import com.intellij.lang.documentation.DocumentationMarkup
import com.intellij.lang.documentation.DocumentationSettings
import com.intellij.openapi.editor.richcopy.HtmlSyntaxInfoUtil
import io.github.riej.lsl.syntax.LslSyntaxHighlighter
import io.github.riej.lsl.psi.LslFile
import io.github.riej.lsl.psi.LslStateEvent

object LslStateEventDocumentation {
    fun generateDoc(element: LslStateEvent): String {
        val eventDeclaration = (element.containingFile as LslFile).kwdbData.availableEvents[element.getIdentifier().text] ?: return ""

        val sb = StringBuilder()

        sb.append(DocumentationMarkup.DEFINITION_START)
        sb.append(eventDeclaration.getIdentifier().text)

        DocumentationUtils.documentateArguments(eventDeclaration.argumentList, sb)

        HtmlSyntaxInfoUtil.appendStyledSpan(
            sb,
            LslSyntaxHighlighter.SEMICOLON,
            ";",
            DocumentationSettings.getHighlightingSaturation(true)
        )

        sb.append(DocumentationMarkup.DEFINITION_END)

        val description = DocumentationUtils.commentsToDescription(eventDeclaration)
        if (description.isNotBlank()) {
            sb.append(DocumentationMarkup.CONTENT_START)
            sb.append(description)
            sb.append(DocumentationMarkup.CONTENT_END)
        }

        return sb.toString()
    }
}