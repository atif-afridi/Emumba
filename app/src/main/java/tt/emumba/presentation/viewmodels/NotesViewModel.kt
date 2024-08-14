package tt.emumba.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import tt.emumba.data.NetworkResult
import tt.emumba.domain.model.Category
import tt.emumba.domain.model.Product
import tt.emumba.domain.usecase.GetCategoriesUseCase
import tt.emumba.domain.usecase.GetProductsUseCase

class NotesViewModel(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    init {
        fetchCategories()
        fetchProducts()
    }

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories: Flow<List<Category>>
        get() = _categories

    private val _products = MutableStateFlow(emptyList<Product>())

    private val _category = MutableStateFlow<Category?>(null)
    val products: Flow<List<Product>>
        get() = combine(_products, _category) { _notes, _category ->
            if (_category != null) {
                _notes.filter { it.category?.id == _category.id }
            } else {
                _notes
            }
        }


    private fun fetchCategories() {

        viewModelScope.launch(Dispatchers.IO) {
            getCategoriesUseCase.invoke().collect {
                when (it) {
                    is NetworkResult.Success -> {
                        it.data?.let { list ->
                            _categories.value = list.apply {
                                it.data[0].initialSelectedValue = true
                            }
                        }
                    }
                    is NetworkResult.Error -> {

                    }
                    is NetworkResult.Loading -> {

                    }
                }
            }
        }
    }
    private fun fetchProducts() {

        viewModelScope.launch(Dispatchers.IO) {
            getProductsUseCase.invoke().collect {
                when (it) {
                    is NetworkResult.Success -> {
                        it.data?.let { list ->
                            _category.value = list[0].category
                            _products.value = list
                        }
                    }
                    is NetworkResult.Error -> {

                    }
                    is NetworkResult.Loading -> {

                    }
                }
            }
        }
    }

    fun onCategorySelected(category: Category) {
        _category.value = category
    }
}