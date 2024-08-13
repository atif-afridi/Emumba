package tt.emumba.domain.repository

import kotlinx.coroutines.flow.Flow
import tt.emumba.domain.model.Note

interface NoteRepository {
    suspend fun getCategories(note: String)
    suspend fun getProducts(note: String)

}