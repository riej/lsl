package io.github.riej.lsl.scope

import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import io.github.riej.lsl.KwdbData
import io.github.riej.lsl.psi.*

object LslScopeUtils {
    fun getScopeFor(element: PsiElement): LslScope =
        when (element) {
            is LslFile -> LslScope(
                KwdbData.getInstance(element.project).scope,
                element.children.filterIsInstance<LslNamedElement>()
            )

            is LslArguments -> LslScope()
            is LslStatementBlock -> when (val parent = element.parent) {
                is LslFunction -> LslScope(parent.scope, parent.arguments)
                is LslEvent -> LslScope(parent.scope, parent.arguments)
                else -> getScopeForPrevStatement(element)
            }

            is LslStatementVariable -> LslScope(getScopeForPrevStatement(element), listOf(element))
            is LslStatement -> getScopeForPrevStatement(element)
            else -> getScopeFor(element.parent)
        }

    private fun getScopeForPrevStatement(element: LslStatement): LslScope =
        getScopeFor(PsiTreeUtil.getPrevSiblingOfType(element, LslStatement::class.java) ?: element.parent)
}