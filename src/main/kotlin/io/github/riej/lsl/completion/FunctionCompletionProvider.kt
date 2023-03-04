package io.github.riej.lsl.completion

import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.util.ProcessingContext
import io.github.riej.lsl.psi.LslFile
import io.github.riej.lsl.psi.LslFunction

class FunctionCompletionProvider : CompletionProvider<CompletionParameters>() {
    override fun addCompletions(
        parameters: CompletionParameters,
        context: ProcessingContext,
        result: CompletionResultSet
    ) {
        result.addAllElements(
            PsiTreeUtil.collectElements(parameters.originalFile) { it is LslFunction }
                .map{it as LslFunction }
                .plus(
                    (parameters.originalFile as LslFile).kwdbData.functions.values
                )
                .mapNotNull { it.name }
                .map { LookupElementBuilder.create(it) }
        )
    }
}