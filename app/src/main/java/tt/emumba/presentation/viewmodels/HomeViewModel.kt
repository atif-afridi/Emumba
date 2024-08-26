package tt.emumba.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import tt.emumba.data.NetworkResult
import tt.emumba.domain.model.Category
import tt.emumba.domain.model.Product
import tt.emumba.domain.usecase.GetCategoriesUseCase
import tt.emumba.domain.usecase.GetProductsUseCase
import tt.emumba.ui.models.CategoryDO

class HomeViewModel(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    init {
        fetchCategories()
        fetchProducts()
    }

    private val _selectedCategory = MutableStateFlow<Category?>(null)

    private val _categories = MutableStateFlow<List<CategoryDO>>(emptyList())

    private val _apiLoader = MutableStateFlow<Boolean>(false)
    val apiLoading = _apiLoader.asStateFlow()

    private val _apiError = MutableStateFlow<String?>(null)
    val apiError = _apiError.asStateFlow()

    private val _products = MutableStateFlow(emptyList<Product>())

    val categories: Flow<List<CategoryDO>>
        get() = combine(_categories, _selectedCategory) { categories, selectedCategory ->
            categories.map { categoryDO ->
                val isSelected = categoryDO.category.id == selectedCategory?.id
                categoryDO.copy(isSelected = isSelected)
            }
        }

    val products: Flow<List<Product>>
        get() = combine(
            _products,
            _selectedCategory,
            ) { products, selectedCategory ->
            if (selectedCategory != null) {
                products.filter { it.category?.id == selectedCategory.id }
            } else {
                products
            }
        }


    private fun fetchCategories() {

        viewModelScope.launch(Dispatchers.IO) {
            _apiLoader.emit(true)
            getCategoriesUseCase.invoke().collect { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        if (!result.data.isNullOrEmpty()) {
                            val categories = result.data.map { category ->
                                CategoryDO(category)
                            }
                            _categories.emit(categories)
                            _selectedCategory.emit(categories[0].category)

                            result.data.forEach {
                                Log.d("TAG", "Category: $it")
                            }
                        }
                    }

                    is NetworkResult.Error -> {
                        _apiError.emit(result.message)
                    }

                    is NetworkResult.Loading -> {
                        _apiLoader.emit(true)
                    }
                }
            }
        }
    }

    private fun fetchProducts() {

        viewModelScope.launch(Dispatchers.IO) {
            getProductsUseCase.invoke().collect {result ->
                when (result) {
                    is NetworkResult.Success -> {
                        _apiLoader.emit(false)
                        if (!result.data.isNullOrEmpty()) {
                            val products = result.data
                            _products.emit(products)
                            result.data.forEach {
                                Log.d("TAG", "Product: $it")
                            }
                        }
                    }

                    is NetworkResult.Error -> {
                        _apiLoader.emit(false)
                    }

                    is NetworkResult.Loading -> {
                    }
                }
            }
        }
    }

    fun onCategorySelected(category: Category) {
        viewModelScope.launch {
            _selectedCategory.emit(category)
        }
    }

}