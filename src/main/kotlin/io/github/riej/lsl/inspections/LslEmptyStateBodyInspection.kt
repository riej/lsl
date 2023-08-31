package io.github.riej.lsl.inspections

import com.intellij.codeInspection.*
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiFile
import io.github.riej.lsl.LslLanguage
import io.github.riej.lsl.psi.LslState

class LslEmptyStateBodyInspection : LocalInspectionTool() {
    override fun getDisplayName(): String = "Empty state"
    override fun getGroupDisplayName(): String = LslLanguage.INSTANCE.displayName
    override fun isEnabledByDefault(): Boolean = true

    override fun checkFile(file: PsiFile, manager: InspectionManager, isOnTheFly: Boolean): Array<ProblemDescriptor> {
        val problemsHolder = ProblemsHolder(manager, file, isOnTheFly)

        file.children
            .filterIsInstance<LslState>()
            .filter { !it.textRange.isEmpty }
            .filter { it.events.isEmpty() }
            .forEach {
                problemsHolder.registerProblem(
                    it,
                    "State has no events",
                    ProblemHighlightType.ERROR,
                    TextRange(it.braceLeftEl?.startOffsetInParent ?: 0, it.textLength),
                )
            }

        return problemsHolder.resultsArray
    }


}
