package io.github.riej.lsl

import com.intellij.psi.tree.IElementType
import io.github.riej.lsl.parser.LslTypes

enum class LslPrimitiveType {
    VOID, // functions result type only

    INTEGER,
    FLOAT,
    STRING,
    KEY,
    VECTOR,
    QUATERNION,
    LIST,

    INVALID;

    companion object {
        fun fromString(s: String?): LslPrimitiveType = when (s) {
            null -> VOID
            "integer" -> INTEGER
            "float" -> FLOAT
            "string" -> STRING
            "key" -> KEY
            "vector" -> VECTOR
            "rotation", "quaternion" -> QUATERNION
            "list" -> LIST
            else -> INVALID
        }

        fun fromElementType(t: IElementType?) = fromString(t?.toString())

        val AVAILABLE_TYPE_NAMES = listOf(
            "integer",
            "float",
            "string",
            "key",
            "vector",
            "rotation", "quaternion",
            "list",
        )
    }

    fun operationTo(other: LslPrimitiveType?, operation: IElementType?): LslPrimitiveType {
        if (this == VOID || this == INVALID || other == VOID || other == INVALID || other == null) {
            return INVALID
        }

        if (this == VECTOR && other == VECTOR && operation == LslTypes.MULTIPLE) {
            return FLOAT
        }

        if (this == KEY) {
            return when { // key = key or key = string only
                (other == KEY || other == STRING) && operation == LslTypes.ASSIGN -> KEY
                (other == KEY || other == STRING) && listOf(LslTypes.EQUAL, LslTypes.NOT_EQUAL).contains(operation) -> INTEGER
                else -> INVALID
            }
        }

        if (this == STRING) {
            return when {
                // string = key
                other == KEY && operation == LslTypes.ASSIGN -> STRING
                // string = string
                // string += string
                // string + string
                other == STRING && listOf(LslTypes.ASSIGN, LslTypes.PLUS_ASSIGN, LslTypes.PLUS).contains(operation) -> STRING
                other == STRING && listOf(LslTypes.EQUAL, LslTypes.NOT_EQUAL).contains(operation) -> INTEGER
                else -> INVALID
            }
        }

        if (this == other) {
            return this
        }

        // int % int only
        if (operation == LslTypes.MODULUS || operation == LslTypes.MODULUS_ASSIGN) {
            return INVALID
        }

        if ((this == INTEGER || this == FLOAT) && (other == INTEGER || other == FLOAT)) {
            return FLOAT
        }

        // <1, 2, 3> * 10 == <10, 20, 30>
        // <10, 20, 30> / 10 == <1, 2, 3>
        if ((operation == LslTypes.MULTIPLE || operation == LslTypes.MULTIPLE_ASSIGN || operation == LslTypes.DIVIDE || operation == LslTypes.DIVIDE_ASSIGN) && this == VECTOR && (other == INTEGER || other == FLOAT)) {
            return VECTOR
        }

        // <1, 0, 0> * llEuler2Rot(<0, 0, 45> * DEG_TO_RAD) == <0.707107, 0.707107, 0>
        // <1, 0, 0> / llEuler2Rot(<0, 0, 45> * DEG_TO_RAD) == <0.707107, -0.707107, 0>
        if ((operation == LslTypes.MULTIPLE || operation == LslTypes.MULTIPLE_ASSIGN || operation == LslTypes.DIVIDE || operation == LslTypes.DIVIDE_ASSIGN) && this == VECTOR && other == QUATERNION) {
            return VECTOR
        }

        // 10 * <1, 2, 3> == <10, 20, 30>
        if (operation == LslTypes.MULTIPLE && (this == INTEGER || this == FLOAT) && other == VECTOR) {
            return VECTOR
        }

        // list a; a += 10;
        if (this == LIST && (operation == LslTypes.PLUS_ASSIGN || operation == LslTypes.PLUS)) {
            return LIST
        }

        return INVALID
    }

    override fun toString(): String = name.lowercase()
}