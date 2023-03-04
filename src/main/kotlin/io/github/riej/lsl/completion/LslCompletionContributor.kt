package io.github.riej.lsl.completion

import com.intellij.codeInsight.completion.CompletionContributor
import com.intellij.codeInsight.completion.CompletionType
import com.intellij.patterns.PlatformPatterns.psiElement
import com.intellij.patterns.PsiElementPattern
import com.intellij.psi.PsiElement
import io.github.riej.lsl.parser.LslTypes
import io.github.riej.lsl.psi.*

class LslCompletionContributor : CompletionContributor() {
    init {
        extend(CompletionType.BASIC, globalScopePlace(), GlobalKeywordCompletionProvider())

        extend(CompletionType.BASIC, globalScopePlace(), TypenameCompletionProvider())
        extend(CompletionType.BASIC, argumentScopePlace(), TypenameCompletionProvider())
        extend(CompletionType.BASIC, statementScopePlace(), TypenameCompletionProvider())

        extend(CompletionType.BASIC, lValueIdentifierScopePlace(), VariableCompletionProvider())

        // at new line there's no function call yet, it will be detected as lvalue
        extend(CompletionType.BASIC, lValueIdentifierScopePlace(), FunctionCompletionProvider())
        extend(CompletionType.BASIC, functionCallScopePlace(), FunctionCompletionProvider())

        extend(CompletionType.BASIC, eventIdentifierScopePlace(), EventNameCompletionProvider())
    }

    fun globalScopePlace(): PsiElementPattern.Capture<PsiElement> =
        psiElement().withParent(LslFile::class.java)

    fun argumentScopePlace(): PsiElementPattern.Capture<PsiElement> =
        psiElement(LslTypes.ARGUMENT)

    fun statementScopePlace(): PsiElementPattern.Capture<PsiElement> =
        psiElement().atStartOf(psiElement(LslStatement::class.java))

    fun functionCallScopePlace(): PsiElementPattern.Capture<PsiElement> =
        psiElement(LslTypes.IDENTIFIER).inside(LslExpressionFunctionCall::class.java)

    fun lValueIdentifierScopePlace(): PsiElementPattern.Capture<PsiElement> =
        psiElement(LslTypes.IDENTIFIER).atStartOf(psiElement(LslLValue::class.java))

    fun eventIdentifierScopePlace(): PsiElementPattern.Capture<PsiElement> =
        psiElement(LslTypes.IDENTIFIER).atStartOf(psiElement(LslEvent::class.java))
}