package io.github.riej.lsl.completion

import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.codeInsight.lookup.LookupElementPresentation
import com.intellij.codeInsight.lookup.LookupElementRenderer
import com.intellij.codeInsight.template.TemplateManager
import com.intellij.codeInsight.template.impl.ConstantNode
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.util.ProcessingContext
import io.github.riej.lsl.KwdbData
import io.github.riej.lsl.psi.LslFunction

class FunctionCompletionProvider(val addSemicolon: Boolean) : CompletionProvider<CompletionParameters>() {
    override fun addCompletions(
        parameters: CompletionParameters,
        context: ProcessingContext,
        result: CompletionResultSet
    ) {
        result.addAllElements(
            PsiTreeUtil.collectElements(parameters.originalFile) { it is LslFunction }
                .map { it as LslFunction }
                .plus(
                    KwdbData.getInstance(parameters.originalFile.project).functions.values
                )
                .mapNotNull {
                    if (it.name != null)
                        LookupElementBuilder.create(it.name!!)
                            .withRenderer(Renderer(it))
                            .withInsertHandler { context, item ->
                                val templateManager = TemplateManager.getInstance(context.project)

                                val template = templateManager.createTemplate(it.name!!, "lsl")
                                template.addTextSegment("(")
                                it.arguments.forEachIndexed { index, lslArgument ->
                                    let {
                                        if (index != 0) template.addTextSegment(", ")
                                        template.addVariable(lslArgument.name!!, ConstantNode(lslArgument.name!!), true)
                                    }
                                }
                                template.addEndVariable()
                                if (addSemicolon) {
                                    template.addTextSegment(");")
                                } else {
                                    template.addTextSegment(")")
                                }

                                templateManager.startTemplate(context.editor, template)
                            }
                    else null
                }
        )
    }

    class Renderer(val function: LslFunction) : LookupElementRenderer<LookupElement>() {
        override fun renderElement(element: LookupElement, presentation: LookupElementPresentation) {
            presentation.icon = function.getIcon(false)
            presentation.itemText = function.name
            presentation.isItemTextBold = true
            presentation.typeText = function.lslType.toString()
            presentation.setTailText("(${function.arguments.joinToString { "${it.lslType} ${it.name}" }})", true)
        }
    }
}