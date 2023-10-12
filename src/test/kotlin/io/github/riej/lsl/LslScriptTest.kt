package io.github.riej.lsl

import com.intellij.psi.PsiFile
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import com.jetbrains.rd.util.printlnError
import java.nio.file.Files
import java.nio.file.Path
import java.util.function.Consumer
import java.util.stream.Collectors
import kotlin.io.path.name

abstract class LslScriptTest: BasePlatformTestCase() {

    protected fun forEachScript(directoryName: String, consumer: Consumer<PsiFile>) {
        val directoryPath = "/testcases/$directoryName"
        println("- $directoryPath")
        val directoryUrl = javaClass.getResource(directoryPath)
            ?: throw NullPointerException("There is no such resource: $directoryPath")

        val scriptNames = Files.walk(Path.of(directoryUrl.toURI()), 1)
            .filter { path -> Files.isRegularFile(path) && path.toString().endsWith(".lsl") }
            .map { path -> path.name }
            .collect(Collectors.toList())

        val failures: List<Pair<String, Throwable>> = scriptNames.mapNotNull { scriptName ->
            val scriptPath = "$directoryPath/$scriptName"
            println("\t- $scriptName ($scriptPath)")
            val scriptUrl = javaClass.getResource(scriptPath)
                ?: throw NullPointerException("There is no such resource: $scriptPath")
            val contents = scriptUrl.readText().replace("/*caret*/", "<caret>")
            try {
                consumer.accept(myFixture.configureByText(scriptName, contents))
            } catch (e: Throwable) {
                when (e) {
                    is RuntimeException,
                    is AssertionError -> {
                        printlnError("\t\t- [FAILED] [$scriptName] $e")
                        return@mapNotNull Pair(scriptName, e)
                    }
                    else -> throw e
                }
            }
            return@mapNotNull null
        }
        if (failures.isEmpty()) {
            println("Success!")
            return
        }

        printlnError("These script testcases have failed:")
        for (failure in failures) {
            printlnError("- ${failure.first}: ${failure.second}")
            failure.second.printStackTrace()
        }
        throw RuntimeException("Some script testcases have failed!")
    }
}
