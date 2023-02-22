package io.github.riej.lsl.documentation

import com.intellij.lang.documentation.DocumentationSettings
import com.intellij.openapi.editor.richcopy.HtmlSyntaxInfoUtil
import com.intellij.psi.PsiComment
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiWhiteSpace
import io.github.riej.lsl.parser.LslTypes
import io.github.riej.lsl.psi.*
import io.github.riej.lsl.syntax.LslSyntaxHighlighter

object DocumentationUtils {
    /**
     * Collects prepending comments and comments from the same line.
     *
     * Example:
     * <code>
     *
     *     // some comment
     *     // prepending to func
     *     integer SomeFunc() { return 0; }
     *
     *     integer SomeVar; // some comment
     *
     * </code>
     */
    fun commentsToDescription(element: PsiElement): String {
        val sb = StringBuilder()
        var el: PsiElement? = element

        if (element.nextSibling is PsiComment) {
            el = element.nextSibling as PsiComment

            sb.append("<p>")

            when (el.tokenType) {
                LslTypes.LINE_COMMENT -> sb.append(el.text.trimStart('/'))
                LslTypes.BLOCK_COMMENT -> sb.append(el.text.trim('/', '*'))
                else -> sb.append(el.text)
            }

            sb.append("</p>")
        } else {
            while (el != null) {
                el = el.prevSibling

                if (el is PsiWhiteSpace) {
                    if (el.text.split("\n").count() > 2) {
                        break
                    }
                    el = el.prevSibling
                }

                if (el is PsiComment) {
                    sb.append("<p>")

                    when (el.tokenType) {
                        LslTypes.LINE_COMMENT -> sb.append(el.text.trimStart('/'))
                        LslTypes.BLOCK_COMMENT -> sb.append(el.text.trim('/', '*'))
                        else -> sb.append(el.text)
                    }

                    sb.append("</p>")
                } else {
                    break
                }
            }
        }

        return sb.toString()
    }

    fun getUrlFor(name: String?): String? =
        if (name != null) "https://wiki.secondlife.com/wiki/$name" else null

    /**
     * Generates documentation for function and event arguments lists.
     */
    fun makeDocumentationForArguments(argumentList: List<LslArgument>, sb: StringBuilder) {
        HtmlSyntaxInfoUtil.appendStyledSpan(
            sb,
            LslSyntaxHighlighter.PARENTHESES,
            "(",
            DocumentationSettings.getHighlightingSaturation(true)
        )

        if (argumentList.isNotEmpty()) {
            argumentList.forEachIndexed { index, argument ->
                if (index != 0) {
                    HtmlSyntaxInfoUtil.appendStyledSpan(
                        sb,
                        LslSyntaxHighlighter.COMMA,
                        ",",
                        DocumentationSettings.getHighlightingSaturation(true)
                    )
                }

                sb.append("\n    ")
                HtmlSyntaxInfoUtil.appendStyledSpan(
                    sb,
                    LslSyntaxHighlighter.TYPENAME,
                    argument.lslType.toString(),
                    DocumentationSettings.getHighlightingSaturation(true)
                )
                sb.append(" ${argument.name}")
            }

            sb.append("\n")
        }

        HtmlSyntaxInfoUtil.appendStyledSpan(
            sb,
            LslSyntaxHighlighter.PARENTHESES,
            ")",
            DocumentationSettings.getHighlightingSaturation(true)
        )
    }
}