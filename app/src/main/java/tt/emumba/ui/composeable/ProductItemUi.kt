package tt.emumba.ui.composeable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import tt.emumba.R
import tt.emumba.domain.model.Note
import tt.emumba.ui.constants.GrayColor
import tt.emumba.ui.constants.LargeDp
import tt.emumba.ui.constants.MediumDp
import tt.emumba.ui.constants.ProductItemBackgroundColor
import tt.emumba.ui.constants.ProductItemHeight
import tt.emumba.ui.constants.ProductItemIconSize
import tt.emumba.ui.constants.ProductItemTextColor
import tt.emumba.ui.constants.ProductItemTitleTextStyle

@Composable
fun ProductItemUi(
    todoItem: Note = Note(id = 0, title = "Todo Item", content = "testing"),
    onItemClick: (Note) -> Unit = {},
    onItemDelete: (Note) -> Unit = {}
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(ProductItemHeight),
        elevation = CardDefaults.cardElevation(defaultElevation = LargeDp),
        shape = RoundedCornerShape(size = MediumDp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(ProductItemBackgroundColor.copy(alpha = 0.5f))
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = true)
                ) {
//                    onItemClick(todoItem)
                },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = "https://i.imgur.com/QkIa5tT.jpeg",
                contentDescription = null,
                modifier = Modifier
                    .padding(LargeDp)
                    .size(ProductItemIconSize)
                    .clip(RoundedCornerShape(50.dp)),
                contentScale = ContentScale.Fit,
                placeholder = painterResource(id = R.drawable.img_placeholder),
                error = painterResource(id = R.drawable.error_img),
            )
            Column(modifier = Modifier.weight(1f).fillMaxWidth()) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = todoItem.title,
                    style = ProductItemTitleTextStyle.copy(color = ProductItemTextColor),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
//                    textDecoration = null
                )
                Spacer(
                    modifier = Modifier.fillMaxWidth().height(10.dp).padding(4.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = todoItem.content,
                    style = ProductItemTitleTextStyle.copy(color = GrayColor),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textDecoration = null
                )
                Spacer(
                    modifier = Modifier.fillMaxWidth().height(10.dp).padding(4.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = todoItem.content,
                    style = ProductItemTitleTextStyle.copy(color = GrayColor),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textDecoration = null
                )
            }

            // 6. IconButton for Deletion
//            IconButton(
//                onClick = { onItemDelete(todoItem) },
//                modifier = Modifier.size(TodoItemActionButtonRippleRadius)
//            ) {
//                Icon(
//                    modifier = Modifier.size(TodoItemIconSize),
////                    painter = painterResource(id = R.drawable.ic_delete),
//                    painter = painterResource(id = 0),
//                    contentDescription = null,
//                    tint = iconTintColor
//                )
//            }
        }
    }
}

@Preview
@Composable
fun TodoItemUiPreview() {
    Column(
        modifier = Modifier.padding(MediumDp),
        verticalArrangement = Arrangement.spacedBy(MediumDp)
    ) {
        ProductItemUi(todoItem = Note(id = 0, title = "Todo Item 1", content = "testing"))
        ProductItemUi(
            todoItem = Note(
                id = 0,
                title = "Todo Item 2",
                content = "testing",
                isDone = true
            )
        )
        ProductItemUi(todoItem = Note(id = 0, title = "Todo Item 3", content = "testing"))
        ProductItemUi(
            todoItem = Note(
                id = 0,
                title = "Todo Item 4",
                content = "testing",
                isDone = true
            )
        )
    }
}