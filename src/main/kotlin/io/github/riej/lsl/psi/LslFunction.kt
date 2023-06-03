package io.github.riej.lsl.psi

import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.icons.AllIcons
import com.intellij.lang.ASTNode
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.lang.documentation.DocumentationMarkup
import com.intellij.lang.documentation.DocumentationSettings
import com.intellij.navigation.ItemPresentation
import com.intellij.openapi.editor.richcopy.HtmlSyntaxInfoUtil
import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import io.github.riej.lsl.LslPrimitiveType
import io.github.riej.lsl.annotation.LslAnnotatedElement
import io.github.riej.lsl.annotation.fixes.DeleteElementsFix
import io.github.riej.lsl.annotation.fixes.NavigateToElementFix
import io.github.riej.lsl.documentation.DocumentationUtils
import io.github.riej.lsl.documentation.LslDocumentedElement
import io.github.riej.lsl.parser.LslTypes
import io.github.riej.lsl.scope.LslPsiScope
import io.github.riej.lsl.syntax.LslSyntaxHighlighter
import javax.swing.Icon

class LslFunction(node: ASTNode) : ASTWrapperLslNamedElement(node), NavigatablePsiElement, LslTypedElement,
    LslAnnotatedElement, LslDocumentedElement, ItemPresentation, LslSymbolDeclaration, LslPsiScope {
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

    val usages: List<PsiElement>
        get() = PsiTreeUtil.collectElements(containingFile) {
            (it is LslExpressionFunctionCall) && (it.function == this)
        }.toList()

    override val declaredElements: List<LslNamedElement>
        get() = arguments

    override fun getPresentableText(): String = "$lslType $name(${
        arguments.joinToString(", ") { "${it.lslType} ${it.name}" }
    })".trim()

    override fun getIcon(unused: Boolean): Icon = AllIcons.Nodes.Function

    override fun annotate(holder: AnnotationHolder) {
        val body = body
        if (body != null && lslType != LslPrimitiveType.VOID && PsiTreeUtil.findChildOfType(
                body,
                LslStatementReturn::class.java
            ) == null
        ) {
            holder.newAnnotation(HighlightSeverity.ERROR, "Missing return statement")
                .highlightType(ProblemHighlightType.ERROR)
                .range(body.node.lastChildNode.textRange)
                .create()
        }

        if ((containingFile as? LslFile)?.kwdbData?.events?.get(name) != null) {
            holder.newAnnotation(HighlightSeverity.ERROR, "Reserved identifier")
                .create()
            return
        }

        val existingIdentifier = scope?.findElementByName(name)
        if (existingIdentifier != this) {
            var builder = holder.newAnnotation(HighlightSeverity.ERROR, "Redeclared identifier")
                .range(identifyingElement?.textRange ?: textRange)

            if (existingIdentifier is NavigatablePsiElement) {
                builder = builder.withFix(NavigateToElementFix(existingIdentifier, "Navigate to declaration"))
            }

            builder.create()
        }

        val identifyingElement = identifyingElement
        if (identifyingElement != null && usages.isEmpty()) {
            holder.newAnnotation(HighlightSeverity.WEAK_WARNING, "Unused function")
                .highlightType(ProblemHighlightType.LIKE_UNUSED_SYMBOL)
                .withFix(DeleteElementsFix(listOf(this), "Remove function"))
                .range(identifyingElement.textRange)
                .create()
        }
    }

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