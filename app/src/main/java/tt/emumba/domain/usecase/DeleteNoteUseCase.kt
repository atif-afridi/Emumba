package tt.emumba.domain.usecase

import tt.emumba.data.source.NoteDataSource

class DeleteNoteUseCase(private val roomDataSource: NoteDataSource) {

    suspend fun execute() {
        roomDataSource.deleteAll()
    }
}