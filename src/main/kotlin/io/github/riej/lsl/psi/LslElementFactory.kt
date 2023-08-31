package io.github.riej.lsl.psi

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.util.PsiTreeUtil
import io.github.riej.lsl.LslFileType
import io.github.riej.lsl.LslPrimitiveType

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

    @JvmStatic
    fun createFunction(project: Project, name: String, type: LslPrimitiveType): PsiElement {
        val file =
            createFile(project, "${if (type == LslPrimitiveType.VOID) "" else type.toString()} ${name}() {\n}".trim())
        return (file.firstChild as LslFunction)
    }

    @JvmStatic
    fun createTypeName(project: Project, type: LslPrimitiveType): PsiElement {
        val file = createFile(project, "$type something; default {}")
        return (file.firstChild as LslGlobalVariable).typeNameEl!!
    }

    @JvmStatic
    fun createTypeCast(project: Project, type: LslPrimitiveType, expression: LslExpression): LslExpressionTypeCast {
        val file = createFile(project, "default { state_entry() { $type something = ($type)${expression.text}; } }")
        return PsiTreeUtil.findChildOfType(file, LslExpressionTypeCast::class.java)!!
    }

    @JvmStatic
    fun createParentheses(project: Project, expression: LslExpression): LslExpressionParentheses {
        val file = createFile(project, "default { state_entry() { integer something = (${expression.text}); } }")
        return PsiTreeUtil.findChildOfType(file, LslExpressionParentheses::class.java)!!
    }
}