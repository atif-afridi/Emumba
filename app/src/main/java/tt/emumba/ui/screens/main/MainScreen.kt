package tt.emumba.ui.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tt.emumba.presentation.viewmodels.HomeViewModel
import tt.emumba.ui.theme.constants.OverlappingHeight

@Composable
internal fun MainScreen(viewModel: HomeViewModel) {

    val categories by viewModel.categories.collectAsState(initial = emptyList())
    val isApiLoading by viewModel.apiLoading.collectAsState(initial = true)
    val isApiError by viewModel.apiError.collectAsState(initial = null)

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(top = 10.dp, bottom = 10.dp)) {
        if (isApiLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            if(isApiError == null){
                Column {
                    LabelSelectorBar(
                        categories = categories,
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
                        }
                    )
                    ProductItemsContainer(
                        productItemsFlow = viewModel.products,
                        overlappingElementsHeight = OverlappingHeight
                    )
                }
            }else {
                Snackbar(modifier = Modifier.align(Alignment.BottomCenter)) {
                    Text(text = isApiError ?: "Please try again")
                }
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {

}