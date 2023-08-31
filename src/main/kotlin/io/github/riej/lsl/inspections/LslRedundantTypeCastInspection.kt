package io.github.riej.lsl.inspections

import com.intellij.codeInspection.*
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.util.PsiTreeUtil
import io.github.riej.lsl.LslLanguage
import io.github.riej.lsl.LslPrimitiveType
import io.github.riej.lsl.psi.LslExpressionTypeCast

class LslRedundantTypeCastInspection : LocalInspectionTool() {
    override fun getDisplayName(): String = "Redundant type cast"
    override fun getGroupDisplayName(): String = LslLanguage.INSTANCE.displayName
    override fun isEnabledByDefault(): Boolean = true

    override fun checkFile(file: PsiFile, manager: InspectionManager, isOnTheFly: Boolean): Array<ProblemDescriptor> {
        val problemsHolder = ProblemsHolder(manager, file, isOnTheFly)

        PsiTreeUtil.collectElementsOfType(file, LslExpressionTypeCast::class.java)
            .filter { !it.textRange.isEmpty }
            .filter { it.lslType != LslPrimitiveType.INVALID }
            .filter { (it.expression?.lslType ?: LslPrimitiveType.INVALID) == it.lslType }
            .forEach {
                problemsHolder.registerProblem(
                    it,
                    "Redundant type cast",
                    ProblemHighlightType.WEAK_WARNING,
                    TextRange(0,
                        it.parenthesesRightEl?.textRangeInParent?.endOffset
                            ?: it.expression?.textRangeInParent?.startOffset ?: it.textLength
                    ),
                    RemoveRedundantTypeCastFix(it),
                )
            }

        return problemsHolder.resultsArray
    }

    class RemoveRedundantTypeCastFix(typeCast: LslExpressionTypeCast) : LocalQuickFixOnPsiElement(typeCast) {
        override fun getFamilyName(): String = "Remove redundant type cast"

        override fun getText(): String = this.familyName

        override fun invoke(project: Project, file: PsiFile, startElement: PsiElement, endElement: PsiElement) {
            val typeCast = startElement as LslExpressionTypeCast
            val expression = typeCast.expression
            if (expression != null) {
                typeCast.replace(expression)
            }
        }
    }
}