package io.github.riej.lsl.psi

import com.intellij.icons.AllIcons
import com.intellij.lang.ASTNode
import com.intellij.lang.documentation.DocumentationMarkup
import com.intellij.lang.documentation.DocumentationSettings
import com.intellij.openapi.editor.richcopy.HtmlSyntaxInfoUtil
import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.PsiElement
import io.github.riej.lsl.LslPrimitiveType
import io.github.riej.lsl.documentation.DocumentationUtils
import io.github.riej.lsl.documentation.LslDocumentedElement
import io.github.riej.lsl.parser.LslTypes
import io.github.riej.lsl.syntax.LslSyntaxHighlighter
import javax.swing.Icon

class LslGlobalVariable(node: ASTNode) : ASTWrapperLslNamedElement(node), NavigatablePsiElement, LslVariable,
    LslDocumentedElement {
    override val lslType: LslPrimitiveType
        get() = LslPrimitiveType.fromString(typeNameEl?.text)

    val typeNameEl: PsiElement?
        get() = findChildByType(LslTypes.TYPE_NAME)

    override val expression: LslExpression?
        get() = findChildByType(LslTypes.EXPRESSIONS)

    override fun getNavigationElement(): PsiElement =
        this.identifyingElement ?: this

    override fun getIcon(unused: Boolean): Icon = AllIcons.Nodes.Gvariable

    override fun generateDocumentation(): String {
        val sb = StringBuilder()

        sb.append(DocumentationMarkup.DEFINITION_START)
        HtmlSyntaxInfoUtil.appendStyledSpan(
            sb,
            LslSyntaxHighlighter.TYPENAME,
            lslType.toString(),
            DocumentationSettings.getHighlightingSaturation(true)
        )
        sb.append(" $name")
        val expression = expression
        if (expression != null) {
            sb.append(" = ${expression.text}")
        }
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