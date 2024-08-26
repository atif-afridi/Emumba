package tt.emumba.ui.models

import tt.emumba.domain.model.Category

data class CategoryDO(
    val category: Category,
    val isSelected: Boolean = false,
)