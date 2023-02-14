package io.github.riej.lsl.psi

import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.icons.AllIcons
import com.intellij.lang.ASTNode
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.lang.documentation.DocumentationMarkup
import com.intellij.lang.documentation.DocumentationSettings
import com.intellij.openapi.editor.richcopy.HtmlSyntaxInfoUtil
import com.intellij.psi.*
import io.github.riej.lsl.LslPrimitiveType
import io.github.riej.lsl.LslScopeUtils
import io.github.riej.lsl.annotation.LslAnnotatedElement
import io.github.riej.lsl.annotation.fixes.DeleteElementsFix
import io.github.riej.lsl.annotation.fixes.NavigateToElementFix
import io.github.riej.lsl.documentation.DocumentationUtils
import io.github.riej.lsl.documentation.LslDocumentedElement
import io.github.riej.lsl.parser.LslTypes
import io.github.riej.lsl.references.LslVariableReference
import io.github.riej.lsl.syntax.LslSyntaxHighlighter
import javax.swing.Icon

class LslStatementVariable(node: ASTNode) : ASTWrapperLslNamedElement(node), LslStatement, NavigatablePsiElement, LslVariable,
    LslAnnotatedElement, LslDocumentedElement {
    override val lslType: LslPrimitiveType
        get() = LslPrimitiveType.fromString(findChildByType<PsiElement?>(LslTypes.TYPE_NAME)?.text)

    val expression: LslExpression?
        get() = this.findChildByType(LslTypes.EXPRESSIONS)

    override fun getReference(): PsiReference = LslVariableReference(this)

    override fun getIcon(unused: Boolean): Icon = AllIcons.Nodes.Variable

    override fun annotate(holder: AnnotationHolder) {
        if ((containingFile as? LslFile)?.kwdbData?.availableEvents?.get(name) != null) {
            holder.newAnnotation(HighlightSeverity.ERROR, "Reserved identifier")
                .create()
            return
        }

        val existingIdentifier = LslScopeUtils.findElementByName(this, name)
        if (existingIdentifier != this) {
            var builder = holder.newAnnotation(HighlightSeverity.ERROR, "Redeclared identifier")
                .range(identifyingElement?.textRange ?: textRange)

            if (existingIdentifier is NavigatablePsiElement) {
                builder = builder.withFix(NavigateToElementFix(existingIdentifier, "Navigate to declaration"))
            }

            builder.create()
        }

        if (reference.resolve() == null && identifyingElement != null) {
            holder.newAnnotation(HighlightSeverity.WEAK_WARNING, "Unused variable")
                .highlightType(ProblemHighlightType.LIKE_UNUSED_SYMBOL)
                .withFix(DeleteElementsFix(listOf(this), "Remove state"))
                .range(identifyingElement!!.textRange)
                .create()
        }
    }

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
        if (expression != null) {
            sb.append(" = ${expression!!.text}")
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