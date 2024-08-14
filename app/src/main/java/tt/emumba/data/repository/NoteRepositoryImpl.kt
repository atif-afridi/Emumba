package tt.emumba.data.repository

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import tt.emumba.data.NetworkResult
import tt.emumba.data.remote.api.ApiClient
import tt.emumba.data.remote.model.CategoryJson
import tt.emumba.data.remote.model.ProductJson
import tt.emumba.domain.model.Category
import tt.emumba.domain.model.Product
import tt.emumba.domain.repository.NoteRepository

class NoteRepositoryImpl(
    private val apiClient: ApiClient
) : NoteRepository {
    //    override suspend fun getCategories() {
//        apiClient.getAllCategories()
//    }
    override suspend fun getCategories() = flow<NetworkResult<List<CategoryJson>>> {
        emit(NetworkResult.Loading())
        with(apiClient.getAllCategories()) {
            if (isSuccessful) {
                emit(NetworkResult.Success(this.body()))
            } else {
                emit(NetworkResult.Error(this.errorBody().toString()))
            }
        }
    }.catch {
        emit(NetworkResult.Error(it.localizedMessage))
    }

    override suspend fun getProducts() = flow<NetworkResult<List<ProductJson>>> {
        emit(NetworkResult.Loading())
        with(apiClient.getCategoryProducts()) {
            if (isSuccessful) {
                emit(NetworkResult.Success(this.body()))
            } else {
                emit(NetworkResult.Error(this.errorBody().toString()))
            }
        }
    }.catch {
        emit(NetworkResult.Error(it.localizedMessage))
    }
}