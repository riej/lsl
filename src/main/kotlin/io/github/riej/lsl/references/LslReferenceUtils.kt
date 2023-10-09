package io.github.riej.lsl.references

import com.intellij.psi.PsiElement
import io.github.riej.lsl.KwdbData
import io.github.riej.lsl.psi.LslNamedElement

object LslReferenceUtils {
    fun findNamedElement(from: PsiElement, name: String): LslNamedElement? {
        // Look for existing identifier in current parent node first.
        val existingIdentifierInCurrentScope = from.parent.children
            .takeWhile { child -> child != from }
            .filterIsInstance<LslNamedElement>()
            .firstOrNull { child -> child.name == name }

        if (existingIdentifierInCurrentScope != null) {
            return existingIdentifierInCurrentScope
        }

        // Check existing identifiers in another scopes.
        var node = from.parent.parent
        while (node != null) {
            val existingIdentifier = node.children
                .filterIsInstance<LslNamedElement>()
                .firstOrNull { child -> child.name == name }

            if (existingIdentifier != null) {
                return existingIdentifier
            }

            node = node.parent
        }

        // Check in KWDB at last.
        return KwdbData.getInstance(from.project).getByName(name)
    }
}