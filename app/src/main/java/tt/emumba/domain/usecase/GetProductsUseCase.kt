package tt.emumba.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import tt.emumba.data.NetworkResult
import tt.emumba.domain.mappers.mapProductsDataItems
import tt.emumba.domain.model.Product
import tt.emumba.domain.repository.NoteRepository

class GetProductsUseCase(private val repository: NoteRepository) {

    suspend operator fun invoke(): Flow<NetworkResult<List<Product>>> = flow {
        repository.getProducts().collect { categoryResponse ->
            when (categoryResponse) {
                is NetworkResult.Success -> {
                    val dataList = categoryResponse.data?.map { categoryJson ->
                        categoryJson.mapProductsDataItems()
                    }
                    emit(NetworkResult.Success(dataList))
                }

                is NetworkResult.Error -> {
                    emit(NetworkResult.Error(categoryResponse.message))
                }

                is NetworkResult.Loading -> {
                    emit(NetworkResult.Loading())
                }
            }
        }
    }
}