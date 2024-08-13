package tt.emumba.data.repository

import kotlinx.coroutines.flow.Flow
import tt.emumba.data.source.NoteDataSource
import tt.emumba.domain.model.Note
import tt.emumba.domain.repository.NoteRepository

class NoteRepositoryImpl(
    private val noteDataSource: NoteDataSource
) : NoteRepository {
    override suspend fun insert(note: Note) {
        noteDataSource.insert(note)
    }

    override suspend fun insertAll(notes: List<Note>) {
        noteDataSource.insertAll(notes)
    }

    override suspend fun update(note: Note) {
        noteDataSource.update(note)
    }

    override suspend fun delete(note: Note) {
        noteDataSource.delete(note)
    }

    override suspend fun deleteAll() {
        noteDataSource.deleteAll()
    }

    override suspend fun getNoteById(id: Int): Note? {
        return noteDataSource.getNoteById(id)
    }

    override fun getAllNotes(): Flow<List<Note>> {
        return noteDataSource.getAllNotes()
    }
}