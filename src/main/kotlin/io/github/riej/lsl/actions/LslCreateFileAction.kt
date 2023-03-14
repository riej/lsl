package io.github.riej.lsl.actions

import com.intellij.ide.actions.CreateFileFromTemplateAction
import com.intellij.ide.actions.CreateFileFromTemplateDialog
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiFile
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.refactoring.suggested.endOffset
import io.github.riej.lsl.LslIcons
import io.github.riej.lsl.psi.LslFile
import io.github.riej.lsl.psi.LslStatementBlock

class LslCreateFileAction : CreateFileFromTemplateAction("Linden Script file", "", LslIcons.FILE), DumbAware {
    override fun buildDialog(project: Project, directory: PsiDirectory, builder: CreateFileFromTemplateDialog.Builder) {
        builder.setTitle("Linden Script file")
            .addKind("Linden Script file", LslIcons.FILE, "Linden Script")
    }

    override fun getActionName(directory: PsiDirectory?, newName: String, templateName: String?): String =
        "Linden Script file"

    override fun postProcess(
        createdElement: PsiFile,
        templateName: String?,
        customProperties: MutableMap<String, String>?
    ) {
        if (createdElement is LslFile) {
            val editor = FileEditorManager.getInstance(createdElement.project).selectedTextEditor ?: return
            val virtualFile = createdElement.containingFile.virtualFile ?: return
            if (FileDocumentManager.getInstance().getDocument(virtualFile) == editor.document) {
                ApplicationManager.getApplication().runWriteAction {
                    PsiDocumentManager.getInstance(createdElement.project).commitDocument(editor.document)

                    // TODO: add newline in new document and put caret on it
                    val brace = PsiTreeUtil.findChildOfType(createdElement, LslStatementBlock::class.java)?.braceLeftEl
                    if (brace != null) {
                        editor.caretModel.moveToOffset(brace.endOffset)
                    }
                }
            }
        }
    }
}