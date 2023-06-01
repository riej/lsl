package io.github.riej.lsl.scope

import com.intellij.psi.PsiElement
import com.intellij.psi.util.CachedValueProvider
import com.intellij.psi.util.CachedValuesManager
import com.intellij.psi.util.PsiTreeUtil
import io.github.riej.lsl.psi.LslNamedElement

interface LslPsiScope : PsiElement, LslScope {
    override val declaredElements: List<LslNamedElement>
        get() = children.mapNotNull { it as? LslNamedElement }

    override val parentScope: LslScope?
        get() = PsiTreeUtil.findFirstParent(this.parent) { it is LslScope } as? LslScope

    override val allDeclaredElements: Map<String, LslNamedElement>
        get() = CachedValuesManager.getCachedValue(this) {
            val elements: Map<String, LslNamedElement> =
                (parentScope?.allDeclaredElements ?: emptyMap()).plus(declaredElements.mapNotNull {
                    val name = it.name
                    if (name == null)
                        null
                    else
                        Pair(name, it)
                }.associate { it })

            CachedValueProvider.Result(
                elements,
                this,
            )
        }
}