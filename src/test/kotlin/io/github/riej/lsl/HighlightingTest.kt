package io.github.riej.lsl

import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiComment
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiFile
import com.intellij.psi.SyntaxTraverser
import com.intellij.refactoring.suggested.startOffset

class HighlightingTest : LslScriptTest() {

    companion object {
        private val POSSIBLE_SEVERITIES = listOf(
            HighlightSeverity.ERROR,
            HighlightSeverity.WARNING,
            HighlightSeverity.WEAK_WARNING,
        )

        private fun highlightingInfoToString(
            severity: HighlightSeverity,
            document: Document,
            offset: Int,
            description: String
        ): String {
            return "${severity.displayCapitalizedName}@${document.getLineNumber(offset)}: $description"
        }
    }

    fun `test highlighting`() {
        forEachScript("highlighting", ::validateFileHighlighting)
    }

    private fun validateFileHighlighting(file: PsiFile) {
        val document = PsiDocumentManager.getInstance(file.project).getDocument(file)
            ?: throw NullPointerException("No document of file ${file.name}!")
        val highlighting = myFixture.doHighlighting()
            .map { highlightingInfoToString(it.severity, document, it.startOffset, it.description) }

        val expectedHighlighting = SyntaxTraverser.psiTraverser().withRoot(file)
            .filterIsInstance<PsiComment>().mapNotNull { comment ->
                for (severity in POSSIBLE_SEVERITIES) {
                    val prefix = "// ${severity.displayCapitalizedName}:"
                    if (comment.text.startsWith(prefix)) {
                        val description = comment.text.replace(prefix, "").trim()
                        return@mapNotNull highlightingInfoToString(severity, document, comment.startOffset, description)
                    }
                }
                return@mapNotNull null
            }
        assertSameElements(highlighting, expectedHighlighting)
    }
}
