package io.github.riej.lsl.structure

import com.intellij.ide.structureView.StructureViewBuilder
import com.intellij.ide.structureView.StructureViewModel
import com.intellij.ide.structureView.TreeBasedStructureViewBuilder
import com.intellij.lang.PsiStructureViewFactory
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiFile

class LslStructureViewFactory : PsiStructureViewFactory {
    override fun getStructureViewBuilder(psiFile: PsiFile): StructureViewBuilder =
        LslTreeBasedStructureViewBuilder(psiFile)

    class LslTreeBasedStructureViewBuilder(val psiFile: PsiFile) : TreeBasedStructureViewBuilder() {
        override fun createStructureViewModel(editor: Editor?): StructureViewModel =
            LslStructureViewModel(editor, psiFile)
    }
}