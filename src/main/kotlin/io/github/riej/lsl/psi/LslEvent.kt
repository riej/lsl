package io.github.riej.lsl.psi

import com.intellij.lang.ASTNode
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.lang.documentation.DocumentationMarkup
import com.intellij.lang.documentation.DocumentationSettings
import com.intellij.navigation.ItemPresentation
import com.intellij.openapi.editor.richcopy.HtmlSyntaxInfoUtil
import com.intellij.openapi.util.TextRange
import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.PsiElement
import com.intellij.refactoring.suggested.endOffset
import com.intellij.refactoring.suggested.startOffset
import io.github.riej.lsl.LslIcons
import io.github.riej.lsl.LslPrimitiveType
import io.github.riej.lsl.LslScopeUtils
import io.github.riej.lsl.annotation.LslAnnotatedElement
import io.github.riej.lsl.annotation.fixes.DeleteElementsFix
import io.github.riej.lsl.annotation.fixes.NavigateToElementFix
import io.github.riej.lsl.documentation.DocumentationUtils
import io.github.riej.lsl.documentation.LslDocumentedElement
import io.github.riej.lsl.parser.LslTypes
import io.github.riej.lsl.syntax.LslSyntaxHighlighter
import javax.swing.Icon
import kotlin.math.min

class LslEvent(node: ASTNode) : ASTWrapperLslNamedElement(node), NavigatablePsiElement, LslDocumentedElement,
    ItemPresentation, LslAnnotatedElement {
    val arguments: List<LslArgument>
        get() = argumentsEl?.arguments.orEmpty()

    val argumentsEl: LslArguments?
        get() = this.findChildByType(LslTypes.ARGUMENTS)

    val body: LslStatement?
        get() = this.findChildByType(LslTypes.STATEMENT_BLOCK)

    override fun getPresentableText(): String = "$name(${
        arguments.joinToString(", ") { "${it.lslType} ${it.name}" }
    })".trim()

    override fun getIcon(unused: Boolean): Icon = LslIcons.EVENT

    override fun annotate(holder: AnnotationHolder) {
        val definition = LslScopeUtils.getEventByName(this, name)
        if (definition == null) {
            holder.newAnnotation(HighlightSeverity.ERROR, "Unknown state event")
                .range(identifyingElement?.textRange ?: textRange)
                .create()
            return
        }

        val existingIdentifier = parent.children.firstOrNull { (it as? LslEvent)?.name == name }
        if (existingIdentifier != this) {
            var builder = holder.newAnnotation(HighlightSeverity.ERROR, "Redeclared identifier")
                .range(identifyingElement?.textRange ?: textRange)

            if (existingIdentifier is NavigatablePsiElement) {
                builder = builder.withFix(NavigateToElementFix(existingIdentifier, "Navigate to declaration"))
            }

            builder.create()
        }

        if (arguments.isNotEmpty()) {
            (0 until min(arguments.size, definition.arguments.size)).forEach { i ->
                try {
                    val resultType = arguments[i].lslType.operationTo(definition.arguments[i].lslType, LslTypes.ASSIGN)
                    if (resultType == LslPrimitiveType.INVALID) {
                        throw LslPrimitiveType.TypeMismatch(definition.arguments[i].lslType, resultType)
                    }
                } catch (e: LslPrimitiveType.TypeMismatch) {
                    holder.newAnnotation(HighlightSeverity.ERROR, e.message!!)
                        .range(arguments[i].textRange)
                        .create()
                }
            }
        }

        val parenthesesLeftEl = findChildByType<PsiElement>(LslTypes.PARENTHESES_LEFT)
        val parenthesesRightEl = findChildByType<PsiElement>(LslTypes.PARENTHESES_RIGHT)

        if (arguments.size < definition.arguments.size) {
            holder.newAnnotation(
                HighlightSeverity.ERROR,
                "Wrong arguments count (expected ${definition.arguments.size}, got ${arguments.size})"
            )
                .range(
                    parenthesesRightEl?.textRange ?: arguments.lastOrNull()?.textRange ?: parenthesesLeftEl?.textRange
                    ?: textRange
                )
                .create()
        } else if (arguments.size > definition.arguments.size) {
            val range = TextRange.create(
                if (definition.arguments.isNotEmpty())
                    arguments[definition.arguments.size - 1].endOffset
                else
                    parenthesesLeftEl?.endOffset ?: identifyingElement?.endOffset ?: startOffset,
                parenthesesRightEl?.startOffset ?: endOffset,
            )
            val elementsToRemove = children.filter { range.contains(it.textRange) }

            holder.newAnnotation(
                HighlightSeverity.ERROR,
                "Wrong arguments count (expected ${definition.arguments.size}, got ${arguments.size})"
            )
                .range(range)
                .withFix(DeleteElementsFix(elementsToRemove, "Remove extra arguments"))
                .create()
        }
    }

    override fun generateDocumentation(): String {
        val eventDeclaration = (containingFile as LslFile).kwdbData.events[name] ?: return ""

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