package io.github.riej.lsl.annotation.fixes

import com.intellij.codeInsight.intention.IntentionAction
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.util.descendants

class DeleteElementsFix(val elements: List<PsiElement>, val message: String) : IntentionAction {
    override fun startInWriteAction(): Boolean = true

    override fun getText(): String = message

    override fun getFamilyName(): String = "remove"

    override fun isAvailable(project: Project, editor: Editor?, file: PsiFile?): Boolean = true

    override fun invoke(project: Project, editor: Editor?, file: PsiFile?) {
        elements.forEach { it.delete() }
    }
}