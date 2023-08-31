package io.github.riej.lsl.completion

import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.codeInsight.lookup.LookupElementPresentation
import com.intellij.codeInsight.lookup.LookupElementRenderer
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.util.ProcessingContext
import io.github.riej.lsl.KwdbData
import io.github.riej.lsl.psi.LslVariable

class VariableCompletionProvider : CompletionProvider<CompletionParameters>() {
    override fun addCompletions(
        parameters: CompletionParameters,
        context: ProcessingContext,
        result: CompletionResultSet
    ) {
        result.addAllElements(
            PsiTreeUtil.collectElements(parameters.originalFile) { it is LslVariable }
                .map { it as LslVariable }
                .plus(
                    KwdbData.getInstance(parameters.originalFile.project).constants.values
                )
                .mapNotNull {
                    if (it.name != null) LookupElementBuilder.create(it.name!!).withRenderer(Renderer(it)) else null
                }
        )
    }

    class Renderer(val variable: LslVariable) : LookupElementRenderer<LookupElement>() {
        override fun renderElement(element: LookupElement, presentation: LookupElementPresentation) {
            presentation.icon = variable.getIcon(false)
            presentation.itemText = variable.name
            presentation.typeText = variable.lslType.toString()
            if (variable.expression != null) {
                presentation.setTailText(" = ${variable.expression!!.text}", true)
            }
        }
    }
}