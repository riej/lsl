package io.github.riej.lsl.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.psi.impl.source.resolve.reference.ReferenceProvidersRegistry
import io.github.riej.lsl.LslPrimitiveType
import io.github.riej.lsl.parser.LslTypes

class LslLValue(node: ASTNode) : ASTWrapperPsiElement(node), LslExpression {
    val variableNameIdentifier: PsiElement?
        get() = findChildByType(LslTypes.IDENTIFIER)

    val variableName: String?
        get() = identifiers.getOrNull(0)

    val item: String?
        get() = identifiers.getOrNull(1)

    private val identifiers: List<String>
        get() = findChildrenByType<PsiElement>(LslTypes.IDENTIFIER).map { it.text }

    val dot: PsiElement?
        get() = findChildByType(LslTypes.DOT)

    val variable: LslVariable?
        get() = reference?.resolve() as? LslVariable

    override fun getReferences(): Array<PsiReference> = ReferenceProvidersRegistry.getReferencesFromProviders(this)

    override fun getReference(): PsiReference? = references.firstOrNull()

    override val lslType: LslPrimitiveType
        get() {
            val variableType = variable?.lslType ?: LslPrimitiveType.INVALID

            return when {
                item == null -> variableType
                variableType == LslPrimitiveType.VECTOR && listOf(
                    "x",
                    "y",
                    "z"
                ).contains(item) -> LslPrimitiveType.FLOAT

                variableType == LslPrimitiveType.QUATERNION && listOf(
                    "x",
                    "y",
                    "z",
                    "s"
                ).contains(item) -> LslPrimitiveType.FLOAT

                else -> LslPrimitiveType.INVALID
            }
        }
}