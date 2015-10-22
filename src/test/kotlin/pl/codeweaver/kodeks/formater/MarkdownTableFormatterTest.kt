package pl.codeweaver.kodeks.formater

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class MarkdownTableFormatterTest {

    @Test fun shouldFormatTable() {
        // when
        val formatted = MarkdownTableFormatter.format(
            "| h1 | h2 | h3 |\n |-|-|-|\n| data1 | data2 | data3 |")
        // then
        assertThat(formatted).
            isEqualTo("| h1    | h2    | h3    |\n|-------|-------|-------|\n| data1 | data2 | data3 |\n")
    }

}