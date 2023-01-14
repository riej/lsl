package io.github.riej.lsl.structure

import com.intellij.ide.projectView.PresentationData
import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.util.treeView.smartTree.SortableTreeElement
import com.intellij.ide.util.treeView.smartTree.TreeElement
import com.intellij.navigation.ItemPresentation
import com.intellij.psi.NavigatablePsiElement
import io.github.riej.lsl.psi.*

class LslStructureViewElement(val element: NavigatablePsiElement) : StructureViewTreeElement, SortableTreeElement {
    override fun getPresentation(): ItemPresentation = element.presentation ?: PresentationData()

    override fun getChildren(): Array<TreeElement> {
        if (element is LslFile || element is LslDefaultStateDeclaration || element is LslStateDeclaration) {
            return element.children.mapNotNull {
                when (it) {
                    is LslGlobalVariableDeclaration -> LslStructureViewElement(it)
                    is LslFunctionDeclaration -> LslStructureViewElement(it)
                    is LslDefaultStateDeclaration -> LslStructureViewElement(it)
                    is LslStateDeclaration -> LslStructureViewElement(it)
                    is LslStateEvent -> LslStructureViewElement(it)
                    else -> null
                }
            }.toTypedArray()
        }

        return emptyArray()
    }

    override fun navigate(requestFocus: Boolean) = element.navigate(requestFocus)

    override fun canNavigate(): Boolean = element.canNavigate()

    override fun canNavigateToSource(): Boolean = element.canNavigateToSource()

    override fun getValue(): Any = element

    override fun getAlphaSortKey(): String = element.name ?: ""
}