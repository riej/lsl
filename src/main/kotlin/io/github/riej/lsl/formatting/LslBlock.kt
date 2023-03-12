package io.github.riej.lsl.formatting

import com.intellij.formatting.*
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiComment
import com.intellij.psi.TokenType
import com.intellij.psi.formatter.common.AbstractBlock
import com.intellij.psi.impl.source.tree.LeafPsiElement
import io.github.riej.lsl.parser.LslTypes

// TODO: rewrite it in some better way
class LslBlock(
    val parent: LslBlock?,
    node: ASTNode, wrap: Wrap?, alignment: Alignment?, val indent_: Indent?,
    val spacingBuilder: SpacingBuilder
) : AbstractBlock(node, wrap, alignment) {
    override fun getSpacing(child1: Block?, child2: Block): Spacing? = spacingBuilder.getSpacing(this, child1, child2)

    override fun isLeaf(): Boolean = node.firstChildNode == null

    override fun buildChildren(): List<Block> {
        return node.getChildren(null)
            .filterNot { isWhitespaceOrBlank(it) }
            .map { makeChildBlock(it) }
            .toList()
    }

    private fun isWhitespaceOrBlank(n: ASTNode) =
        n.elementType == TokenType.WHITE_SPACE || n.text.isBlank()

    private fun makeChildBlock(child: ASTNode): LslBlock {
        val indent = when {
            child.psi is LeafPsiElement && child.psi !is PsiComment -> Indent.getNoneIndent()

            listOf(
                LslTypes.DEFAULT_STATE_DECLARATION,
                LslTypes.STATE_DECLARATION
            ).contains(node.elementType) -> Indent.getNormalIndent()

            !listOf(
                LslTypes.FUNCTION,
                LslTypes.EVENT,

                LslTypes.STATEMENT_BLOCK,
            ).contains(node.elementType) && child.elementType == LslTypes.STATEMENT_BLOCK -> Indent.getNoneIndent()

            node.elementType == LslTypes.STATEMENT_EXPRESSION -> Indent.getNoneIndent()

            child.elementType == LslTypes.STATEMENT_ELSE -> Indent.getNoneIndent()

            LslTypes.STATEMENTS.contains(node.elementType) -> Indent.getNormalIndent()
            node.elementType == LslTypes.STATEMENT_ELSE -> Indent.getNormalIndent()

            node.elementType == LslTypes.ARGUMENTS -> Indent.getNormalIndent()

            LslTypes.EXPRESSIONS.contains(node.elementType) && child != node.firstChildNode -> Indent.getNormalIndent()

            else -> Indent.getNoneIndent()
        }

        return LslBlock(this, child, null, null, indent, spacingBuilder)
    }

    override fun getIndent(): Indent? =
        indent_

    override fun getChildIndent(): Indent? =
        if (listOf(
                LslTypes.STATE_DECLARATION,
                LslTypes.DEFAULT_STATE_DECLARATION,

                LslTypes.FUNCTION,
                LslTypes.EVENT,

                LslTypes.ARGUMENTS,
                LslTypes.STATEMENT_BLOCK,
            ).contains(node.elementType)
        ) {
            Indent.getNormalIndent()
        } else {
            Indent.getNoneIndent()
        }
}