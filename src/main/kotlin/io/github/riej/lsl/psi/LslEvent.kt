package io.github.riej.lsl.psi

import com.intellij.lang.ASTNode
import com.intellij.lang.documentation.DocumentationMarkup
import com.intellij.lang.documentation.DocumentationSettings
import com.intellij.navigation.ItemPresentation
import com.intellij.openapi.editor.richcopy.HtmlSyntaxInfoUtil
import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.PsiElement
import io.github.riej.lsl.KwdbData
import io.github.riej.lsl.LslIcons
import io.github.riej.lsl.documentation.DocumentationUtils
import io.github.riej.lsl.documentation.LslDocumentedElement
import io.github.riej.lsl.parser.LslTypes
import io.github.riej.lsl.syntax.LslSyntaxHighlighter
import javax.swing.Icon

class LslEvent(node: ASTNode) : ASTWrapperLslNamedElement(node), NavigatablePsiElement, LslDocumentedElement,
    ItemPresentation {
    val arguments: List<LslArgument>
        get() = argumentsEl?.arguments.orEmpty()

    val argumentsEl: LslArguments?
        get() = findChildByType(LslTypes.ARGUMENTS)

    val body: LslStatement?
        get() = findChildByType(LslTypes.STATEMENT_BLOCK)

    val parenthesesLeftEl: PsiElement?
        get() = findChildByType(LslTypes.PARENTHESES_LEFT)
    val parenthesesRightEl: PsiElement?
        get() = findChildByType(LslTypes.PARENTHESES_RIGHT)

    override fun getNavigationElement(): PsiElement =
        this.identifyingElement ?: this

    override fun getPresentableText(): String = "$name(${
        arguments.joinToString(", ") { "${it.lslType} ${it.name}" }
    })".trim()

    override fun getIcon(unused: Boolean): Icon = LslIcons.EVENT

    override fun generateDocumentation(): String {
        val eventDeclaration = KwdbData.getInstance(project).events[name] ?: return ""

        val sb = StringBuilder()

        sb.append(DocumentationMarkup.DEFINITION_START)
        sb.append(name)

        DocumentationUtils.makeDocumentationForArguments(arguments, sb)

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