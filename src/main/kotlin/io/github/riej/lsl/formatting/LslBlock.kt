package io.github.riej.lsl.formatting

import com.intellij.formatting.*
import com.intellij.lang.ASTNode
import com.intellij.openapi.util.TextRange
import com.intellij.psi.TokenType
import com.intellij.psi.codeStyle.CodeStyleSettings
import com.intellij.psi.formatter.FormatterUtil
import io.github.riej.lsl.psi.*
import io.github.riej.lsl.psi.LslBlock as PsiLslBlock

class LslBlock(
    val parent: LslBlock?,
    val _node: ASTNode,
    val codeStyleSettings: CodeStyleSettings,
    val _alignment: Alignment?,
    val _indent: Indent,
    val _wrap: Wrap?,
    val spacingBuilder: SpacingBuilder
) : ASTBlock {

    override fun getTextRange(): TextRange =
        _node.textRange

    override fun getSubBlocks(): List<Block> =
        _node.getChildren(null)
            .filterNot { isWhitespaceOrBlank(it) }
            .map {
                when {
                    it.psi is LslStateEvent -> LslBlock(
                        this,
                        it,
                        codeStyleSettings,
                        null,
                        Indent.getNormalIndent(),
                        Wrap.createWrap(WrapType.NORMAL, false),
                        spacingBuilder
                    )
                    _node.psi is PsiLslBlock && (it.elementType != LslTypes.BRACES_LEFT && it.elementType != LslTypes.BRACES_RIGHT) -> LslBlock(
                        this,
                        it,
                        codeStyleSettings,
                        null,
                        Indent.getNormalIndent(),
                        Wrap.createWrap(WrapType.NORMAL, false),
                        spacingBuilder
                    )
//                    _node.psi is LslIfStatement && it.psi is LslStatement -> LslBlock(
//                        this,
//                        it,
//                        codeStyleSettings,
//                        null,
//                        Indent.getNormalIndent(true),
//                        Wrap.createWrap(WrapType.NORMAL, false),
//                        spacingBuilder
//                    )
                    else -> LslBlock(this, it, codeStyleSettings, null, Indent.getNoneIndent(), null, spacingBuilder)
                }
            }

    private fun isWhitespaceOrBlank(n: ASTNode) =
        n.elementType == TokenType.WHITE_SPACE || n.text.isBlank()

    override fun getWrap(): Wrap? = _wrap

    override fun getIndent(): Indent = _indent

    override fun getAlignment(): Alignment? = _alignment

    override fun getSpacing(child1: Block?, child2: Block): Spacing? =
        spacingBuilder.getSpacing(this, child1, child2)

    override fun getChildAttributes(newChildIndex: Int): ChildAttributes {
        if (_node.psi is LslFile) {
            return ChildAttributes(Indent.getNoneIndent(), null)
        }

        if (_node.psi is PsiLslBlock) {
            return ChildAttributes(Indent.getNormalIndent(), null)
        }

        return ChildAttributes(null, null)
    }

    override fun isIncomplete(): Boolean =
        FormatterUtil.isIncomplete(_node)

    override fun isLeaf(): Boolean =
        _node.firstChildNode == null

    override fun getNode(): ASTNode = _node
}