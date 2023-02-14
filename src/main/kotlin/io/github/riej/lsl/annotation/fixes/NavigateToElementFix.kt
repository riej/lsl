package io.github.riej.lsl.annotation.fixes

import com.intellij.codeInsight.intention.IntentionAction
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.pom.Navigatable
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile

class NavigateToElementFix(val element: Navigatable, val message: String) : IntentionAction {
    override fun startInWriteAction(): Boolean = false

    override fun getText(): String = message

    override fun getFamilyName(): String = "navigate"

    override fun isAvailable(project: Project, editor: Editor?, file: PsiFile?): Boolean = true

    override fun invoke(project: Project, editor: Editor?, file: PsiFile?) {
        element.navigate(true)
    }
}