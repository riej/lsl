package io.github.riej.lsl

import com.intellij.psi.PsiErrorElement
import com.intellij.psi.PsiFile
import com.intellij.psi.SyntaxTraverser
import com.intellij.testFramework.ParsingTestCase
import com.intellij.testFramework.PsiTestUtil

class ScriptParserTest : LslScriptTest() {

    fun `test parsing`() {
        forEachScript("parsing", ::validateFileParsing)
    }

    private fun validateFileParsing(file: PsiFile) {
        ParsingTestCase.ensureParsed(file)
        ParsingTestCase.ensureCorrectReparse(file)
        val errors = SyntaxTraverser.psiTraverser().withRoot(file).filterIsInstance<PsiErrorElement>()
        if (file.name.startsWith("valid")) {
            assertEmpty(errors)
        } else {
            assertNotEmpty(errors)
        }
        PsiTestUtil.checkFileStructure(file)
    }
}
