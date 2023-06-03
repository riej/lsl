package io.github.riej.lsl.psi

import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.icons.AllIcons
import com.intellij.lang.ASTNode
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.lang.documentation.DocumentationMarkup
import com.intellij.lang.documentation.DocumentationSettings
import com.intellij.openapi.editor.richcopy.HtmlSyntaxInfoUtil
import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.PsiElement
import com.intellij.psi.util.parentsOfType
import io.github.riej.lsl.LslPrimitiveType
import io.github.riej.lsl.LslScopeUtils
import io.github.riej.lsl.annotation.LslAnnotatedElement
import io.github.riej.lsl.annotation.fixes.DeleteElementsFix
import io.github.riej.lsl.annotation.fixes.NavigateToElementFix
import io.github.riej.lsl.documentation.LslDocumentedElement
import io.github.riej.lsl.parser.LslTypes
import io.github.riej.lsl.syntax.LslSyntaxHighlighter
import javax.swing.Icon

class LslArgument(node: ASTNode) : ASTWrapperLslNamedElement(node), NavigatablePsiElement,
    LslVariable, LslAnnotatedElement, LslDocumentedElement {
    override val lslType: LslPrimitiveType
        get() = LslPrimitiveType.fromString(findChildByType<PsiElement?>(LslTypes.TYPE_NAME)?.text)

    override val expression: LslExpression?
        get() = null

    override fun getIcon(unused: Boolean): Icon = AllIcons.Nodes.Variable

    override fun annotate(holder: AnnotationHolder) {
        if ((containingFile as? LslFile)?.kwdbData?.events?.get(name) != null) {
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

        if (identifyingElement != null && usages.isEmpty() && parentsOfType<LslEvent>().none()) {
            val parentChildren = parent.node.getChildren(null).toList()
            val elementsToDelete = listOfNotNull(
                parentChildren
                    .subList(0, parentChildren.indexOf(node))
                    .findLast { it.elementType == LslTypes.COMMA }
                    ?.psi,
                this
            )

            holder.newAnnotation(HighlightSeverity.WEAK_WARNING, "Unused argument")
                .highlightType(ProblemHighlightType.LIKE_UNUSED_SYMBOL)
                .withFix(DeleteElementsFix(elementsToDelete, "Remove unused argument"))
                .range(identifyingElement?.textRange ?: textRange)
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