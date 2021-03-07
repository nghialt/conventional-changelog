package commit

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import main.commit.COMMIT_PATTERN
import main.commit.CommitMessage
import main.commit.RegexParser

class RegexParserTest : StringSpec({
    "success" {
        forAll(
            row(
                "enough component",
                COMMIT_PATTERN,
                "chore(markdown): re-format file",
                CommitMessage(
                    "chore", "markdown",
                    "chore(markdown): re-format file"
                )

            ),
            row(
                "missing submodule",
                COMMIT_PATTERN,
                "chore: re-format file",
                CommitMessage(
                    "chore", "",
                    "chore: re-format file"
                )

            ),
        ) { _, pattern, commitMessage, expected ->
            val parser = RegexParser(pattern)
            parser.parse(commitMessage) shouldBe expected
        }
    }
    "has exception" {
        forAll(
            row(
                "enough component",
                COMMIT_PATTERN,
                "(markdown): re-format file",
                IllegalArgumentException("invalid message"),
            ),
        ) { _, pattern, commitMessage, expected ->
            val parser = RegexParser(pattern)
            try {
                parser.parse(commitMessage) shouldBe expected
            } catch (e: Exception) {
                e shouldBe expected
            }
        }
    }
})
