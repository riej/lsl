package io.github.riej.lsl.formatting

import com.intellij.formatting.*
import com.intellij.lang.ASTNode
import com.intellij.psi.TokenType
import com.intellij.psi.formatter.common.AbstractBlock
import io.github.riej.lsl.parser.LslTypes

class LslBlock(
    node: ASTNode, wrap: Wrap?, alignment: Alignment?,
    val spacingBuilder: SpacingBuilder
) : AbstractBlock(node, wrap, alignment) {
    override fun getSpacing(child1: Block?, child2: Block): Spacing? = spacingBuilder.getSpacing(this, child1, child2)

    override fun isLeaf(): Boolean = node.firstChildNode == null

    override fun buildChildren(): MutableList<Block> =
        node.getChildren(null).filterNot { isWhitespaceOrBlank(it) }.map { LslBlock(it, null, null, spacingBuilder) }
            .toMutableList()

    private fun isWhitespaceOrBlank(n: ASTNode) =
        n.elementType == TokenType.WHITE_SPACE || n.text.isBlank()

    override fun getIndent(): Indent? {
        val parentType = node.treeParent?.elementType
        val type = node.elementType

        return when {
            listOf(
                LslTypes.FUNCTION,
                LslTypes.EVENT,
                LslTypes.STATEMENT_BLOCK,
            ).contains(parentType) && !listOf(
                LslTypes.TYPE_NAME,
                LslTypes.IDENTIFIER,
                LslTypes.BRACE_LEFT,
                LslTypes.BRACE_RIGHT,
                LslTypes.PARENTHESES_LEFT,
                LslTypes.PARENTHESES_RIGHT,
            ).contains(type) -> Indent.getNormalIndent()

            listOf(
                LslTypes.STATE_DECLARATION,
                LslTypes.DEFAULT_STATE_DECLARATION,
            ).contains(parentType) && !listOf(
                LslTypes.DEFAULT,
                LslTypes.STATE,
                LslTypes.IDENTIFIER,
                LslTypes.BRACE_LEFT,
                LslTypes.BRACE_RIGHT,
            ).contains(type) -> Indent.getNormalIndent()

            else -> Indent.getNoneIndent()
        }
    }
}