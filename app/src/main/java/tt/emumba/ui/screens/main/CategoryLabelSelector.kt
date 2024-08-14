package tt.emumba.ui.screens.main

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import tt.emumba.domain.model.Category

@Composable
fun LabelSelectorBar(
    categoryItemFlow: Flow<List<Category>> = flowOf(listOf()),
    barHeight: Dp = 56.dp,
    horizontalPadding: Dp = 8.dp,
    distanceBetweenItems: Dp = 0.dp,
    labelTextStyle: TextStyle = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 16.sp),
    backgroundColor: Color = Color.White,
    selectedBackgroundColor: Color = Color.Black,
    textColor: Color = Color.Black,
    selectedTextColor: Color = Color.White,
    roundedCornerShapeSize: Dp = 8.dp,
    labelHorizontalPadding: Dp = 16.dp,
    labelVerticalPadding: Dp = 8.dp,
    onCategoryClick: (Category) -> Unit = {},
) {
    var categories = categoryItemFlow.collectAsState(initial = listOf()).value
    // Set the first element as selected by default if not already set
    var selectedIndex by rememberSaveable { mutableStateOf(0) }

    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.height(barHeight)
    ) {
        item { Spacer(modifier = Modifier.width(horizontalPadding)) }
        items(categories.size) {index->
            val category = categories[index]
            LabelUi(
                category = category,
                selected =  category.initialSelectedValue,
                labelTextStyle = labelTextStyle,
                backgroundColor = backgroundColor,
                selectedBackgroundColor = selectedBackgroundColor,
                textColor = textColor,
                selectedTextColor = selectedTextColor,
                roundedCornerShapeSize = roundedCornerShapeSize,
                horizontalPadding = labelHorizontalPadding,
                verticalPadding = labelVerticalPadding,
            ) {

                categories = categories.mapIndexed { i, cat ->
                    if(cat.id == category.id){
                        cat.copy(initialSelectedValue = true)
                    }
                    cat.copy(initialSelectedValue = false)
                }

                onCategoryClick.invoke(category)
            }
            Spacer(modifier = Modifier.width(distanceBetweenItems))
        }
        item { Spacer(modifier = Modifier.width(horizontalPadding)) }
    }
}

@Preview
@Composable
fun LabelSelectorBarPreview() {
    LabelSelectorBar(
        categoryItemFlow = flowOf(listOf(
            Category(name = "All", initialSelectedValue = true),
            Category(name = "Pop"),
            Category(name = "Rock"),
            Category(name = "Jazz"),
            Category(name = "Jazz"),
            Category(name = "Hip Hop"),
            Category(name = "Classical")
        ))
    )
}