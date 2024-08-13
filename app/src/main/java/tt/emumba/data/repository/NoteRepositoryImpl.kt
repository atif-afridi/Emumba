package tt.emumba.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import tt.emumba.data.database.daos.NoteDao
import tt.emumba.data.database.model.NoteEntity
import tt.emumba.domain.model.Note
import tt.emumba.domain.repository.NoteRepository

class NoteRepositoryImpl(
//    private val noteDao: NoteDao
    private val noteDao: NoteDao
) : NoteRepository {
    override suspend fun insert(note: Note) {
        noteDao.insert(note.toEntity())
    }

    override suspend fun insertAll(notes: List<Note>) {
        noteDao.insertAll(notes.map { it.toEntity() })
    }

    override suspend fun update(note: Note) {
        noteDao.update(note.toEntity())
    }

    override suspend fun delete(note: Note) {
        noteDao.delete(note.toEntity())
    }

    override suspend fun deleteAll() {
        noteDao.deleteAll()
    }

    override suspend fun getNoteById(id: Int): Note? {
        return noteDao.getNoteById(id)?.toDomainModel()
    }

    override fun getAllNotes(): Flow<List<Note>> {
        return noteDao.getAllNotes().map { entities ->
            entities.map { it.toDomainModel() }
        }
    }

    private fun Note.toEntity(): NoteEntity {
        return NoteEntity(id = id, title = title, content = content)
    }

    private fun NoteEntity.toDomainModel(): Note {
        return Note(id = id, title = title, content = content)
    }
}