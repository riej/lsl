package io.github.riej.lsl.completion

import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.codeInsight.lookup.LookupElementPresentation
import com.intellij.codeInsight.lookup.LookupElementRenderer
import com.intellij.codeInsight.template.TemplateManager
import com.intellij.util.ProcessingContext
import io.github.riej.lsl.KwdbData
import io.github.riej.lsl.psi.LslEvent

class EventNameCompletionProvider : CompletionProvider<CompletionParameters>() {
    override fun addCompletions(
        parameters: CompletionParameters,
        context: ProcessingContext,
        result: CompletionResultSet
    ) {
        result.addAllElements(
            KwdbData.getInstance(parameters.originalFile.project)
                .events
                .values
                .map {
                    LookupElementBuilder
                        .create(it.name!!)
                        .withRenderer(Renderer(it))
                        .withInsertHandler { context, item ->
                            val templateManager = TemplateManager.getInstance(context.project)

                            val template = templateManager.createTemplate(it.name!!, "lsl")
                            template.addTextSegment("(")
                            it.arguments.forEachIndexed { index, lslArgument ->
                                let {
                                    if (index != 0) template.addTextSegment(", ")
                                    template.addTextSegment("${lslArgument.lslType} ${lslArgument.name}")
                                }
                            }
                            template.addTextSegment(") {\n")
                            template.addEndVariable()
                            template.addTextSegment("\n}")
                            template.isToReformat = true

                            templateManager.startTemplate(context.editor, template)
                        }
                }
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