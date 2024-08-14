package tt.emumba.data.remote.model

import tt.emumba.domain.model.Category

data class ProductJson(
    val id: Int,
    val title: String,
    val price: Double,
    val images: List<String>,
    val creationAt: String,
    val updatedAt: String,
    val category: Category
)