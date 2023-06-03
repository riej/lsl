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

    @Throws(TypeMismatch::class)
    fun operationTo(other: LslPrimitiveType, operation: IElementType?): LslPrimitiveType {
        if (this == VOID || this == INVALID || other == VOID || other == INVALID) {
            return INVALID
        }

        if (this == VECTOR && other == VECTOR && operation == LslTypes.MULTIPLE) {
            return FLOAT
        }

        if (this == other) {
            return this
        }

        // int % int only
        if (operation == LslTypes.MODULUS || operation == LslTypes.MODULUS_ASSIGN) {
            return INVALID
        }

        if ((this == STRING || this == KEY) && (other == STRING || other == KEY)) {
            return STRING
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

        throw TypeMismatch(other, this)
    }

    override fun toString(): String = name.lowercase()

    class TypeMismatch(expected: LslPrimitiveType, got: LslPrimitiveType) : Exception(
        "Type mismatch (expected %s, got %s).".format(expected.toString(), got.toString())
    )
}