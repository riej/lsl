package io.github.riej.lsl.validation.fixes

import com.intellij.codeInspection.LocalQuickFix
import com.intellij.codeInspection.ProblemDescriptor
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.util.descendants

class RemoveCodeLocalQuickFix(val message: String, val parent: PsiElement, val range: TextRange) : LocalQuickFix {
    override fun getFamilyName(): String =
        message

    override fun applyFix(project: Project, descriptor: ProblemDescriptor) {
        val children = parent.descendants().filter { range.contains(it.textRange) && it.parent == parent }
        parent.deleteChildRange(
            children.first(),
            children.last(),
        )
    }
}