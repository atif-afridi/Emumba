package tt.emumba.data.source

import kotlinx.coroutines.flow.Flow
import tt.emumba.domain.model.Note

interface NoteDataSource {

    suspend fun insert(note: Note)

    suspend fun insertAll(notes: List<Note>)

    suspend fun update(note: Note)

    suspend fun delete(note: Note)

    suspend fun deleteAll()

    suspend fun getNoteById(id: Int): Note?

    fun getAllNotes(): Flow<List<Note>>
}