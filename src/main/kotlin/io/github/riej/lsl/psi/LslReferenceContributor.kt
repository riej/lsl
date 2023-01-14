package io.github.riej.lsl.psi

import com.intellij.patterns.PlatformPatterns.psiElement
import com.intellij.psi.*
import com.intellij.util.ProcessingContext

class LslReferenceContributor : PsiReferenceContributor() {
    override fun registerReferenceProviders(registrar: PsiReferenceRegistrar) {
        registrar.registerReferenceProvider(psiElement(LslLValue::class.java), LslReferenceProvider())
        registrar.registerReferenceProvider(psiElement(LslCallExpr::class.java), LslReferenceProvider())
        registrar.registerReferenceProvider(psiElement(LslStateStatement::class.java), LslReferenceProvider())
        registrar.registerReferenceProvider(psiElement(LslJumpStatement::class.java), LslReferenceProvider())
    }

    class LslReferenceProvider : PsiReferenceProvider() {
        override fun getReferencesByElement(element: PsiElement, context: ProcessingContext): Array<PsiReference> =
            when (element) {
                is LslLValue -> arrayOf(LslReference(element))
                is LslCallExpr -> arrayOf(LslReference(element))
                is LslFunctionDeclaration -> arrayOf(LslReference(element))
                is LslLocalVariableDeclaration -> arrayOf(LslReference(element))
                is LslGlobalVariableDeclaration -> arrayOf(LslReference(element))
                is LslArgument -> arrayOf(LslReference(element))
                is LslDefaultStateDeclaration -> arrayOf(LslReference(element))
                is LslStateDeclaration -> arrayOf(LslReference(element))
                is LslStateStatement -> arrayOf(LslReference(element))
                is LslLabelStatement -> arrayOf(LslReference(element))
                is LslJumpStatement -> arrayOf(LslReference(element))
                else -> emptyArray()
            }
    }
}