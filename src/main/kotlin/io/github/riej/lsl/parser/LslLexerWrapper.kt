package io.github.riej.lsl.parser

import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.IntStream
import org.antlr.v4.runtime.LexerNoViableAltException
import org.antlr.v4.runtime.Token

class LslLexerWrapper(input: CharStream) : LSLLexer(input) {
    // The same as nextToken method, but doesn't skip invalid tokens.
    override fun nextToken(): Token {
        checkNotNull(_input) { "nextToken requires a non-null input stream." }

        // Mark start location in char stream so unbuffered streams are
        // guaranteed at least have text of current token

        // Mark start location in char stream so unbuffered streams are
        // guaranteed at least have text of current token
        val tokenStartMarker = _input.mark()
        try {
            outer@ while (true) {
                if (_hitEOF) {
                    emitEOF()
                    return _token
                }
                _token = null
                _channel = Token.DEFAULT_CHANNEL
                _tokenStartCharIndex = _input.index()
                _tokenStartCharPositionInLine = interpreter.charPositionInLine
                _tokenStartLine = interpreter.line
                _text = null
                do {
                    _type = Token.INVALID_TYPE
                    val ttype = try {
                        interpreter.match(_input, _mode)
                    } catch (e: LexerNoViableAltException) {
                        notifyListeners(e) // report error
                        recover(e)
                        Token.INVALID_TYPE
                    }
                    if (_input.LA(1) == IntStream.EOF) {
                        _hitEOF = true
                    }
                    if (_type == Token.INVALID_TYPE) _type = ttype
                    if (_type == SKIP) {
                        continue@outer
                    }
                } while (_type == MORE)
                if (_token == null) emit()
                return _token
            }
        } finally {
            // make sure we release marker after match or
            // unbuffered char stream will keep buffering
            _input.release(tokenStartMarker)
        }
    }
}