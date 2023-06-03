package io.github.riej.lsl.scope

import com.intellij.psi.PsiElement
import com.intellij.psi.util.CachedValue
import com.intellij.psi.util.CachedValueProvider
import com.intellij.psi.util.CachedValuesManager
import io.github.riej.lsl.psi.LslNamedElement
import java.util.function.Supplier

/**
 * A utility for efficiently accessing the elements in a given scope. The childGetter function should return the
 * highest-priority elements first and then the lower-priority ones. When names coincide, the higher-priority ones
 * will be taken instead of the lower-priority ones.
 */
class ChildCache<T: LslNamedElement>(private val element: PsiElement, private val childGetter: Supplier<Iterable<T>>) {
    private var elementByNameCache: CachedValue<Map<String, T>>? = null

    companion object {
        inline fun <reified T : LslNamedElement> ofChildType(element: PsiElement) =
            ChildCache(element) { element.children.filterIsInstance<T>() }
    }

    private fun getCache(): CachedValue<Map<String, T>> {
        val definedCache = elementByNameCache ?: CachedValuesManager.getManager(element.project).createCachedValue {
            CachedValueProvider.Result.create(
                childGetter.get().filter { it.name != null }.reversed().associateBy { it.name.orEmpty() },
                element
            )
        }
        elementByNameCache = definedCache
        return definedCache
    }

    operator fun get(name: String?): T? = if (name == null) null else getCache().value[name]
}
