package tt.cleanarchmvvm.domain.usecase

import tt.emumba.data.source.NoteDataSource
import tt.emumba.domain.model.Note

class InsertNoteUseCase(private val roomDataSource: NoteDataSource) {

    suspend fun execute(note: Note) {
        roomDataSource.insert(note)
    }
}