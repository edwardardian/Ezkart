package com.dicoding.ezkart.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.ezkart.R
import com.dicoding.ezkart.model.Menu

@Composable
fun ListItem(menu: Menu, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.width(200.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
    ) {
        Column {
            Image(
                painter = painterResource(menu.image),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(210.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = menu.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                )
                Text(
                    text = menu.price,
                    style = MaterialTheme.typography.titleSmall,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MenuItemPreview() {
    MaterialTheme {
        ListItem(
            menu = Menu(R.drawable.img_iphone_15_pro, "iPhone 15 Pro", "Rp20.999.000"),
            modifier = Modifier.padding(8.dp)
        )
    }
}
