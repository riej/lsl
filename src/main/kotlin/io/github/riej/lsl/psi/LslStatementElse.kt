package io.github.riej.lsl.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode

class LslStatementElse(node: ASTNode) : ASTWrapperPsiElement(node), LslStatement {
}