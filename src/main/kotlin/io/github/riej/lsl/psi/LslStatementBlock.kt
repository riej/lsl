package io.github.riej.lsl.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.util.parentOfType
import io.github.riej.lsl.parser.LslTypes

class LslStatementBlock(node: ASTNode) : ASTWrapperPsiElement(node), LslStatement {
    val statements: List<LslStatement>
        get() = findChildrenByType(LslTypes.STATEMENTS)

    val braceLeftEl: PsiElement?
        get() = findChildByType(LslTypes.BRACE_LEFT)

    val braceRightEl: PsiElement?
        get() = findChildByType(LslTypes.BRACE_RIGHT)

    val labels: Map<String, LslStatementLabel>
        get() = (parentOfType<LslStatementBlock>()?.labels ?: emptyMap()).plus(
            statements.filterIsInstance<LslStatementLabel>().associateBy { it.name ?: "" }
                .filterKeys { it.isNotBlank() }
        )
}