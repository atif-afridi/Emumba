package tt.emumba.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import tt.emumba.domain.model.Product
import tt.emumba.ui.theme.constants.GrayColor
import tt.emumba.ui.theme.constants.LargeDp
import tt.emumba.ui.theme.constants.MediumDp
import tt.emumba.ui.theme.constants.ProductItemBackgroundColor
import tt.emumba.ui.theme.constants.ProductItemHeight
import tt.emumba.ui.theme.constants.ProductItemIconSize
import tt.emumba.ui.theme.constants.ProductItemTextColor
import tt.emumba.ui.theme.constants.ProductItemTitleTextStyle

@Composable
fun ProductItemUi(
    productItem: Product,
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
                .background(ProductItemBackgroundColor.copy(alpha = 0.5f)),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = productItem.images?.get(0) ?: "",
                contentDescription = null,
                modifier = Modifier
                    .padding(LargeDp)
                    .size(ProductItemIconSize)
                    .clip(RoundedCornerShape(50.dp))
                    .height(300.dp)
                    .width(100.dp),
                contentScale = ContentScale.Fit,
                placeholder = painterResource(id = R.drawable.img_placeholder),
                error = painterResource(id = R.drawable.error_img),
            )
            Column(modifier = Modifier
                .weight(1f)
                .fillMaxWidth()) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = productItem.title ?: "",
                    style = ProductItemTitleTextStyle.copy(color = ProductItemTextColor),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textDecoration = null
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                        .padding(4.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Description with ellipsis",
                    style = ProductItemTitleTextStyle.copy(color = GrayColor),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textDecoration = null
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                        .padding(4.dp))
                Row {
                    Text(
//                        modifier = Modifier.fillMaxWidth(),
                        text = "Price ${productItem.price ?: 0}",
                        style = ProductItemTitleTextStyle.copy(color = GrayColor),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textDecoration = null
                    )
                    Spacer(Modifier.weight(1f))
                    Text(
//                        modifier = Modifier.fillMaxWidth(),
                        text = productItem.category?.name ?: "",
                        style = ProductItemTitleTextStyle.copy(color = GrayColor),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textDecoration = null
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ProductItemUiPreview() {
    Column(
        modifier = Modifier.padding(MediumDp),
        verticalArrangement = Arrangement.spacedBy(MediumDp)
    ) {
        ProductItemUi(Product(id= 1,title = "test", price = 100.0, images = emptyList(), category = null, creationAt = "", updatedAt = ""))
        ProductItemUi(Product(id= 2,title = "test", price = 100.0, images = emptyList(), category = null, creationAt = "", updatedAt = ""))
        ProductItemUi(Product(id= 3,title = "test", price = 100.0, images = emptyList(), category = null, creationAt = "", updatedAt = ""))
    }
}