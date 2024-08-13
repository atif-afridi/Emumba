package tt.emumba.ui.composeable

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tt.emumba.domain.model.Category

@Composable
fun LabelSelectorBar(
    labelItems: List<Category> = listOf(),
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
    // 3. Stateful Selection Management:
    val selectedLabel = rememberSaveable { mutableStateOf(labelItems.firstOrNull()?.name ?: "") }
    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.height(barHeight)
    ) {
        item { Spacer(modifier = Modifier.width(horizontalPadding)) }
        // 4. Interactive Label Identification
        items(labelItems) { label ->
            LabelUi(
                category = label,
                selected = label.name == selectedLabel.value,
                labelTextStyle = labelTextStyle,
                backgroundColor = backgroundColor,
                selectedBackgroundColor = selectedBackgroundColor,
                textColor = textColor,
                selectedTextColor = selectedTextColor,
                roundedCornerShapeSize = roundedCornerShapeSize,
                horizontalPadding = labelHorizontalPadding,
                verticalPadding = labelVerticalPadding,
//                onClick = onCategoryClick,
            ) {
                selectedLabel.value = label.name
                onCategoryClick.invoke(label)
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
        labelItems = listOf(
            Category(name = "All", initialSelectedValue = true),
            Category(name = "Pop"),
            Category(name = "Rock"),
            Category(name = "Jazz"),
            Category(name = "Jazz"),
            Category(name = "Hip Hop"),
            Category(name = "Classical")
        )
    )
}