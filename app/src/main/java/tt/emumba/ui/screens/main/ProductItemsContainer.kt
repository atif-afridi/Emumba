package tt.emumba.ui.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import tt.emumba.ui.theme.constants.MediumDp
import tt.emumba.domain.model.Product

@Composable
fun ProductItemsContainer(
    modifier: Modifier = Modifier,
    productItemsFlow: Flow<List<Product>> = flowOf(listOf()),
    overlappingElementsHeight: Dp = 0.dp
) {
    val products = productItemsFlow.collectAsState(initial = listOf()).value
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(MediumDp),
        verticalArrangement = Arrangement.spacedBy(MediumDp)
    ) {
        items(products, /*key = { it.id }*/) { item ->
            ProductItemUi(
                productItem = item,
            )
        }
        item { Spacer(modifier = Modifier.height(overlappingElementsHeight)) }
    }
}

@Preview
@Composable
fun ProductItemsContainerPreview() {
    ProductItemsContainer(
        productItemsFlow = flowOf(listOf())
    )
}