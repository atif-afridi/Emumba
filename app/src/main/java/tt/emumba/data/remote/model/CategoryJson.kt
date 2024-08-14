package tt.emumba.data.remote.model

data class CategoryJson(
    val id: Int = -1,
    val name: String,
    val image: String? = null,
    val creationAt: String? = null,
    val updatedAt: String? = null,
)