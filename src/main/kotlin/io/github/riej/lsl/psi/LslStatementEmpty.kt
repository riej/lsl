package io.github.riej.lsl.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode

class LslStatementEmpty(node: ASTNode) : ASTWrapperPsiElement(node), LslStatement {
}