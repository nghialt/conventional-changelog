package main.commit

import java.util.regex.Pattern

private const val TYPE = "type"
private const val SUBMODULE = "submodule"
const val COMMIT_PATTERN = "^(?<type>[a-z]+)(\\((?<submodule>.+)\\))?:"

interface MessageParser {
    fun parse(commitMessage: String): CommitMessage
}

class RegexParser(regex: String) : MessageParser {
    private val pattern = Pattern.compile(regex)

    override fun parse(commitMessage: String): CommitMessage {
        val matcher = pattern.matcher(commitMessage)
        if (!matcher.find()) {
            throw IllegalArgumentException("invalid message")
        }
        val type: String = matcher.group(TYPE)

        var subModule = ""
        try {
            subModule = matcher.group(SUBMODULE)
        } catch (e: NullPointerException) {
        }

        return CommitMessage(
            type,
            subModule,
            commitMessage,
        )
    }
}
