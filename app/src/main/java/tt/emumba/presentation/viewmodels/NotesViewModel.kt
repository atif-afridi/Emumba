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
        get() = combine(_products, _category) { _products, _category ->
            if (_category != null) {
                _products.filter { it.category?.id == _category.id }
            } else {
                _products
            }
        }


    private fun fetchCategories() {

        viewModelScope.launch(Dispatchers.IO) {
            getCategoriesUseCase.invoke().collect {
                when (it) {
                    is NetworkResult.Success -> {
                        var temp: Category? = null
                        it.data?.let { list ->
                            _categories.value = list.mapIndexed { index, category ->
                                if (index == 0) {
                                    temp = category
                                    category.copy(initialSelectedValue = true)
                                } else {
                                    category
                                }
                            }
                            temp?.let {
                                onCategorySelected(it)
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