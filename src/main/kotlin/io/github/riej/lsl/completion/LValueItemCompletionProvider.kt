package io.github.riej.lsl.completion

import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.completion.CompletionSorter
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.util.ProcessingContext

class LValueItemCompletionProvider : CompletionProvider<CompletionParameters>() {
    override fun addCompletions(
        parameters: CompletionParameters,
        context: ProcessingContext,
        result: CompletionResultSet
    ) {
        result.withRelevanceSorter(CompletionSorter.emptySorter()).addAllElements(listOf(
            LookupElementBuilder.create("x"),
            LookupElementBuilder.create("y"),
            LookupElementBuilder.create("z"),
            LookupElementBuilder.create("s"),
        ))
    }
}
