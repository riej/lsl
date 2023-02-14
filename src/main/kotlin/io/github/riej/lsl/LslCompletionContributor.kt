package io.github.riej.lsl

import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.patterns.PlatformPatterns.psiElement
import com.intellij.patterns.PsiElementPattern
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.util.ProcessingContext

class LslCompletionContributor : CompletionContributor() {
    init {
        extend(CompletionType.BASIC, globalScopePlace(), TypenameCompletionProvider())
        extend(CompletionType.BASIC, globalScopePlace(), GlobalKeywordCompletionProvider())

//        extend(CompletionType.BASIC, argumentsPlace(), TypenameCompletionProvider())

        extend(CompletionType.BASIC, globalScopePlace(), GlobalKeywordCompletionProvider())

//        extend(CompletionType.BASIC, identifierScopePlace(), IdentifierCompletionProvider())
//        extend(CompletionType.BASIC, identifierScopePlace(), KwdbIdentifierCompletionProvider())
    }

    fun globalScopePlace(): PsiElementPattern.Capture<PsiElement> =
        psiElement().withParent(PsiFile::class.java)
//
//    fun argumentsPlace(): PsiElementPattern.Capture<PsiElement> =
//        psiElement().andOr(
//            psiElement().inside(LslFunctionDeclaration::class.java).afterLeaf("(")
//                .andNot(psiElement().inside(LslBlock::class.java)),
//            psiElement().inside(LslFunctionDeclaration::class.java).afterLeaf(",")
//                .andNot(psiElement().inside(LslBlock::class.java)),
//
//            psiElement().inside(LslStateEvent::class.java).afterLeaf("(")
//                .andNot(psiElement().inside(LslBlock::class.java)),
//            psiElement().inside(LslStateEvent::class.java).afterLeaf(",")
//                .andNot(psiElement().inside(LslBlock::class.java))
//        )
//
//    fun identifierScopePlace(): PsiElementPattern.Capture<PsiElement> =
//        psiElement().inside(LslBlock::class.java)

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
                LslPrimitiveType.AVAILABLE_TYPES.map {
                    LookupElementBuilder.create(it.toString())
                }
            )
        }
    }
//
//    class IdentifierCompletionProvider : CompletionProvider<CompletionParameters>() {
//        override fun addCompletions(
//            parameters: CompletionParameters,
//            context: ProcessingContext,
//            result: CompletionResultSet
//        ) {
//            result.addAllElements(
//                PsiTreeUtil.collectElements(parameters.originalFile) { it is LslNamedElement }
//                    .map{it as LslNamedElement}
//                    .mapNotNull { if (it.getIdentifier() != null) LookupElementBuilder.create(it.getIdentifier()!!.text) else null }
//            )
//        }
//    }
//
//    class KwdbIdentifierCompletionProvider : CompletionProvider<CompletionParameters>() {
//        override fun addCompletions(
//            parameters: CompletionParameters,
//            context: ProcessingContext,
//            result: CompletionResultSet
//        ) {
//            result.addAllElements(
//                PsiTreeUtil.collectElements((parameters.originalFile as LslFile).kwdbData.generated) { it is LslNamedElement }
//                    .map{it as LslNamedElement}
//                    .mapNotNull { if (it.getIdentifier() != null) LookupElementBuilder.create(it.getIdentifier()!!.text) else null }
//            )
//        }
//    }
}