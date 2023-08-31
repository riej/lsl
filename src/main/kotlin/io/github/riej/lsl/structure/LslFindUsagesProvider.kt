package io.github.riej.lsl.structure

import com.intellij.lang.findUsages.FindUsagesProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.util.elementType
import io.github.riej.lsl.parser.LslTypes
import io.github.riej.lsl.psi.LslNamedElement
import io.github.riej.lsl.psi.LslTypedElement

class LslFindUsagesProvider : FindUsagesProvider {
    override fun canFindUsagesFor(psiElement: PsiElement): Boolean =
        listOf(
            LslTypes.GLOBAL_VARIABLE,
            LslTypes.FUNCTION,
            LslTypes.DEFAULT_STATE_DECLARATION,
            LslTypes.STATE_DECLARATION,
            LslTypes.ARGUMENT,
            LslTypes.STATEMENT_VARIABLE,
            LslTypes.STATEMENT_LABEL,
        ).contains(psiElement.elementType)

    override fun getHelpId(psiElement: PsiElement): String? = null

    override fun getType(element: PsiElement): String =
        (element as? LslTypedElement)?.lslType?.toString() ?: ""

    override fun getDescriptiveName(element: PsiElement): String =
        (element as? LslNamedElement)?.name ?: ""

    override fun getNodeText(element: PsiElement, useFullName: Boolean): String =
        element.text
}