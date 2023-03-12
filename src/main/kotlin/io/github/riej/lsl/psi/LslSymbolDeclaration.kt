package io.github.riej.lsl.psi

import com.intellij.model.Symbol
import com.intellij.model.psi.PsiSymbolDeclaration
import com.intellij.model.psi.PsiSymbolService
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner

interface LslSymbolDeclaration : PsiNameIdentifierOwner, PsiSymbolDeclaration {
    override fun getDeclaringElement(): PsiElement = this

    override fun getRangeInDeclaringElement(): TextRange = identifyingElement!!.textRangeInParent
    override fun getSymbol(): Symbol = PsiSymbolService.getInstance().asSymbol(this)

    override fun getOwnDeclarations(): Collection<PsiSymbolDeclaration> = listOf(this)
}