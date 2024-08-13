package tt.emumba.ui.screens.main

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.flowOf
import tt.emumba.domain.model.Category
import tt.emumba.domain.model.Note
import tt.emumba.ui.theme.constants.OverlappingHeight

@Composable
internal fun MainScreen() {
    Scaffold(modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)) { innerPadding ->

        Column {
            Row {
                val context = LocalContext.current
                LabelSelectorBar(
                    //                                labelItems = listOf(
                    //                                    "All", "Pop", "Rock", "Jazz", "Hip Hop", "Classical"
                    //                                ),
                    labelItems = listOf(
                        Category(name = "All", initialSelectedValue = true),
                        Category(name = "Pop"),
                        Category(name = "Rock"),
                        Category(name = "Jazz"),
                        Category(name = "Hip Hop"),
                        Category(name = "Classical")
                        //                                    "All", "Pop", "Rock", "Jazz", "Hip Hop", "Classical"
                    ),
                    barHeight = 80.dp,
                    horizontalPadding = 12.dp,
                    distanceBetweenItems = 8.dp,
                    backgroundColor = Color(0xFFB2F0AD),
                    selectedBackgroundColor = Color(0xFF294D16),
                    textColor = Color(0xFF333333),
                    selectedTextColor = Color(0xFFD8D1D1),
                    roundedCornerShapeSize = 24.dp,
                    labelVerticalPadding = 14.dp,
                    labelHorizontalPadding = 18.dp,
                    onCategoryClick = { category ->
                        Toast.makeText(
                            context,
                            "This is a Toast. Yay! ${category.name}",
                            Toast.LENGTH_SHORT
                        ).show()
                        // change the product list on this click...

                    }
                )
            }
            Column {
                ProductItemsContainer(
                    // 1. Mock Data for Todo Items
                    todoItemsFlow = flowOf(
                        listOf(
                            Note(title = "Todo Item 1"),
                            Note(title = "Todo Item 2", isDone = true),
                            Note(title = "Todo Item 3"),
                            Note(title = "Todo Item 4", isDone = true),
                            Note(title = "Todo Item 5"),
                            Note(title = "Todo Item 6"),
                            Note(title = "Todo Item 7"),
                            Note(title = "Todo Item 8"),
                            Note(title = "Todo Item 9"),
                            Note(title = "Todo Item 10"),
                            Note(title = "Todo Item 11", isDone = true),
                            Note(title = "Todo Item 12"),
                            Note(title = "Todo Item 13"),
                            Note(title = "Todo Item 14"),
                            Note(title = "Todo Item 15")
                        )
                    ),
                    onItemClick = {},
                    onItemDelete = {},
                    // 2. Space Adjustment for Overlapping UI Elements
                    overlappingElementsHeight = OverlappingHeight
                )
            }
        }


    }
}

@Preview
@Composable
fun MainScreenPreview() {
//    KoinApplication(
//        application = { modules(viewModels) },
////        content = { NotesScreen(viewModel = koinViewModel()) }
//    )
}