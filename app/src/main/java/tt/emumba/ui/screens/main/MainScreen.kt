package tt.emumba.ui.screens.main

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
import tt.emumba.presentation.viewmodels.NotesViewModel
import tt.emumba.ui.theme.constants.OverlappingHeight

@Composable
internal fun MainScreen(viewModel: NotesViewModel) {

    Scaffold(modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)) { innerPadding ->
        Column {
            Row {
                val context = LocalContext.current
                LabelSelectorBar(
                    categoryItemFlow =viewModel.categories,
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
                        viewModel.onCategorySelected(category)
                        /*Toast.makeText(
                            context,
                            "This is a Toast. Yay! ${category.name}",
                            Toast.LENGTH_SHORT
                        ).show()*/
                    }
                )
            }
            Column {
                ProductItemsContainer(
                    productItemsFlow = viewModel.products,
                    overlappingElementsHeight = OverlappingHeight
                )
            }
        }


    }
}

@Preview
@Composable
fun MainScreenPreview() {

}