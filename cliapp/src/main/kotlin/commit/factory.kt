package main.commit

import java.util.Date

interface Factory {
    fun compose(commitMessage: String, dateStr: String): Commit
}

class FactoryImpl(private val parser: MessageParser) : Factory {

    override fun compose(commitMessage: String, dateStr: String): Commit {
        val message = parser.parse(commitMessage)
        return Commit(message, Date())
    }
}
