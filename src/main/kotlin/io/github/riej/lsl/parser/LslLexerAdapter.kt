package io.github.riej.lsl.parser

import com.intellij.lexer.LexerBase
import com.intellij.psi.tree.IElementType
import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CodePointBuffer
import org.antlr.v4.runtime.CodePointCharStream
import java.nio.IntBuffer

class LslLexerAdapter : LexerBase() {
    private lateinit var buffer: CharSequence
    private var startOffset: Int = -1
    private var endOffset: Int = -1

    private lateinit var charStream: CharStream
    private var lexer: LslLexerWrapper = LslLexerWrapper(CharStreams.fromString(""))

    override fun start(buffer: CharSequence, startOffset: Int, endOffset: Int, initialState: Int) {
        this.buffer = buffer
        this.startOffset = startOffset
        this.endOffset = endOffset

        // Offsets in this class (startOffset, endOffset, getTokenStart, getTokenEnd) are in chars, not code points.
        // That's why I wrote such complicated code here.
        this.charStream = CodePointCharStream.fromBuffer(
            CodePointBuffer.withInts(
                IntBuffer.wrap(
                    buffer.substring(0, endOffset).chars().toArray()
                )
            )
        )
        this.charStream.seek(startOffset)

        this.lexer.inputStream = this.charStream
        this.lexer.state = initialState

        this.advance()
    }

    override fun getState(): Int =
        lexer.state

    override fun getTokenType(): IElementType? =
        LslTypes.getLslTokenTypeForAntlrToken(lexer.token)

    override fun getTokenStart(): Int =
        lexer.token.startIndex

    override fun getTokenEnd(): Int =
        lexer.token.stopIndex + 1

    override fun advance() {
        lexer.nextToken()
    }

    override fun getBufferSequence(): CharSequence =
        buffer

    override fun getBufferEnd(): Int = endOffset

}