package tt.emumba.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import tt.emumba.data.remote.model.CategoryJson
import tt.emumba.data.remote.model.ProductJson
import tt.emumba.domain.model.Category
import tt.emumba.domain.model.Product

interface ApiClient {

    @GET("/api/v1/categories")
    suspend fun getAllCategories(): Response<List<CategoryJson>>

    @GET("/api/v1/products")
    suspend fun getCategoryProducts(): Response<List<ProductJson>>

}