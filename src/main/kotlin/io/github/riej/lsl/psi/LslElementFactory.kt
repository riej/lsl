package io.github.riej.lsl.psi

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFileFactory
import io.github.riej.lsl.LslFileType

object LslElementFactory {
    @JvmStatic
    fun createFile(project: Project, text: String): io.github.riej.lsl.psi.LslFile =
        PsiFileFactory.getInstance(project).createFileFromText("dummy.lsl", LslFileType.INSTANCE, text) as io.github.riej.lsl.psi.LslFile

    @JvmStatic
    fun createIdentifier(project: Project, name: String): PsiElement {
        val file = io.github.riej.lsl.psi.LslElementFactory.createFile(project, "integer ${name}; default {}")
        return (file.firstChild as io.github.riej.lsl.psi.LslGlobalVariable).nameIdentifier!!
    }
}