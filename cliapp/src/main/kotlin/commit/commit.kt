package main.commit

import java.util.Date

data class Commit(
    val message: CommitMessage,
    val date: Date,
)

data class CommitMessage(
    val type: String,
    val subModule: String,
    val rawMessage: String,
)
