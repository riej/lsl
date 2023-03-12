package io.github.riej.lsl.structure

import com.intellij.lang.ASTNode
import com.intellij.lang.folding.FoldingBuilderEx
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.navigation.ItemPresentation
import com.intellij.openapi.editor.Document
import com.intellij.openapi.project.DumbAware
import com.intellij.psi.PsiElement
import com.intellij.psi.util.elementType
import io.github.riej.lsl.parser.LslTypes
import io.github.riej.lsl.psi.LslEvent

class LslFoldingBuilder : FoldingBuilderEx(), DumbAware {
    override fun buildFoldRegions(root: PsiElement, document: Document, quick: Boolean): Array<FoldingDescriptor> {
        val globalFoldings = root.children
            .filter {
                listOf(
                    LslTypes.FUNCTION,
                    LslTypes.DEFAULT_STATE_DECLARATION,
                    LslTypes.STATE_DECLARATION
                ).contains(it.elementType)
            }.map {
                FoldingDescriptor(it, it.textRange)
            }

        val statesFoldings = root.children
            .filter {
                listOf(
                    LslTypes.DEFAULT_STATE_DECLARATION,
                    LslTypes.STATE_DECLARATION
                ).contains(it.elementType)
            }
            .flatMap { it.children.toList() }
            .filterIsInstance<LslEvent>().map {
                FoldingDescriptor(it, it.textRange)
            }

        return globalFoldings.plus(statesFoldings).toTypedArray()
    }

    override fun getPlaceholderText(node: ASTNode): String? {
        val presentableText = (node.psi as? ItemPresentation)?.presentableText ?: return null

        return "$presentableText { ... }"
    }

    override fun isCollapsedByDefault(node: ASTNode): Boolean = false
}