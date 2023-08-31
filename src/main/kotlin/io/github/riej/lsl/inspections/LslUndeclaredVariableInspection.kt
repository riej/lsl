package io.github.riej.lsl.inspections

import com.intellij.codeInspection.*
import com.intellij.psi.PsiFile
import com.intellij.psi.util.PsiTreeUtil
import io.github.riej.lsl.LslLanguage
import io.github.riej.lsl.psi.LslLValue

class LslUndeclaredVariableInspection : LocalInspectionTool() {
    override fun getDisplayName(): String = "Undeclared variable"
    override fun getGroupDisplayName(): String = LslLanguage.INSTANCE.displayName
    override fun isEnabledByDefault(): Boolean = true

    override fun checkFile(file: PsiFile, manager: InspectionManager, isOnTheFly: Boolean): Array<ProblemDescriptor> {
        val problemsHolder = ProblemsHolder(manager, file, isOnTheFly)

        PsiTreeUtil.collectElementsOfType(file, LslLValue::class.java)
            .filter { !it.textRange.isEmpty }
            .filter { it.reference?.resolve() == null }
            .forEach {
                problemsHolder.registerProblem(
                    it,
                    "Undeclared variable",
                    ProblemHighlightType.ERROR,
                    it.variableNameIdentifier?.textRangeInParent,
                    // TODO: create variable fix
                )
            }

        return problemsHolder.resultsArray
    }
}