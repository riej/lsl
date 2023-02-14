package io.github.riej.lsl.structure

import com.intellij.ide.structureView.StructureViewModel
import com.intellij.ide.structureView.StructureViewModelBase
import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.util.treeView.smartTree.Sorter
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiFile
import io.github.riej.lsl.psi.*

class LslStructureViewModel(editor: Editor?, psiFile: PsiFile) :
    StructureViewModelBase(psiFile, editor, LslStructureViewElement(psiFile)), StructureViewModel.ElementInfoProvider {
    override fun getSorters(): Array<Sorter> = arrayOf(Sorter.ALPHA_SORTER)

    override fun isAlwaysShowsPlus(element: StructureViewTreeElement): Boolean =
        element.value is LslState

    override fun isAlwaysLeaf(element: StructureViewTreeElement): Boolean =
        (element.value is LslGlobalVariable) || (element.value is LslFunction) || (element.value is LslEvent)

    override fun getSuitableClasses(): Array<Class<*>> = arrayOf(
        LslGlobalVariable::class.java,
        LslFunction::class.java,
        LslState::class.java,
        LslEvent::class.java,
    )
}