package tt.emumba.ui.composeable

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
import tt.emumba.ui.constants.MediumDp
import tt.emumba.domain.model.Note

@Composable
fun ProductItemsContainer(
    modifier: Modifier = Modifier,
    todoItemsFlow: Flow<List<Note>> = flowOf(listOf()),
    onItemClick: (Note) -> Unit = {},
    onItemDelete: (Note) -> Unit = {},
    overlappingElementsHeight: Dp = 0.dp
) {
    // 1. Flow Data Collection
    val todos = todoItemsFlow.collectAsState(initial = listOf()).value
    // 2. LazyColumn Setup
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(MediumDp),
        verticalArrangement = Arrangement.spacedBy(MediumDp)
    ) {
        // 3. Items Rendering
        items(todos, /*key = { it.id }*/) { item ->
            ProductItemUi(
                todoItem = item,
//                onItemClick = onItemClick,
//                onItemDelete = onItemDelete
            )
        }
        // 4. Layout Adjustment
        item { Spacer(modifier = Modifier.height(overlappingElementsHeight)) }
    }
}

@Preview
@Composable
fun ProductItemsContainerPreview() {
    ProductItemsContainer(
        todoItemsFlow = flowOf(
            listOf(
                Note(title = "Todo Item 1", isDone = true),
                Note(title = "Todo Item 2"),
                Note(title = "Todo Item 3"),
                Note(title = "Todo Item 4", isDone = true),
            )
        )
    )
}