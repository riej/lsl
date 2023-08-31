package io.github.riej.lsl.inspections

import com.intellij.codeInspection.*
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.util.PsiTreeUtil
import io.github.riej.lsl.LslLanguage
import io.github.riej.lsl.LslPrimitiveType
import io.github.riej.lsl.psi.LslLValue

class LslInvalidVectorOrQuaternionItemInspection : LocalInspectionTool() {
    override fun getDisplayName(): String = "Invalid vector or quaternion item"
    override fun getGroupDisplayName(): String = LslLanguage.INSTANCE.displayName
    override fun isEnabledByDefault(): Boolean = true

    override fun checkFile(file: PsiFile, manager: InspectionManager, isOnTheFly: Boolean): Array<ProblemDescriptor> {
        val problemsHolder = ProblemsHolder(manager, file, isOnTheFly)

        PsiTreeUtil.collectElementsOfType(file, LslLValue::class.java)
            .filter { !it.textRange.isEmpty }
            .filterNot { it.item.isNullOrBlank() }
            .filter {
                when (it.variable?.lslType) {
                    LslPrimitiveType.VECTOR -> !listOf("x", "y", "z").contains(it.item)
                    LslPrimitiveType.QUATERNION -> !listOf("x", "y", "z", "s").contains(it.item)
                    else -> false
                }
            }
            .forEach {
                problemsHolder.registerProblem(
                    it,
                    "Invalid item",
                    ProblemHighlightType.ERROR,
                    TextRange(it.dot?.startOffsetInParent ?: 0, it.textLength),
                    RemoveLValueItem(it),
                )
            }

        return problemsHolder.resultsArray
    }

    class RemoveLValueItem(lvalue: LslLValue) : LocalQuickFixOnPsiElement(lvalue) {
        override fun getFamilyName(): String = "Remove item"

        override fun getText(): String = familyName

        override fun invoke(project: Project, file: PsiFile, startElement: PsiElement, endElement: PsiElement) {
            startElement.deleteChildRange((startElement as LslLValue).dot, startElement.lastChild)
        }
    }
}
