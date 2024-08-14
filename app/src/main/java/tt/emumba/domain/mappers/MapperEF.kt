package tt.emumba.domain.mappers

import tt.emumba.data.remote.model.CategoryJson
import tt.emumba.data.remote.model.ProductJson
import tt.emumba.domain.model.Category
import tt.emumba.domain.model.Product

fun CategoryJson.mapCategoryDataItems(): Category {
    return Category(
        id = this.id,
        name = this.name,
        image = this.image,
        creationAt = this.creationAt,
        updatedAt = this.updatedAt
    )
}

fun ProductJson.mapProductsDataItems(): Product {
    return Product(
        id = this.id,
        title = this.title,
        price = this.price,
        images= this.images,
        creationAt= this.creationAt,
        updatedAt= this.updatedAt,
        category= this.category,
    )
}