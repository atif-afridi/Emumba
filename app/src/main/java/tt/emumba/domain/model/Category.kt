package tt.emumba.domain.model

import androidx.compose.runtime.mutableStateOf


data class Category(
    val id: Int = -1,
    val name: String,
    val image: String? = null,
    val creationAt: String? = null,
    val updatedAt: String? = null,
    // non-api local check
    var initialSelectedValue: Boolean = false
) {

//    var selected by mutableStateOf(initialSelectedValue)


}