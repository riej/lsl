package io.github.riej.lsl.inspections

import com.intellij.codeInspection.*
import com.intellij.psi.PsiFile
import com.intellij.psi.util.PsiTreeUtil
import io.github.riej.lsl.KwdbData
import io.github.riej.lsl.LslLanguage
import io.github.riej.lsl.psi.LslEvent
import io.github.riej.lsl.psi.LslNamedElement

class LslReservedIdentifierInspection : LocalInspectionTool() {
    override fun getDisplayName(): String = "Reserved identifier"
    override fun getGroupDisplayName(): String = LslLanguage.INSTANCE.displayName
    override fun isEnabledByDefault(): Boolean = true

    override fun checkFile(file: PsiFile, manager: InspectionManager, isOnTheFly: Boolean): Array<ProblemDescriptor> {
        val problemsHolder = ProblemsHolder(manager, file, isOnTheFly)

        val kwdbData = KwdbData.getInstance(file.project)
        val kwdbNames = kwdbData.constants.keys + kwdbData.functions.keys + kwdbData.events.keys

        PsiTreeUtil.collectElementsOfType(file, LslNamedElement::class.java)
            .filter { !it.textRange.isEmpty }
            .filter { it !is LslEvent }
            .filter { kwdbNames.contains(it.name) }
            .forEach {
                problemsHolder.registerProblem(
                    it,
                    "Reserved identifier",
                    ProblemHighlightType.GENERIC_ERROR,
                    it.identifyingElement?.textRangeInParent,
                )
            }

        return problemsHolder.resultsArray
    }
}
