package tt.emumba.domain.repository

import kotlinx.coroutines.flow.Flow
import tt.emumba.data.NetworkResult
import tt.emumba.data.remote.model.CategoryJson
import tt.emumba.data.remote.model.ProductJson
import tt.emumba.domain.model.Category
import tt.emumba.domain.model.Product

interface NoteRepository {
    suspend fun getCategories(): Flow<NetworkResult<List<CategoryJson>>>
//    suspend fun getCategories(): Flow<List<Category>>
    suspend fun getProducts(): Flow<NetworkResult<List<ProductJson>>>
//    suspend fun getProducts(): Flow<List<Product>>

}