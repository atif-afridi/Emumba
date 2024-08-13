package tt.emumba.domain.usecase

import tt.emumba.domain.model.Note
import tt.emumba.domain.repository.NoteRepository

class InsertNoteUseCase(private val noteRepository: NoteRepository) {

    suspend fun execute(note: Note) {
        noteRepository.insert(note)
    }
}