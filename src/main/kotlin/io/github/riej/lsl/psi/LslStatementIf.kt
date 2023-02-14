package io.github.riej.lsl.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.*

class LslStatementIf(node: ASTNode) : ASTWrapperPsiElement(node), LslStatement {

}