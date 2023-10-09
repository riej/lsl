package io.github.riej.lsl.psi

import com.intellij.icons.AllIcons
import com.intellij.lang.ASTNode
import com.intellij.lang.documentation.DocumentationMarkup
import com.intellij.lang.documentation.DocumentationSettings
import com.intellij.navigation.ItemPresentation
import com.intellij.openapi.editor.richcopy.HtmlSyntaxInfoUtil
import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.PsiElement
import io.github.riej.lsl.LslPrimitiveType
import io.github.riej.lsl.documentation.DocumentationUtils
import io.github.riej.lsl.documentation.LslDocumentedElement
import io.github.riej.lsl.parser.LslTypes
import io.github.riej.lsl.syntax.LslSyntaxHighlighter
import javax.swing.Icon

class LslFunction(node: ASTNode) : ASTWrapperLslNamedElement(node), NavigatablePsiElement, LslTypedElement,
    LslDocumentedElement, ItemPresentation, LslSymbolDeclaration {
    override val lslType: LslPrimitiveType
        get() = LslPrimitiveType.fromString(typeNameEl?.text)

    val typeNameEl: PsiElement?
        get() = findChildByType(LslTypes.TYPE_NAME)

    val arguments: List<LslArgument>
        get() = argumentsEl?.arguments.orEmpty()

    val argumentsEl: LslArguments?
        get() = findChildByType(LslTypes.ARGUMENTS)

    val body: LslStatement?
        get() = findChildByType(LslTypes.STATEMENT_BLOCK)

    override fun getNavigationElement(): PsiElement =
        this.identifyingElement ?: this

    override fun getPresentableText(): String = "$lslType $name(${
        arguments.joinToString(", ") { "${it.lslType} ${it.name}" }
    })".trim()

    override fun getIcon(unused: Boolean): Icon = AllIcons.Nodes.Function

    override fun generateDocumentation(): String {
        val sb = StringBuilder()

        sb.append(DocumentationMarkup.DEFINITION_START)
        if (lslType != LslPrimitiveType.VOID) {
            HtmlSyntaxInfoUtil.appendStyledSpan(
                sb,
                LslSyntaxHighlighter.TYPENAME,
                lslType.toString(),
                DocumentationSettings.getHighlightingSaturation(true)
            )
            sb.append(" ")
        }
        sb.append(name)

        DocumentationUtils.makeDocumentationForArguments(arguments, sb)

        HtmlSyntaxInfoUtil.appendStyledSpan(
            sb,
            LslSyntaxHighlighter.SEMICOLON,
            ";",
            DocumentationSettings.getHighlightingSaturation(true)
        )

        sb.append(DocumentationMarkup.DEFINITION_END)

        val description = DocumentationUtils.commentsToDescription(this)
        if (description.isNotBlank()) {
            sb.append(DocumentationMarkup.CONTENT_START)
            sb.append(description)
            sb.append(DocumentationMarkup.CONTENT_END)
        }

        return sb.toString()
    }
}