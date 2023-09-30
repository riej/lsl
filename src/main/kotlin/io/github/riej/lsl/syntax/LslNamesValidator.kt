package io.github.riej.lsl.syntax

import com.intellij.lang.refactoring.NamesValidator
import com.intellij.openapi.project.Project
import io.github.riej.lsl.KwdbData

class LslNamesValidator : NamesValidator {
    companion object {
        private val identifierRegex = Regex("^[a-zA-Z][a-zA-Z0-9_]*$")
    }

    override fun isKeyword(name: String, project: Project): Boolean =
        listOf("default", "state", "jump", "return", "if", "else", "while", "do", "for", "print")
            .plus(KwdbData.getInstance(project).events.keys)
            .contains(name)

    override fun isIdentifier(name: String, project: Project): Boolean =
        identifierRegex.matches(name) && !isKeyword(name, project)
}