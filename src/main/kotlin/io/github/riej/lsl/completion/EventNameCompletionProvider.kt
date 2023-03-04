package io.github.riej.lsl.completion

import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.codeInsight.lookup.LookupElementPresentation
import com.intellij.codeInsight.lookup.LookupElementRenderer
import com.intellij.util.ProcessingContext
import io.github.riej.lsl.psi.LslEvent
import io.github.riej.lsl.psi.LslFile

class EventNameCompletionProvider : CompletionProvider<CompletionParameters>() {
    override fun addCompletions(
        parameters: CompletionParameters,
        context: ProcessingContext,
        result: CompletionResultSet
    ) {
        result.addAllElements(
            (parameters.originalFile as LslFile)
                .kwdbData
                .events
                .values
                .map { LookupElementBuilder.create(it.name!!).withRenderer(Renderer(it)) }
        )
    }

    class Renderer(val event: LslEvent) : LookupElementRenderer<LookupElement>() {
        override fun renderElement(element: LookupElement, presentation: LookupElementPresentation) {
            presentation.icon = event.getIcon(false)
            presentation.itemText = event.name
            presentation.setTailText("(${event.arguments.joinToString { "${it.lslType} ${it.name}" }})", true)
        }
    }
}