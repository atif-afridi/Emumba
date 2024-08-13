package tt.emumba.domain.usecase

import tt.emumba.domain.repository.NoteRepository


class DeleteNoteUseCase(private val noteRepository: NoteRepository) {

    suspend fun execute() {
        noteRepository.deleteAll()
    }
}