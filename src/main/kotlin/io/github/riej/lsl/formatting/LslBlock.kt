package io.github.riej.lsl.formatting

import com.intellij.formatting.*
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiComment
import com.intellij.psi.PsiWhiteSpace
import com.intellij.psi.TokenType
import com.intellij.psi.formatter.common.AbstractBlock
import com.intellij.psi.impl.source.tree.LeafPsiElement
import io.github.riej.lsl.parser.LslTypes

// TODO: rewrite it in some better way
class LslBlock(
    val parent: LslBlock?,
    node: ASTNode, wrap: Wrap?, alignment: Alignment?,
    val spacingBuilder: SpacingBuilder
) : AbstractBlock(node, wrap, alignment) {
    override fun getSpacing(child1: Block?, child2: Block): Spacing? = spacingBuilder.getSpacing(this, child1, child2)

    override fun isLeaf(): Boolean = node.firstChildNode == null

    val childAlignment = makeChildAlignment()
    val argumentsAlignment = Alignment.createAlignment()

    // some hack for if-else-if-else chaining
    val ifBaseAlignment: Alignment?
    val ifChildAlignment: Alignment?

    init {
        if (node.elementType == LslTypes.STATEMENT_ELSE) {
            ifBaseAlignment = parent?.ifBaseAlignment
            ifChildAlignment = parent?.ifChildAlignment
        } else if (node.elementType == LslTypes.STATEMENT_IF && parent?.node?.elementType == LslTypes.STATEMENT_ELSE) {
            ifBaseAlignment = parent.ifBaseAlignment
            ifChildAlignment = parent.ifChildAlignment
        } else {
            ifBaseAlignment = alignment
            ifChildAlignment = Alignment.createAlignment()
        }
    }

    override fun buildChildren(): List<Block> {
        return node.getChildren(null)
            .filterNot { isWhitespaceOrBlank(it) }
            .map {
                LslBlock(this, it, makeWrap(it), useChildAlignment(it, childAlignment), spacingBuilder)
            }
            .toList()
    }

    private fun isWhitespaceOrBlank(n: ASTNode) =
        n.elementType == TokenType.WHITE_SPACE || n.text.isBlank()

    private fun makeWrap(child: ASTNode): Wrap? =
        when {
            child is LeafPsiElement -> null

            LslTypes.GLOBAL_DECLARATIONS.contains(child.elementType) -> Wrap.createWrap(WrapType.NORMAL, true)
            LslTypes.STATEMENTS.contains(child.elementType) -> Wrap.createWrap(WrapType.NORMAL, true)

            else -> null
        }

    private fun useChildAlignment(child: ASTNode, childAlignment: Alignment?): Alignment? =
        when {
            child is LeafPsiElement -> null

            node.elementType == LslTypes.STATEMENT_IF && child.elementType == LslTypes.STATEMENT_ELSE -> ifBaseAlignment
            node.elementType == LslTypes.STATEMENT_ELSE && !hasNewlineBefore(child) -> ifBaseAlignment

            node.elementType == LslTypes.STATEMENT_IF -> ifChildAlignment
            node.elementType == LslTypes.STATEMENT_ELSE -> ifChildAlignment

            child.elementType == LslTypes.ARGUMENT -> argumentsAlignment

            else -> childAlignment
        }

    private fun makeChildAlignment(): Alignment? =
        when {
            LslTypes.GLOBAL_DECLARATIONS.contains(node.elementType) -> Alignment.createAlignment()
            LslTypes.STATEMENTS.contains(node.elementType) -> Alignment.createAlignment()

            node.elementType == LslTypes.EVENT -> Alignment.createAlignment()

            else -> alignment
        }

    override fun getIndent(): Indent? =
        if ((listOf(
                LslTypes.STATE_DECLARATION,
                LslTypes.DEFAULT_STATE_DECLARATION,

                LslTypes.FUNCTION,
                LslTypes.EVENT,

                LslTypes.STATEMENT_BLOCK,
            ).contains(node.treeParent?.elementType)) && (node.psi !is LeafPsiElement || node.psi is PsiComment)
        ) {
            Indent.getNormalIndent()
        } else {
            Indent.getNoneIndent()
        }

    override fun getChildIndent(): Indent? =
        if (listOf(
                LslTypes.STATE_DECLARATION,
                LslTypes.DEFAULT_STATE_DECLARATION,

                LslTypes.FUNCTION,
                LslTypes.EVENT,

                LslTypes.STATEMENT_BLOCK,
            ).contains(node.elementType)
        ) {
            Indent.getNormalIndent()
        } else {
            Indent.getNoneIndent()
        }

    private fun hasNewlineBefore(child: ASTNode): Boolean =
        if (child.treePrev.psi is PsiWhiteSpace)
            if (child.treePrev.text.contains("\n"))
                true
            else
                hasNewlineBefore(child.treePrev)
        else
            false
}