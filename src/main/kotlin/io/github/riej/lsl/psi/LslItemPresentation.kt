package io.github.riej.lsl.psi

import com.intellij.icons.AllIcons
import com.intellij.navigation.ItemPresentation
import com.intellij.psi.PsiElement
import io.github.riej.lsl.LslIcons
import javax.swing.Icon

class LslItemPresentation(val element: PsiElement) : ItemPresentation {
    override fun getPresentableText(): String? =
            when (element) {
                is LslGlobalVariableDeclaration -> "${element.typeName.text} ${element.name}"
                is LslLocalVariableDeclaration -> "${element.typeName.text} ${element.name}"
                is LslArgument -> "${element.typeName.text} ${element.name}"
                is LslFunctionDeclaration -> "${element.typeName?.text ?: ""} ${element.name}(${
                    element.argumentList.joinToString(", ") { "${it.typeName.text} ${it.getIdentifier()?.text}".trim() }
                })".trim()

                is LslStateEvent -> "${element.name}(${
                    element.argumentList.joinToString(", ") { "${it.typeName.text} ${it.getIdentifier()?.text}".trim() }
                })".trim()

                is LslDefaultStateDeclaration -> "default"
                is LslStateDeclaration -> "state ${element.name}"
                else -> null
            }

    override fun getIcon(unused: Boolean): Icon? =
        when (element) {
            is LslFile -> LslIcons.FILE
            is LslGlobalVariableDeclaration -> AllIcons.Nodes.Gvariable
            is LslLocalVariableDeclaration -> AllIcons.Nodes.Variable
            is LslArgument -> AllIcons.Nodes.Variable
            is LslFunctionDeclaration -> AllIcons.Nodes.Function
            is LslStateEvent -> LslIcons.EVENT // SpyJSIcons.Icons.Event
            is LslDefaultStateDeclaration -> LslIcons.STATE // AllIcons.Nodes.Static
            is LslStateDeclaration -> LslIcons.STATE // AllIcons.Nodes.Static
            else -> null
        }
}