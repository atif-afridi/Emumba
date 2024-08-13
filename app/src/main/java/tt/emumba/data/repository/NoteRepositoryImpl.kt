package tt.emumba.data.repository

import tt.emumba.data.remote.api.ApiClient
import tt.emumba.domain.repository.NoteRepository

class NoteRepositoryImpl(
    private val apiClient: ApiClient
) : NoteRepository {
    override suspend fun getCategories(note: String) {
        apiClient.getAllCategories()
    }

    override suspend fun getProducts(note: String) {
        apiClient.getCategoryProducts()
    }
}