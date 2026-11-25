package uz.ttpu.composepractice

import android.content.Context
import android.os.Build
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
fun Question1() {
    Text(
        text = "Hi",
        modifier = Modifier
            .padding(16.dp)
            .background(Color.Yellow)
    )
}

@Composable
fun Question2() {
    Text(
        text = "Hi",
        modifier = Modifier
            .background(Color.Yellow)
            .padding(16.dp)
    )
}

@Composable
fun Question3(onIconClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clickable { onIconClick() }
            .padding(12.dp)
            .size(24.dp)
    )

    Box(
        modifier = Modifier
            .padding(12.dp)
            .clickable { onIconClick() }
            .size(24.dp)
    )
}

@Composable
fun Question4(onContinueClick: () -> Unit) {
    Button(
        onClick = { onContinueClick() },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Continue")
    }
}

@Composable
fun Question5() {
    Box(
        modifier = Modifier
            .size(12.dp)
            .background(Color.Green)
    )
}

@Composable
fun Question6() {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text("Card content")
    }
}

@Composable
fun Question7() {
    Column(
        modifier = Modifier
            .background(
                color = Color(0xFFEADDFF),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp)
    ) {
        Text("Product card")
    }
}

@Composable
fun Question8() {
    Column(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(12.dp)
            )
            .background(
                color = Color(0xFFEADDFF),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp)
    ) {
        Text("Product card with border")
    }
}

@Composable
fun Question9() {
    Box(
        modifier = Modifier.offset(x = 4.dp, y = 2.dp)
    ) {
        Text("Tooltip")
    }
}

@Composable
fun Question10() {
    var isEnabled by remember { mutableStateOf(false) }
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Enable notifications",
            modifier = Modifier.weight(2f)
        )
        Switch(
            checked = isEnabled,
            onCheckedChange = { isEnabled = it },
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun Question11() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(android.R.drawable.ic_menu_gallery),
            contentDescription = null,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun UserAvatar(name: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(48.dp)
            .clip(CircleShape)
            .background(Color.LightGray)
    ) {
        Text(
            text = name.first().toString(),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun Question12() {
    UserAvatar(
        name = "Mukhammadamin",
        modifier = Modifier.padding(8.dp)
    )
}

@Composable
fun Question13() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Loading…",
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        )
    }
}

@Composable
fun Question14() {
    Image(
        painter = painterResource(android.R.drawable.ic_menu_gallery),
        contentDescription = "Product photo",
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16f / 9f),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun Question15() {
    Box(
        modifier = Modifier
            .size(56.dp)
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = CircleShape
            )
            .padding(2.dp)
            .clip(CircleShape)
            .background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "M", color = Color.White)
    }
}

@Composable
fun Question16() {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        repeat(30) {
            Text("Settings Row #$it", modifier = Modifier.padding(16.dp))
        }
    }
}

@Composable
fun Question17() {
    val scrollState = rememberScrollState()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(scrollState),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        listOf("All", "Electronics", "Books", "Clothing", "Sports").forEach { category ->
            Text(category, modifier = Modifier.padding(12.dp))
        }
    }
}

@Composable
fun Question18(onSubmit: () -> Unit) {
    Button(
        onClick = onSubmit,
        modifier = Modifier.testTag("submit_button")
    ) {
        Text("Submit")
    }
}

@Composable
fun Question19() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(width = 1.dp, height = 24.dp)
                .background(Color.Gray)
        )
        Icon(
            imageVector = Icons.Default.Warning,
            contentDescription = "Warning"
        )
    }
}

@Composable
fun Question20(isVisible: Boolean) {
    val animatedAlpha by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        label = "alpha"
    )
    Box(
        modifier = Modifier
            .size(100.dp)
            .graphicsLayer {
                alpha = animatedAlpha
            }
            .background(Color.Blue)
    )
}

@Composable
fun Question21(angle: Float, scaleFactor: Float) {
    Box(
        modifier = Modifier
            .size(64.dp)
            .graphicsLayer {
                rotationZ = angle
                scaleX = scaleFactor
                scaleY = scaleFactor
            }
            .background(Color.Cyan)
    )
}

@Composable
fun Question22(isBeingDragged: Boolean) {
    Box(modifier = Modifier.size(200.dp)) {
        Box(
            modifier = Modifier
                .size(120.dp)
                .zIndex(if (isBeingDragged) 1f else 0f)
                .background(Color.Red)
        )
        Box(
            modifier = Modifier
                .offset(20.dp, 20.dp)
                .size(120.dp)
                .zIndex(0f)
                .background(Color.Green)
        )
    }
}

fun Modifier.dashedBorder(color: Color, strokeWidth: Dp): Modifier = this.drawBehind {
    val stroke = Stroke(
        width = strokeWidth.toPx(),
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    )
    drawRect(color = color, style = stroke)
}

@OptIn(androidx.compose.foundation.ExperimentalFoundationApi::class)
@Composable
fun Question24(onSelect: () -> Unit, onMenu: () -> Unit) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .combinedClickable(
                onClick = onSelect,
                onLongClick = onMenu
            )
            .background(Color.Magenta)
    )
}

@Composable
fun Question25() {
    Box(modifier = Modifier.size(60.dp)) {
        Box(
            modifier = Modifier
                .requiredSize(100.dp)
                .background(Color.Red)
        )
    }
}

@Composable
fun Question26() {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(20) { index ->
            Text("Message #$index", modifier = Modifier.padding(vertical = 8.dp))
        }
    }
}

@Composable
fun Question27() {
    Box {
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(Color.Yellow.copy(alpha = 0.4f), CircleShape)
        )
        Image(
            painter = painterResource(android.R.drawable.ic_menu_gallery),
            contentDescription = "Avatar",
            modifier = Modifier.size(120.dp)
        )
    }
}

@Composable
fun RowScope.MyRow() {
    Text("A", Modifier.weight(1f))
    Text("B", Modifier.weight(1f))
}

@Composable
fun Question29() {
    var query by remember { mutableStateOf("") }
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        TextField(
            value = query,
            onValueChange = { query = it },
            modifier = Modifier.fillMaxWidth(0.8f)
        )
    }
}

@Composable
fun Question30() {
    Box(
        modifier = Modifier
            .size(100.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color.Cyan)
    )
}

@Composable
fun AppTheme(
    darkTheme: Boolean = false,
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> darkColorScheme()
        else -> lightColorScheme()
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}

@Composable
fun Question36(onSave: () -> Unit, onPreview: () -> Unit, onCancel: () -> Unit) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Button(onClick = onSave) {
            Text("Save")
        }
        FilledTonalButton(onClick = onPreview) {
            Text("Preview")
        }
        OutlinedButton(onClick = onCancel) {
            Text("Cancel")
        }
    }
}

@Composable
fun AdaptiveNavigation(
    widthSizeClass: WindowWidthSizeClass,
    content: @Composable () -> Unit
) {
    when (widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            Column(Modifier.fillMaxSize()) {
                Box(Modifier.weight(1f)) { content() }
                NavigationBar {}
            }
        }
        WindowWidthSizeClass.Medium -> {
            Row(Modifier.fillMaxSize()) {
                NavigationRail {}
                Box(Modifier.weight(1f)) { content() }
            }
        }
        WindowWidthSizeClass.Expanded -> {
            PermanentNavigationDrawer(
                drawerContent = {
                    PermanentDrawerSheet {
                        NavigationDrawerItem(
                            label = { Text("Home") },
                            selected = true,
                            onClick = {}
                        )
                    }
                }
            ) {
                content()
            }
        }
    }
}

@Composable
fun Counter(
    count: Int,
    onIncrement: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(text = "$count")
        Button(onClick = onIncrement) {
            Text("Increment")
        }
    }
}

@Composable
fun CounterScreen() {
    var count by remember { mutableIntStateOf(0) }
    Counter(
        count = count,
        onIncrement = { count++ }
    )
}
```[cite: 12]