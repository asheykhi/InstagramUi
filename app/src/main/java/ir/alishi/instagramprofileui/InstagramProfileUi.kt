package ir.alishi.instagramprofileui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

val defaultBrush = Brush.linearGradient(
    colors = listOf(Color.LightGray, Color.LightGray)
)

val storyBrush = Brush.linearGradient(
    colors = listOf(
        Color.Yellow,
        Color(0xFFFF5722),
        Color(0xFFFF0066),
        Color.Red,
    ),
    tileMode = TileMode.Repeated
)

@Composable
fun TopBar() {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            text = "Username",
            style = MaterialTheme.typography.body2.copy(
                fontSize = 20.sp
            ),
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = null,
        )
    }
}

@Composable
fun ProfImage(
    index: Int = 0,
    size: Dp = 80.dp,
    vector: ImageVector = Icons.Default.AccountCircle,
    painter: Painter? = null
) {
    if (painter != null) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .size(size)
                .clip(CircleShape)
                .border(
                    width = if (index % 2 == 0) 2.dp else 1.dp,
                    brush = if (index % 2 == 0) storyBrush else defaultBrush,
                    shape = CircleShape
                ),
        )
    } else {
        Image(
            imageVector = vector,
            contentDescription = null,
            modifier = Modifier
                .size(size)
                .clip(CircleShape)
                .border(
                    width = if (index % 2 == 0) 2.dp else 1.dp,
                    brush = if (index % 2 == 0) storyBrush else defaultBrush,
                    shape = CircleShape
                ),

            colorFilter = ColorFilter.tint(
                color = Color.Gray
            )
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun InstagramProfile() {

    var showSuggest by remember {
        mutableStateOf(false)
    }
    var showProgress by remember {
        mutableStateOf(false)
    }

    val coroutineScope = rememberCoroutineScope()

    Column(
        Modifier.verticalScroll(rememberScrollState())
    ) {
        TopBar()
        PictureRowScope()
        ProfileBio()
        ButtonRowScope(
            onSuggestClicked = { value ->
                coroutineScope.launch {
                    showProgress = true
                    if (!showSuggest) {
                        delay(2000)
                    }
                    showSuggest = value
                    showProgress = false
                }
            },
            showSuggest = !showSuggest,
            progress = {
                if (showProgress) {
                    CircularProgressIndicator(
                        color = Color.Black,
                        modifier = Modifier
                            .size(24.dp),
                        strokeWidth = 2.dp
                    )
                }
            }
        )
        if (showSuggest) {
            SuggestedRowScope()
            SuggestListRow()
        }
        HighLightRow()
    }
}

@Composable
fun PictureRowScope() {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .padding(horizontal = 10.dp)
    ) {
        ProfImage()
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "758",
                    style = MaterialTheme.typography.subtitle2
                )
                Text(
                    text = "Posts",
                    style = MaterialTheme.typography.caption.copy(
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp
                    )
                )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "108K",
                    style = MaterialTheme.typography.subtitle2
                )
                Text(
                    text = "Followers",
                    style = MaterialTheme.typography.caption.copy(
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp
                    )
                )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "109",
                    style = MaterialTheme.typography.subtitle2
                )
                Text(
                    text = "Followings",
                    style = MaterialTheme.typography.caption.copy(
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp
                    )
                )
            }
        }
    }
}

@Composable
fun ProfileBio() {
    var expanded by remember {
        mutableStateOf(false)
    }

    var visibleTxt by remember {
        mutableStateOf("some introduction...")
    }

    val more = "#hashtag\n" +
            "some other description\n" +
            "✅ verified account"

    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
    ) {
        Text(
            text = "Full Name",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Black,
            ),
            modifier = Modifier,
        )

        Row(modifier = Modifier) {
            Text(text = visibleTxt)
            if (!expanded) {
                Text(
                    text = "more",
                    color = Color.Gray,
                    modifier = Modifier
                        .clickable {
                            expanded = !expanded
                        },
                )
            }
        }

        if (expanded) {
            visibleTxt = "some introduction"
            Text(text = more)
        }


    }
}

@Composable
fun ButtonRowScope(
    showSuggest: Boolean,
    onSuggestClicked: (Boolean) -> Unit,
    progress: @Composable () -> Unit,
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Surface(
            shape = RoundedCornerShape(10.dp),
            color = colorResource(id = R.color.blue_btn),
            modifier = Modifier
                .weight(0.6f)

        ) {
            Text(
                text = "Follow back",
                style = TextStyle(
                    color = Color.White,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .padding(8.dp)

            )
        }

        Surface(
            shape = RoundedCornerShape(10.dp),
            color = Color.LightGray,
            modifier = Modifier.weight(0.6f)
        ) {
            Text(
                text = "Message",
                style = TextStyle(
                    color = Color.Black,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.padding(8.dp)

            )
        }

        Surface(
            shape = RoundedCornerShape(10.dp),
            color = Color.LightGray,
            modifier = Modifier
                .weight(0.2f)
                .clickable {
                    onSuggestClicked(showSuggest)
                }
        ) {
            Box(contentAlignment = Alignment.Center) {

                Icon(
                    imageVector = if (!showSuggest) {
                        Icons.Default.CheckCircle
                    } else {
                        Icons.Default.AddCircle
                    },
                    contentDescription = null,
                    modifier = Modifier.padding(5.dp),
                    tint = Color.Black
                )

                progress()


            }
        }


    }
}

@Composable
fun SuggestedRowScope() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .padding(bottom = 5.dp)

    ) {
        val style = TextStyle(
            fontSize = 15.sp
        )
        Text(
            text = "Suggested for you",
            style = style.copy(
                fontWeight = FontWeight.Medium,
                fontSize = 13.sp,
                color = Color.Black.copy(
                    alpha = 0.8f
                )
            )
        )
        Text(
            text = "see all",
            color = Color.Blue.copy(
                alpha = 0.8f
            ),
            style = style
        )
    }
}

@Composable
fun SuggestRowItem(
    username: String,
    index: Int = 0
) {
    Surface(
        shape = MaterialTheme.shapes.small,
        elevation = 3.dp,
        modifier = Modifier
            .size(width = 150.dp, height = 200.dp)
            .padding(horizontal = 5.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            ProfImage(index = index)
            Text(text = "username")
            Text(
                text = username,
                color = Color.Gray,
                fontSize = 12.sp
            )
            Spacer(modifier = Modifier.height(15.dp))
            Surface(
                shape = RoundedCornerShape(5.dp),
                color = colorResource(id = R.color.blue_btn),
                modifier = Modifier
                    .padding(bottom = 10.dp)
            ) {
                Text(
                    text = "Follow",
                    style = TextStyle(
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        fontSize = 14.sp
                    ),
                    modifier = Modifier
                        .padding(horizontal = 22.dp, vertical = 3.dp)
                )
            }
        }
    }
}

@Composable
fun SuggestListRow() {
    val items = listOf("Tomi", "Petter", "John")
    LazyRow {
        itemsIndexed(items) { index, suggestItem ->
            SuggestRowItem(
                username = suggestItem, index
            )
        }
    }
}

@Composable
fun HighlightItem(name: String, painter: Painter) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(horizontal = 5.dp)
    ) {
        ProfImage(
            size = 60.dp,
            index = 1,
            painter = painter
        )
        Text(text = name)
    }
}

@Composable
fun HighLightRow() {
    val highlights = listOf("selected", "birthday","waterfall")
    val painters = listOf(
        R.drawable.waterfall_nature,
        R.drawable.waterfalls,
        R.drawable.waterfalls_svgrepo
    )

    LazyRow(
        modifier = Modifier
            .padding(top = 20.dp, bottom = 20.dp)
    ) {
        itemsIndexed(highlights) { index, _ ->
            HighlightItem(
                highlights[index],
                painterResource(painters[index])
            )
        }
    }
}