package pl.codeweaver.kodeks.formater

import java.io.FileOutputStream
import java.io.InputStreamReader
import java.util.regex.Pattern
import kotlin.text.MatchResult

class Kodeks(val data: String) {
    fun replace(what: String, with: String) = Kodeks(replace(data, what, with))
    fun replace(what: String, with: (MatchResult) -> String) = Kodeks(data.replace(what.toRegex(), with))

    fun write(name: String): Kodeks {
        save(name, data)
        return this
    }

    private fun replace(within: String, what: String, with: String) =
        Pattern.compile(what, Pattern.CASE_INSENSITIVE).
            matcher(within).
            replaceAll(with);

    companion object IO {
        fun load(name: String) = Kodeks(loadResource(name))

        fun save(name: String, data: String) =
            FileOutputStream(name, false).use { it.write(data.toByteArray(Charsets.UTF_8)) }

        private fun loadResource(resource: String) =
            InputStreamReader(Class::class.java.getResourceAsStream(resource)).use { it.readText() }
    }
}