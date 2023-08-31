package io.github.riej.lsl.inspections

import com.intellij.codeInspection.*
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.refactoring.suggested.endOffset
import com.intellij.refactoring.suggested.startOffset
import io.github.riej.lsl.KwdbData
import io.github.riej.lsl.LslLanguage
import io.github.riej.lsl.LslPrimitiveType
import io.github.riej.lsl.parser.LslTypes
import io.github.riej.lsl.psi.LslArgument
import io.github.riej.lsl.psi.LslElementFactory
import io.github.riej.lsl.psi.LslEvent
import kotlin.math.min

class LslInvalidEventDeclarationInspection : LocalInspectionTool() {
    override fun getDisplayName(): String = "Invalid event declaration"
    override fun getGroupDisplayName(): String = LslLanguage.INSTANCE.displayName
    override fun isEnabledByDefault(): Boolean = true

    override fun checkFile(file: PsiFile, manager: InspectionManager, isOnTheFly: Boolean): Array<ProblemDescriptor> {
        val problemsHolder = ProblemsHolder(manager, file, isOnTheFly)
        val kwdbData = KwdbData.getInstance(file.project)

        PsiTreeUtil.collectElementsOfType(file, LslEvent::class.java)
            .filter { !it.textRange.isEmpty }
            .forEach {
                val definition = kwdbData.events[it.name]

                if (definition == null) {
                    problemsHolder.registerProblem(
                        it,
                        "Unknown event",
                        ProblemHighlightType.ERROR,
                        TextRange(0, it.textLength),
                        RemoveEventFix(it),
                    )
                    return@forEach
                }

                val arguments = it.arguments

                if (arguments.isNotEmpty()) {
                    (0 until min(arguments.size, definition.arguments.size)).forEach { i ->
                        val definitionType = definition.arguments[i].lslType
                        val argumentType = arguments[i].lslType

                        if (definitionType.operationTo(argumentType, LslTypes.ASSIGN) == LslPrimitiveType.INVALID) {
                            problemsHolder.registerProblem(
                                it,
                                "Type mismatch (expected %s, got %s)".format(definitionType, argumentType),
                                ProblemHighlightType.GENERIC_ERROR,
                                arguments[i].textRangeInParent,
                                ChangeTypeFix(arguments[i], definitionType),
                            )
                        }
                    }
                }

                if (arguments.size < definition.arguments.size) {
                    problemsHolder.registerProblem(
                        it,
                        "Wrong arguments count (expected ${definition.arguments.size}, got ${arguments.size})",
                        ProblemHighlightType.GENERIC_ERROR,
                        it.parenthesesRightEl?.textRangeInParent ?: it.lastChild.textRangeInParent,
                        AddMissingArgumentsFix(
                            it,
                            definition.arguments.subList(arguments.size, definition.arguments.size)
                        ),
                    )
                } else if (arguments.size > definition.arguments.size) {
                    val firstExtraArgument = if (definition.arguments.isNotEmpty())
                        arguments[definition.arguments.size]
                    else
                        arguments.first()

                    val firstExtraArgumentComma = it.argumentsEl?.node?.getChildren(null)
                        ?.filter { it.elementType == LslTypes.COMMA }
                        ?.lastOrNull { it.psi.endOffset < firstExtraArgument.startOffset }
                        ?.psi

                    val lastExtraArgument = arguments.last()

                    problemsHolder.registerProblem(
                        it,
                        "Wrong arguments count (expected ${definition.arguments.size}, got ${arguments.size})",
                        ProblemHighlightType.GENERIC_ERROR,
                        TextRange(
                            (firstExtraArgumentComma?.startOffset ?: firstExtraArgument.startOffset) - it.startOffset,
                            lastExtraArgument.endOffset - it.startOffset,
                        ),
                        RemoveExtraArgumentsFix(
                            firstExtraArgumentComma ?: firstExtraArgument, lastExtraArgument
                        ),
                    )
                }
            }

        return problemsHolder.resultsArray
    }

    class RemoveEventFix(event: LslEvent) : LocalQuickFixOnPsiElement(event) {
        override fun getFamilyName(): String = "Remove event"

        override fun getText(): String = familyName

        override fun invoke(project: Project, file: PsiFile, startElement: PsiElement, endElement: PsiElement) {
            startElement.delete()
        }
    }

    class ChangeTypeFix(argument: LslArgument, val type: LslPrimitiveType) : LocalQuickFixOnPsiElement(argument) {
        override fun getFamilyName(): String = "Change type to $type"

        override fun getText(): String = familyName

        override fun invoke(project: Project, file: PsiFile, startElement: PsiElement, endElement: PsiElement) {
            val argument = startElement as LslArgument
            argument.typeNameEl?.replace(LslElementFactory.createTypeName(project, type))
        }
    }

    class RemoveExtraArgumentsFix(startElement: PsiElement, endElement: PsiElement) :
        LocalQuickFixOnPsiElement(startElement, endElement) {
        override fun getFamilyName(): String = "Remove extra arguments"

        override fun getText(): String = this.familyName

        override fun invoke(project: Project, file: PsiFile, startElement: PsiElement, endElement: PsiElement) {
            startElement.parent.deleteChildRange(startElement, endElement)
        }
    }

    class AddMissingArgumentsFix(event: LslEvent, val missingArguments: List<LslArgument>) :
        LocalQuickFixOnPsiElement(event) {
        override fun getFamilyName(): String = "Add missing arguments"

        override fun getText(): String = this.familyName

        override fun invoke(project: Project, file: PsiFile, startElement: PsiElement, endElement: PsiElement) {
            val event = startElement as LslEvent
            val argumentsEl = event.argumentsEl ?: return

            val firstMissingArgument = missingArguments.first()
            val lastMissingArgument = missingArguments.last()

            val firstMissingArgumentComma = firstMissingArgument.parent.node.getChildren(null)
                .filter { it.elementType == LslTypes.COMMA }
                .lastOrNull { it.psi.endOffset < firstMissingArgument.startOffset }

            firstMissingArgument
                .parent
                .node
                .getChildren(null)
                .dropWhile { it != (firstMissingArgumentComma ?: firstMissingArgument.node) }
                .takeWhile { it != lastMissingArgument.node }
                .plus(lastMissingArgument.node)
                .forEach { argumentsEl.node.addChild(it.copyElement()) }
        }
    }
}
