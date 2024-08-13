package tt.emumba.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import tt.emumba.domain.model.Category

interface ApiClient {

    @GET("/")
    suspend fun getAllCategories(): Response<List<Category>>

    @GET("/")
    suspend fun getCategoryProducts(): Response<List<Category>>

}