package tt.emumba.domain.usecase

import kotlinx.coroutines.flow.Flow
import tt.emumba.domain.model.Note
import tt.emumba.domain.repository.NoteRepository

class GetNotesUseCase(private val repository: NoteRepository) {
    operator fun invoke(): Flow<List<Note>> {
        return repository.getAllNotes()
    }
}