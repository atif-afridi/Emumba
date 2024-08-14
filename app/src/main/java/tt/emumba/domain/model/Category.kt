package tt.emumba.domain.model


data class Category(
    val id: Int = -1,
    val name: String,
    val image: String? = null,
    val creationAt: String? = null,
    val updatedAt: String? = null,
    // non-api local check
    var initialSelectedValue: Boolean = false
)