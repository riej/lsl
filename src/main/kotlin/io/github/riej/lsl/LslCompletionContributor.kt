package io.github.riej.lsl

import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.patterns.PlatformPatterns.psiElement
import com.intellij.patterns.PsiElementPattern
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.util.ProcessingContext
import io.github.riej.lsl.formatting.LslBlock
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
    }

    fun globalScopePlace(): PsiElementPattern.Capture<PsiElement> =
        psiElement().inside(LslFile::class.java)

    fun argumentScopePlace(): PsiElementPattern.Capture<PsiElement> =
        psiElement(LslTypes.ARGUMENT)

    fun statementScopePlace(): PsiElementPattern.Capture<PsiElement> =
        psiElement().atStartOf(psiElement(LslStatement::class.java))

    fun functionCallScopePlace(): PsiElementPattern.Capture<PsiElement> =
        psiElement(LslTypes.IDENTIFIER).inside(LslExpressionFunctionCall::class.java)

    fun lValueIdentifierScopePlace(): PsiElementPattern.Capture<PsiElement> =
        psiElement(LslTypes.IDENTIFIER).atStartOf(psiElement(LslLValue::class.java))

    class GlobalKeywordCompletionProvider : CompletionProvider<CompletionParameters>() {
        override fun addCompletions(
            parameters: CompletionParameters,
            context: ProcessingContext,
            result: CompletionResultSet
        ) {
            result.addElement(LookupElementBuilder.create("default"))
            result.addElement(LookupElementBuilder.create("state"))
        }
    }

    class TypenameCompletionProvider : CompletionProvider<CompletionParameters>() {
        override fun addCompletions(
            parameters: CompletionParameters,
            context: ProcessingContext,
            result: CompletionResultSet
        ) {
            result.addAllElements(
                LslPrimitiveType.AVAILABLE_TYPE_NAMES.map {
                    LookupElementBuilder.create(it)
                }
            )
        }
    }

    class FunctionCompletionProvider : CompletionProvider<CompletionParameters>() {
        override fun addCompletions(
            parameters: CompletionParameters,
            context: ProcessingContext,
            result: CompletionResultSet
        ) {
            result.addAllElements(
                PsiTreeUtil.collectElements(parameters.originalFile) { it is LslFunction }
                    .map{it as LslFunction}
                    .plus(
                        (parameters.originalFile as LslFile).kwdbData.functions.values
                    )
                    .mapNotNull { it.name }
                    .map { LookupElementBuilder.create(it) }
            )
        }
    }

    class VariableCompletionProvider : CompletionProvider<CompletionParameters>() {
        override fun addCompletions(
            parameters: CompletionParameters,
            context: ProcessingContext,
            result: CompletionResultSet
        ) {
            result.addAllElements(
                PsiTreeUtil.collectElements(parameters.originalFile) { it is LslVariable }
                    .map{it as LslVariable}
                    .plus(
                        (parameters.originalFile as LslFile).kwdbData.constants.values
                    )
                    .mapNotNull { it.name }
                    .map { LookupElementBuilder.create(it) }
            )
        }
    }
}