package io.github.riej.lsl

import com.intellij.codeInsight.completion.CompletionType
import com.intellij.psi.PsiComment
import com.intellij.psi.PsiFile
import com.intellij.psi.SyntaxTraverser

class CompletionTest : LslScriptTest() {

    companion object {
        private const val SUGGESTIONS_COMMENT_PREFIX = "// Suggestions:"
    }

    fun `test completion`() {
        forEachScript("completion", ::validateCompletion)
    }

    private fun validateCompletion(file: PsiFile) {
        val lookupElements = myFixture.complete(CompletionType.BASIC)!!.toList()
        val lookupStrings = lookupElements.map { it.lookupString }
        val expectedLookupStrings = SyntaxTraverser.psiTraverser().withRoot(file)
            .filterIsInstance<PsiComment>().mapNotNull {  comment ->
                if (!comment.text.startsWith(SUGGESTIONS_COMMENT_PREFIX)) {
                    return@mapNotNull null
                }
                return@mapNotNull comment.text.replaceFirst(SUGGESTIONS_COMMENT_PREFIX, "").split(",")
                    .map { it.trim() }
                    .filter { it.isNotEmpty() }
            }.flatten()
        assertSameElements(lookupStrings, expectedLookupStrings)
    }
}
