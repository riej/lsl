package io.github.riej.lsl.psi

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFileFactory
import io.github.riej.lsl.LslFileType

object LslElementFactory {
    @JvmStatic
    fun createFile(project: Project, text: String): LslFile =
        PsiFileFactory.getInstance(project)
            .createFileFromText("dummy.lsl", LslFileType.INSTANCE, text, 0, false, true) as LslFile

    @JvmStatic
    fun createIdentifier(project: Project, name: String): PsiElement {
        val file = createFile(project, "integer ${name}; default {}")
        return (file.firstChild as LslGlobalVariable).nameIdentifier!!
    }
}