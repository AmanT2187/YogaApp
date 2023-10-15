@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.akeera.yogaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.wear.compose.material.Colors
import com.akeera.yogaapp.ui.theme.YogaAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YogaAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MySootheAppPortrait()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(modifier: Modifier = Modifier){
    TextField(value = "", onValueChange = {},
        leadingIcon = {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = null
        )
    },
        colors = TextFieldDefaults.textFieldColors(
            unfocusedIndicatorColor = MaterialTheme.colorScheme.surface,
            focusedIndicatorColor = MaterialTheme.colorScheme.surface
        ),
        placeholder = {
            Text(stringResource(R.string.placeholder_search))
        },
    modifier = modifier
        .fillMaxWidth()
        .heightIn(min = 56.dp))

}

@Composable
fun AlignBodyElement( @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier)
{
    Column (modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally){
        Image(painter = painterResource(id = drawable), contentDescription = null,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
                , contentScale = ContentScale.Crop)
        Text(text = stringResource(R.string.ex1_pushUps),
            modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp),
            style = MaterialTheme.typography.bodyMedium)
    }

}

@Composable
fun FavBarCollectionCard(@DrawableRes drawable: Int,
                         @StringRes text: Int,
                         modifier: Modifier = Modifier){
        Surface(shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.surfaceVariant,
            modifier = modifier) {
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.width(255.dp)) {
                Image(painter = painterResource(id = R.drawable.latpulldown) , contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(80.dp))
                Text(text = stringResource(id =  R.string.ex2),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(horizontal = 16.dp))
            }

        }
}


@Composable
fun AlignYourBodyRow(
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(alignYourBodyData) { item ->
            AlignBodyElement(item.drawable, item.text)
        }
    }
}

@Composable
fun FavoriteCollectionsGrid(
    modifier: Modifier = Modifier
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.height(168.dp)
    ) {
        items(favoriteCollectionsData) { item ->
            FavBarCollectionCard(item.drawable, item.text, Modifier.height(80.dp))
        }
    }
}

@Composable
fun HomeSection(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        Text(
            text = stringResource(title),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(Modifier.height(16.dp))
        SearchBar(Modifier.padding(horizontal = 16.dp))
        HomeSection(title = R.string.align_your_body) {
            AlignYourBodyRow()
        }
        HomeSection(title = R.string.favorite_collections) {
            FavoriteCollectionsGrid()
        }
        Spacer(Modifier.height(16.dp))
    }
}

@Composable
private fun SootheBottomNavigation(modifier: Modifier = Modifier) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null
                )
            },
            label = {
                Text(stringResource(R.string.bottom_navigation_home))
            },
            selected = true,
            onClick = {}
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            },
            label = {
                Text(stringResource(R.string.bottom_navigation_profile))
            },
            selected = false,
            onClick = {}
        )
    }
}

@Composable
fun MySootheAppPortrait() {
    YogaAppTheme {
        Scaffold(
            bottomBar = { SootheBottomNavigation() }
        ) { padding ->
            HomeScreen(Modifier.padding(padding))
        }
    }
}


private val alignYourBodyData = listOf(
    R.drawable.pushup to R.string.ex1_pushUps,
    R.drawable.latpulldown to R.string.ex1_pushUps,
    R.drawable.ex3 to R.string.ex1_pushUps,
    R.drawable.ex4 to R.string.ex1_pushUps,
    R.drawable.ex5 to R.string.ex1_pushUps,
    R.drawable.ex6 to R.string.ex1_pushUps
).map { DrawableStringPair(it.first, it.second) }

private val favoriteCollectionsData = listOf(
    R.drawable.pushup to R.string.ex1_pushUps,
    R.drawable.latpulldown to R.string.ex1_pushUps,
    R.drawable.ex3 to R.string.ex1_pushUps,
    R.drawable.ex4 to R.string.ex1_pushUps,
    R.drawable.ex5 to R.string.ex1_pushUps,
    R.drawable.ex6 to R.string.ex1_pushUps
).map { DrawableStringPair(it.first, it.second) }

private data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    YogaAppTheme {
        SearchBar()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun FavBar() {
    YogaAppTheme {
        FavBarCollectionCard(R.drawable.ex4, R.string.ex1_pushUps, modifier = Modifier.padding(12.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ALignBody(){
    YogaAppTheme {
        AlignBodyElement(drawable = R.drawable.pushup,
            text = R.string.ex1_pushUps,
            modifier = Modifier.padding(8.dp))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun HomeSectionPreview() {
    YogaAppTheme {
        HomeSection(R.string.align_your_body) {
            AlignYourBodyRow()
        }
    }
}
