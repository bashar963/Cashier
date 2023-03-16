package app.snapcart.cashier.ui.view.home.fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.snapcart.cashier.R

@Composable
fun SalesFragment() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFECECEC))
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        BoxWithConstraints {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                TodaySalesWidget()
                Spacer(modifier = Modifier.height(16.dp))
                TodaySalesCardWidget()
                Spacer(modifier = Modifier.height(70.dp))
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 50.dp)
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .align(Alignment.BottomCenter)
                    .background(Color(0xFF4DB6AC))
                    .clickable {
                    }
            ) {
                CreateSellButton()
            }
        }
    }
}

@Composable
fun CreateSellButton() {
    Row(
        modifier = Modifier.padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(8.dp))
        Icon(
            painterResource(id = R.drawable.ic_sell),
            modifier = Modifier.size(64.dp),
            contentDescription = "Sale",
            tint = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = "CREATE NEW SALE",
            fontSize = 21.sp,
            color = MaterialTheme.colorScheme.onPrimary,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            modifier = Modifier.size(32.dp),
            imageVector = Icons.Rounded.KeyboardArrowRight,
            contentDescription = "Sell button",
            tint = MaterialTheme.colorScheme.onPrimary
        )
        Spacer(modifier = Modifier.width(6.dp))
    }
}

@Composable
fun TodaySalesWidget() {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Today’s Sales",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.secondary,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = "Rp0",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.secondary,
            fontSize = 36.sp
        )
    }
}

@Composable
fun TodaySalesCardWidget() {
    // the values for mocks
    val cards = listOf<Map<String, Any>>(
        mapOf(pair = Pair("Cash", "Rp0")),
        mapOf(pair = Pair("Collectible", "Rp0")),
        mapOf(pair = Pair("Card", "Rp0"))
    )
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        repeat(cards.size) {
            val card = cards[it]
            Box(modifier = Modifier.weight(1f)) {
                CardWidget(
                    title = card.keys.first(),
                    titleColor = getColor(card.keys.first()),
                    value = card.values.first().toString(),
                    showDivider = (it + 1) < cards.size,
                    noPaddingFirst = it == 0
                )
            }
        }
    }
}

// just for mocks
fun getColor(key: String): Color {
    return when (key) {
        "Cash" -> Color(0xFFEEB342)
        "Collectible" -> Color(0xFFFF6D79)
        "Card" -> Color(0xFF4073A9)
        else -> { Color(0xFFEEB342) }
    }
}

@Composable
fun CardWidget(
    title: String,
    titleColor: Color,
    value: String,
    showDivider: Boolean,
    noPaddingFirst: Boolean
) {
    Row(
        modifier = Modifier
            .height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.Start
    ) {
        if (!noPaddingFirst) {
            Spacer(modifier = Modifier.width(8.dp))
        }
        Column(
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                color = titleColor,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 20.sp
            )
        }
        if (showDivider) {
            Divider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
            )
        }
    }
}
