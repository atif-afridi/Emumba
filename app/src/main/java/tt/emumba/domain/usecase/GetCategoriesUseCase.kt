package tt.emumba.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import tt.emumba.data.NetworkResult
import tt.emumba.domain.mappers.mapCategoryDataItems
import tt.emumba.domain.model.Category
import tt.emumba.domain.repository.NoteRepository

class GetCategoriesUseCase(private val repository: NoteRepository) {

    suspend operator fun invoke(): Flow<NetworkResult<List<Category>>> = flow {
        repository.getCategories().collect { categoryResponse ->
            when (categoryResponse) {
                is NetworkResult.Success -> {
                    val dataList = categoryResponse.data?.map { categoryJson ->
                        categoryJson.mapCategoryDataItems()
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