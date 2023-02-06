package io.github.riej.lsl.psi.impl

import com.intellij.navigation.ItemPresentation
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.psi.impl.source.resolve.reference.ReferenceProvidersRegistry
import com.intellij.psi.impl.source.tree.LeafPsiElement
import com.intellij.psi.tree.IElementType
import io.github.riej.lsl.LslPrimitiveType
import io.github.riej.lsl.psi.*

object LslPsiImplUtil {
    @JvmStatic
    fun getPresentation(element: LslDefaultStateDeclaration): ItemPresentation = LslItemPresentation(element)

    @JvmStatic
    fun isVector(v: LslVectorOrQuaternionExpr): Boolean = v.expressionList.size == 3

    @JvmStatic
    fun isQuaternion(v: LslVectorOrQuaternionExpr): Boolean = v.expressionList.size == 4

    @JvmStatic
    fun getPrimitiveType(v: LslGlobalVariableDeclaration): LslPrimitiveType =
        LslPrimitiveType.fromString(v.typeName.text)

    @JvmStatic
    fun getPrimitiveType(v: LslLocalVariableDeclaration): LslPrimitiveType =
        LslPrimitiveType.fromString(v.typeName.text)

    @JvmStatic
    fun getPrimitiveType(v: LslFunctionDeclaration): LslPrimitiveType = LslPrimitiveType.fromString(v.typeName?.text)

    @JvmStatic
    fun getPrimitiveType(v: LslArgument): LslPrimitiveType = LslPrimitiveType.fromString(v.typeName.text)

    @JvmStatic
    fun getPrimitiveType(v: LslVectorOrQuaternionExpr): LslPrimitiveType = when {
        v.isVector -> LslPrimitiveType.VECTOR
        v.isQuaternion -> LslPrimitiveType.QUATERNION
        else -> LslPrimitiveType.INVALID
    }

    @JvmStatic
    fun getPrimitiveType(c: LslConstantValue): LslPrimitiveType = when {
        c.integerValue != null -> LslPrimitiveType.INTEGER
        c.hexIntegerValue != null -> LslPrimitiveType.INTEGER
        c.floatValue != null -> LslPrimitiveType.FLOAT
        c.stringValue != null -> LslPrimitiveType.STRING
        else -> LslPrimitiveType.INVALID
    }

    @JvmStatic
    fun getPrimitiveType(c: LslListExpr): LslPrimitiveType = LslPrimitiveType.LIST

    @JvmStatic
    fun getPrimitiveType(c: LslCallExpr): LslPrimitiveType {
        val file = c.containingFile as LslFile
        val function = file.getFunction(c.identifier.text)
            ?: file.kwdbData.generated.getFunction(c.identifier.text)
        return function?.getPrimitiveType() ?: LslPrimitiveType.INVALID
    }

    @JvmStatic
    fun getPrimitiveType(l: LslLValue): LslPrimitiveType {
        val variable: LslTypedElement? = findVariableInScope(l, l.identifierList.first().text)
            ?: (l.containingFile as LslFile).kwdbData.generated.getGlobalVariable(l.identifierList.first().text)

        val primitiveType = variable?.getPrimitiveType() ?: LslPrimitiveType.INVALID

        // vector and quaternion items
        if (l.identifierList.size > 1) {
            if (primitiveType != LslPrimitiveType.VECTOR && primitiveType != LslPrimitiveType.QUATERNION) {
                return LslPrimitiveType.INVALID
            }

            return when (l.identifierList.last().text) {
                "x", "y", "z" -> LslPrimitiveType.FLOAT
                "s" -> if (primitiveType == LslPrimitiveType.QUATERNION) LslPrimitiveType.FLOAT else LslPrimitiveType.INVALID
                else -> LslPrimitiveType.INVALID
            }
        }

        return primitiveType
    }

    @JvmStatic
    fun getPrimitiveType(b: LslBinaryExpr): LslPrimitiveType {
        val t1 = b.expressionList.first() as LslExpression
        val t2 = b.expressionList.last() as LslExpression

        return t1.getPrimitiveType().operationTo(t2.getPrimitiveType(), b.node.getChildren(null).firstOrNull { it is LeafPsiElement && it.text.isNotBlank() }?.elementType)
    }

    @JvmStatic
    fun getPrimitiveType(a: LslAssignExpr): LslPrimitiveType {
        return try {
            a.lValue.primitiveType.operationTo(a.expression!!.getPrimitiveType(), a.node.getChildren(null).firstOrNull { it is LeafPsiElement && it.text.isNotBlank() }?.elementType)
        } catch (e: LslPrimitiveType.TypeMismatch) {
            LslPrimitiveType.INVALID
        }
    }

    @JvmStatic
    fun getPrimitiveType(c: LslConversionExpr): LslPrimitiveType = LslPrimitiveType.fromString(c.typeName.text)

    @JvmStatic
    fun getPrimitiveType(l: LslLiteral): LslPrimitiveType
        = l.constantValue?.getPrimitiveType() ?: l.lValue?.primitiveType ?: LslPrimitiveType.INVALID

    @JvmStatic
    fun getPrimitiveType(p: LslParenthesesExpr): LslPrimitiveType =
        p.expression?.getPrimitiveType() ?: LslPrimitiveType.INVALID

    @JvmStatic
    fun getPrimitiveType(p: LslPrintExpr): LslPrimitiveType = LslPrimitiveType.VOID

    @JvmStatic
    fun getPrimitiveType(c: LslConditionalExpr): LslPrimitiveType = LslPrimitiveType.INTEGER

    @JvmStatic
    fun getPrimitiveType(u: LslUnaryExprImpl): LslPrimitiveType = LslPrimitiveType.INTEGER

    @JvmStatic
    fun getPrimitiveType(u: LslUnaryPostfixExprImpl): LslPrimitiveType = LslPrimitiveType.INTEGER

    @JvmStatic
    fun getNameIdentifier(s: LslDefaultStateDeclaration): PsiElement = s.default

    @JvmStatic
    fun getIdentifier(s: LslDefaultStateDeclaration): PsiElement = s.default

    @JvmStatic
    fun setName(s: LslDefaultStateDeclaration, name: String): PsiElement = s

    @JvmStatic
    fun getReferences(element: PsiElement): Array<PsiReference> =
        ReferenceProvidersRegistry.getReferencesFromProviders(element)

    @JvmStatic
    fun findVariableInScope(element: PsiElement, name: String): LslNamedTypedElement? {
        var container: PsiElement? = element.parent
        while (container != null) {
            when (container) {
                is LslBlock -> {
                    val variable = container.children.firstOrNull {
                        (it is LslStatement)
                                && (it.localVariableDeclaration != null)
                                && (it.localVariableDeclaration!!.getIdentifier()?.text == name)
                                && (it.textRange.endOffset < element.textRange.startOffset)
                    }
                    if (variable != null) {
                        return (variable as LslStatement).localVariableDeclaration
                    }
                }

                is LslFile -> {
                    val variable = container.getGlobalVariable(name)
                    if (variable != null) {
                        return variable
                    }
                }

                is LslFunctionDeclaration -> {
                    val argument = container.argumentList.firstOrNull { it.getIdentifier()!!.text == name }
                    if (argument != null) {
                        return argument
                    }
                }

                is LslStateEvent -> {
                    val argument = container.argumentList.firstOrNull { it.getIdentifier()!!.text == name }
                    if (argument != null) {
                        return argument
                    }
                }
            }
            container = container.parent
        }
        return null
    }

    @JvmStatic
    fun getTypeToken(t: LslTypeName): IElementType? {
        if (t.integer != null) {
            return LslTypes.INTEGER
        }
        if (t.float != null) {
            return LslTypes.FLOAT
        }
        if (t.string != null) {
            return LslTypes.STRING
        }
        if (t.key != null) {
            return LslTypes.KEY
        }
        if (t.vector != null) {
            return LslTypes.VECTOR
        }
        if (t.rotation != null || t.quaternion != null) {
            return LslTypes.QUATERNION
        }
        if (t.list != null) {
            return LslTypes.LIST
        }
        return null
    }

    /**
     * Is t1 compatible to t2?
     */
    @JvmStatic
    fun isCompatibleType(t1: LslTypeName, t2: LslTypeName): Boolean {
        val t1Token = t1.typeToken
        val t2Token = t2.typeToken

        if (t1Token == t2Token) {
            return true
        }

        // string and key are totally compatible
        if ((t1Token == LslTypes.STRING || t1Token == LslTypes.KEY) && (t2Token == LslTypes.STRING || t2Token == LslTypes.KEY)) {
            return true
        }

        // integer is compatible to float, but not otherwise
        if (t1Token == LslTypes.INTEGER && t2Token == LslTypes.FLOAT) {
            return true
        }

        return false
    }
}