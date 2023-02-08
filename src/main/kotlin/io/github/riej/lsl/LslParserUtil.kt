package io.github.riej.lsl

import com.intellij.lang.PsiBuilder
import com.intellij.lang.parser.GeneratedParserUtilBase
import com.intellij.openapi.util.Key
import gnu.trove.TObjectIntHashMap
import io.github.riej.lsl.psi.LslTypes


object LslParserUtil : GeneratedParserUtilBase() {
    @JvmStatic
    fun ExpressionWithRecover(b: PsiBuilder?, l: Int, g: Int): Boolean {
        if (!recursion_guard_(b, l, "ExpressionWithRecover")) return false
        val m = enter_section_(b, l, _NONE_)
        val r = LslParser.Expression(b, l + 1, g)
        exit_section_(b, l, m, r, false, LslParser::ExpressionListRecover)
        return r
    }

    @JvmStatic
    fun parseVectorOrQuaternionExpr(b: PsiBuilder, l: Int): Boolean {
        if (!recursion_guard_(b, l, "VectorOrQuaternionExpr")) return false
        if (!nextTokenIsSmart(b, LslTypes.LESS)) return false

        var r: Boolean
        var p: Boolean

        val m = enter_section_(b, l, _NONE_, LslTypes.VECTOR_OR_QUATERNION_EXPR, null)
        r = consumeTokenSmart(b, LslTypes.LESS)
        p = r // pin = 1

        // g: 2 - means skip any comparison operations, plus && and ||
        // TODO: finally make comparison operations works inside of vector/quaternion definitions.
        r = p && report_error_(b, ExpressionWithRecover(b, l + 1, 2)) && r
        r = p && report_error_(b, consumeToken(b, LslTypes.COMMA)) && r
        r = p && report_error_(b, ExpressionWithRecover(b, l + 1, 2)) && r
        r = p && report_error_(b, consumeToken(b, LslTypes.COMMA)) && r
        r = p && report_error_(b, ExpressionWithRecover(b, l + 1, 2)) && r

        if (nextTokenIsSmart(b, LslTypes.COMMA)) {
            r = p && report_error_(b, consumeToken(b, LslTypes.COMMA)) && r
            r = p && report_error_(b, ExpressionWithRecover(b, l + 1, 2)) && r
        }

        r = p && report_error_(b, consumeToken(b, LslTypes.GREATER)) && r
        exit_section_(b, l, m, r, p, null)
        return r || p
    }
}