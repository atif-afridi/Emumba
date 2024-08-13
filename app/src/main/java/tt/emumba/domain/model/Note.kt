package tt.emumba.domain.model

import java.util.UUID

data class Note(
//    val id: Int,
//    val id: UUID = UUID.randomUUID(),
    val id: Int = 0,
    val title: String,
    val content: String = "",
    // non-api local check
    var isDone: Boolean = false
)