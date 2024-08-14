package tt.emumba.domain.model

data class Product(
    val id: Int?,
    val title: String?,
    val price: Double?,
    val images: List<String>?,
    val creationAt: String?,
    val updatedAt: String?,
    val category: Category?,
)
