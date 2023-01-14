package io.github.riej.lsl.documentation

import com.intellij.lang.documentation.DocumentationMarkup
import com.intellij.lang.documentation.DocumentationSettings
import com.intellij.openapi.editor.richcopy.HtmlSyntaxInfoUtil
import io.github.riej.lsl.syntax.LslSyntaxHighlighter
import io.github.riej.lsl.psi.LslArgument

object LslArgumentDocumentation {
    fun generateDoc(element: LslArgument): String {
        val sb = StringBuilder()

        sb.append(DocumentationMarkup.DEFINITION_START)
        HtmlSyntaxInfoUtil.appendStyledSpan(
            sb,
            LslSyntaxHighlighter.TYPENAME,
            element.typeName.text,
            DocumentationSettings.getHighlightingSaturation(true)
        )
        sb.append(" ${element.getIdentifier()?.text}")
        HtmlSyntaxInfoUtil.appendStyledSpan(
            sb,
            LslSyntaxHighlighter.SEMICOLON,
            ";",
            DocumentationSettings.getHighlightingSaturation(true)
        )
        sb.append(DocumentationMarkup.DEFINITION_END)

        return sb.toString()
    }
}