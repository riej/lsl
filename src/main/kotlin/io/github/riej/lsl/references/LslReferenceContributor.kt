package io.github.riej.lsl.references

import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.*
import com.intellij.util.ProcessingContext
import io.github.riej.lsl.parser.LslTypes
import io.github.riej.lsl.psi.LslExpressionFunctionCall
import io.github.riej.lsl.psi.LslLValue
import io.github.riej.lsl.psi.LslStatementJump
import io.github.riej.lsl.psi.LslStatementState

class LslReferenceContributor : PsiReferenceContributor() {
    override fun registerReferenceProviders(registrar: PsiReferenceRegistrar) {
        registrar.registerReferenceProvider(
            PlatformPatterns.psiElement(LslTypes.EXPRESSION_FUNCTION_CALL),
            object : PsiReferenceProvider() {
                override fun getReferencesByElement(
                    element: PsiElement,
                    context: ProcessingContext
                ): Array<PsiReference> {
                    return arrayOf(LslExpressionFunctionCallReference(element as LslExpressionFunctionCall))
                }
            })

        registrar.registerReferenceProvider(
            PlatformPatterns.psiElement(LslTypes.STATEMENT_STATE),
            object : PsiReferenceProvider() {
                override fun getReferencesByElement(
                    element: PsiElement,
                    context: ProcessingContext
                ): Array<PsiReference> {
                    return arrayOf(LslStatementStateReference(element as LslStatementState))
                }
            })

        registrar.registerReferenceProvider(
            PlatformPatterns.psiElement(LslTypes.STATEMENT_JUMP),
            object : PsiReferenceProvider() {
                override fun getReferencesByElement(
                    element: PsiElement,
                    context: ProcessingContext
                ): Array<PsiReference> {
                    return arrayOf(LslStatementJumpReference(element as LslStatementJump))
                }
            })

        registrar.registerReferenceProvider(
            PlatformPatterns.psiElement(LslTypes.L_VALUE),
            object : PsiReferenceProvider() {
                override fun getReferencesByElement(
                    element: PsiElement,
                    context: ProcessingContext
                ): Array<PsiReference> {
                    return arrayOf(LslLValueReference(element as LslLValue))
                }
            })
    }
}