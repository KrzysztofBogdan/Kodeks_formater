package pl.codeweaver.kodeks.formater.regexp

import pl.codeweaver.kodeks.formater.Kodeks
import pl.codeweaver.kodeks.formater.MarkdownTableFormatter

fun main(args: Array<String>) {
    generateTextAndMdFile("/kodeks_postepowania_w_sprawach_o_wykroczenia.txt", "kodeks_postepowania_w_sprawach_o_wykroczenia.txt", "kpwsow-README.md")
    generateTextAndMdFile("/kodeks_postepowania_administracyjnego.txt", "kodeks_postepowania_administracyjnego.txt", "kpa-README.md")
    generateTextAndMdFile("/kodeks_karny_skarbowy.txt", "kodeks_karny_skarbowy.txt", "kks-README.md")
    generateTextAndMdFile("/kodeks_wyborczy.txt", "kodeks_wyborczy.txt", "kw-README.md")
    generateTextAndMdFile("/kodeks_postepowania_karnego.txt", "kodeks_postepowania_karnego.txt", "kpk-README.md")
    generateTextAndMdFile("/kodeks_karny.txt", "kodeks_karny.txt", "kk-README.md")
}

fun generateTextAndMdFile(resource: String, textFile: String, readmeFile: String) {
    Kodeks.load(resource).
        replace("\t", " ").
        // Opakowje w tabelke
        replace("""([0-9]{4}-[0-9]{2}-[0-9]{2})? (zm\.|sprost\.|zm.wyn.z) DZ(.*)""", """| $1 | $2 DZ$3 |""").
        // Header tabelki
        replace("^Dz.U.([0-9]{4})(.*)", "| Dz.U.$1$2 | |\n| - | - |").
        // Łamie Art.
        replace("Art\\.([0-9a-zA-Z\\[\\]]+)\\.(.*)", "Art. $1\n$2").
        // Dodaje dwie spacje przed paragrafem, dodaje spacje po kropce
        replace("""§([0-9a-zA-Z]+)(\[[0-9]+\]\.|\.)(.*)""", """  §$1$2 $3""").
        // Dodaje dwie spacje przed wypunktowaniem, dodaje spacje po nawiasie ")"
        replace("\n([0-9a-zA-Z]{0,2})\\)(.*)", "\n  $1) $2").
        replace("\nUSTAWA", "\n\nUSTAWA").
        // Fix niepotrzebnego endl w art.
        replace("Art. ([0-9a-zA-Z\\[\\]]+)\n\n", "Art. $1\n").
        // Formatowanie tabelki
        replace("\\|(.*\n){1,500}\\|.*\\|", { MarkdownTableFormatter.format(it.value) }).

        write(textFile).

        replace("\n(Art.*)", "\n###### $1").
        replace("\n(ROZDZIA[Ł|ł] .*)", "\n##### $1").
        replace("\n(DZIA[Ł|ł].*)", "\n#### $1").
        replace("\n(TYTU[Ł|ł] .*)", "\n### $1").
        replace("\n(ODDZIA[Ł|ł] .*)", "\n### $1").
        replace("\n(KSI[Ę|ę]GA .*)", "\n## $1").
        replace("\n  (§[0-9a-zA-Z]+.*)", "\n$1\n").
        replace("\n  ([0-9a-zA-Z]{0,2}\\)+.*)", "\n$1\n").
        replace("\nUSTAWA\n(.*)\n(.*)\n", "\n# USTAWA\n$1\n## $2\n").


        // Kodeks wyborczy
        replace("\nOKRĘG WYBORCZY NR", "\n##### OKRĘG WYBORCZY NR").
        // Kodeks wyborczy
        replace("\n WYKAZ OKRĘGÓW WYBORCZYCH DO SEJMU RZECZYPOSPOLITEJ POLSKIEJ", "\n#### WYKAZ OKRĘGÓW WYBORCZYCH DO SEJMU RZECZYPOSPOLITEJ POLSKIEJ").
        // Kodeks wyborczy
        replace("\nZAŁĄCZNIK Nr", "\n### ZAŁĄCZNIK Nr").
        // Kodeks wyborczy
        replace("\nZAŁĄCZNIKI", "\n## ZAŁĄCZNIKI").

        write(readmeFile)
}