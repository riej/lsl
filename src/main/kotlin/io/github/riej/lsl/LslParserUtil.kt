package io.github.riej.lsl

import com.intellij.lang.PsiBuilder
import com.intellij.lang.parser.GeneratedParserUtilBase
import com.intellij.openapi.util.Key
import gnu.trove.TObjectIntHashMap
import io.github.riej.lsl.psi.LslTypes


object LslParserUtil : GeneratedParserUtilBase() {
    val MODES_KEY = Key.create<TObjectIntHashMap<String>>("MODES_KEY")

    private fun getParsingModes(builder: PsiBuilder): TObjectIntHashMap<String> =
        builder.getUserData(MODES_KEY) ?: let {
            val flags = TObjectIntHashMap<String>()
            builder.putUserData(MODES_KEY, flags)
            flags
        }

    @JvmStatic
    fun isModeOn(builder: PsiBuilder, level: Int, mode: String?): Boolean {
        return getParsingModes(builder)[mode] > 0
    }

    @JvmStatic
    fun withOn(builder: PsiBuilder, level: Int, mode: String, parser: Parser): Boolean {
        return withImpl(builder, level, mode, true, parser, parser)
    }

    @JvmStatic
    fun withOff(builder: PsiBuilder, level: Int, parser: Parser, vararg modes: String): Boolean {
        val map = getParsingModes(builder)
        val prev = TObjectIntHashMap<String>()
        for (mode in modes) {
            val p = map[mode]
            if (p > 0) {
                map.put(mode, 0)
                prev.put(mode, p)
            }
        }
        val result = parser.parse(builder, level)
        prev.forEachEntry { mode: String?, p: Int ->
            map.put(mode, p)
            true
        }
        return result
    }

    private fun withImpl(
        builder: PsiBuilder, level: Int, mode: String, onOff: Boolean, whenOn: Parser, whenOff: Parser
    ): Boolean {
        val map = getParsingModes(builder)
        val prev = map[mode]
        val change = (prev.and(1) == 0) == onOff
        if (change) map.put(mode, prev.shl(1).or(if (onOff) 1 else 0))
        val result = (if (change) whenOn else whenOff).parse(builder, level)
        if (change) map.put(mode, prev)
        return result
    }

    @JvmStatic
    fun isModeOff(builder: PsiBuilder, level: Int, mode: String): Boolean {
        return getParsingModes(builder)[mode] == 0
    }

    @JvmStatic
    fun enterMode(builder: PsiBuilder, level: Int, mode: String): Boolean {
        val flags = getParsingModes(builder)
        if (!flags.increment(mode)) flags.put(mode, 1)
        return true
    }

    private fun exitMode(builder: PsiBuilder, level: Int, mode: String, safe: Boolean): Boolean {
        val flags = getParsingModes(builder)
        val count = flags[mode]
        if (count == 1) {
            flags.remove(mode)
        } else if (count > 1) {
            flags.put(mode, count - 1)
        } else if (!safe) {
            builder.error("Could not exit inactive '" + mode + "' mode at offset " + builder.currentOffset)
        }
        return true
    }

    @JvmStatic
    fun exitMode(builder: PsiBuilder, level: Int, mode: String): Boolean = exitMode(builder, level, mode, false)

    @JvmStatic
    fun exitModeSafe(builder: PsiBuilder, level: Int, mode: String): Boolean = exitMode(builder, level, mode, true)

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