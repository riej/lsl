package io.github.riej.lsl.psi.impl

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFileFactory
import io.github.riej.lsl.LslFileType
import io.github.riej.lsl.psi.LslFile
import io.github.riej.lsl.psi.LslGlobalVariableDeclaration

object LslElementFactory {
    @JvmStatic
    fun createFile(project: Project, text: String): LslFile =
        PsiFileFactory.getInstance(project).createFileFromText("dummy.lsl", LslFileType.INSTANCE, text) as LslFile

    @JvmStatic
    fun createIdentifier(project: Project, name: String): PsiElement {
        val file = createFile(project, "integer ${name}; default {}")
        return (file.firstChild as LslGlobalVariableDeclaration).getIdentifier()
    }

    @JvmStatic
    fun createGlobalVariable(
        project: Project,
        type: String,
        name: String,
        value: String?,
        comment: String?
    ): PsiElement {
        val file = createFile(project, "/* $comment */\n$type $name ${if (value != null) "= $value" else ""};")
        return file.children.first { it is LslGlobalVariableDeclaration }!!
    }
}