package io.github.riej.lsl

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VfsUtil
import com.intellij.psi.PsiManager
import com.intellij.psi.xml.XmlFile
import com.intellij.psi.xml.XmlTag
import com.intellij.util.ResourceUtil
import com.intellij.xml.util.XmlUtil
import io.github.riej.lsl.psi.*


class KwdbData(project: Project) {
    val data: XmlFile
    val lang = "en"
    val generated: LslFile

    init {
        val resource = ResourceUtil.getResource(javaClass.classLoader, ".", "kwdb.xml")
        val file = VfsUtil.findFileByURL(resource)
        data = PsiManager.getInstance(project).findFile(file!!) as XmlFile

        generated = io.github.riej.lsl.psi.LslElementFactory.createFile(project, generateSource())
    }

    fun commentDescription(description: String): String =
        description.trim().split('\n').filter { it != "<!-- TODO: add documentation -->" }
            .joinToString("\n") { "// $it" }

    fun isSLGrid(tag: XmlTag): Boolean =
        (tag.getAttributeValue("grid") ?: "sl").split(' ').contains("sl")

    /**
     * Generates LSL source code for KWDB file.
     * All descriptions will be put as comments before declaration.
     */
    fun generateSource(): String {
        val sb = StringBuilder()
        val subtags = data.rootTag?.subTags?.filter { isSLGrid(it) }
        subtags?.forEach { tag ->
            when (tag.name) {
                "constant" -> let {
                    tag.findSubTags("description").forEach { description ->
                        sb.append("${commentDescription(description.value.text)}\n")
                    }

                    val type = tag.getAttributeValue("type")
                    val value = when (type) {
                        "string", "key" -> "\"${tag.getAttributeValue("value")}\""
                        "vector", "rotation", "quaternion" -> XmlUtil.unescape(tag.getAttributeValue("value")!!)
                        else -> tag.getAttributeValue("value")
                    }

                    sb.append("$type ${tag.getAttributeValue("name")} = $value;\n")
                }

                "function" -> let {
                    tag.findSubTags("description").forEach { description ->
                        sb.append("${commentDescription(description.value.text)}\n")
                    }

                    val type = tag.getAttributeValue("type")
                    if (type != null) {
                        sb.append("$type ")
                    }
                    sb.append("${tag.getAttributeValue("name")}(")
                    tag.findSubTags("param").forEachIndexed { index, param ->
                        if (index != 0) {
                            sb.append(", ")
                        }
                        sb.append("${param.getAttributeValue("type")} ${param.getAttributeValue("name")}")
                    }
                    sb.append(") {}\n")
                }
            }
        }

        sb.append("default {\n")
        subtags?.filter { it.name == "event" }?.forEach { tag ->
            tag.findSubTags("description").forEach { description ->
                sb.append("    ${commentDescription(description.value.text)}\n")
            }

            sb.append("    ${tag.getAttributeValue("name")} (")
            tag.findSubTags("param").forEachIndexed { index, param ->
                if (index != 0) {
                    sb.append(", ")
                }
                sb.append("${param.getAttributeValue("type")} ${param.getAttributeValue("name")}")
            }
            sb.append(") {}\n")
        }
        sb.append("}\n")

        return sb.toString()
    }

    val functions: Map<String, LslFunction> by lazy {
        generated.children.filterIsInstance<LslFunction>().associateBy { it.name!! }
    }

    val constants: Map<String, LslGlobalVariable> by lazy {
        generated.children.filterIsInstance<LslGlobalVariable>().associateBy { it.name!! }
    }

    val events: Map<String, LslEvent> by lazy {
        generated.children
            .firstOrNull { it is LslStateDefault }
            ?.children
            ?.filterIsInstance<LslEvent>()
            ?.associateBy { it.name!! }
            .orEmpty()
    }

    fun findElementByName(name: String?): LslNamedElement? {
        if (name == null) {
            return null
        }

        val event = events[name]
        if (event != null) {
            return event
        }

        return generated.children.firstOrNull { (it is LslNamedElement) && (it.getName() == name) } as LslNamedElement?
    }
}