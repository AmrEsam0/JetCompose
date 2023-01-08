package com.example.tasky2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tasky2.ui.theme.Tasky2Theme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			Tasky2Theme {
				MyApp()
			}
		}
	}
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {
	Scaffold(
		bottomBar = { MyButtonNavigation()},
		modifier = modifier.fillMaxSize(),
		backgroundColor = Color(0xFFF0EAE2)
	// padding here is to move screen content away from the bottomNavBar
	// when needed like in rotated mode (specially for smaller screens)
	) { padding ->
			HomeScreen(Modifier.padding(padding))
	}
}


@Composable
fun HomeSection(
	@StringRes title: Int,
	modifier: Modifier = Modifier,
	content: @Composable () -> Unit,
) {
	Column(modifier = modifier) {
		Text(
			text = stringResource(id = title).uppercase(java.util.Locale.getDefault()),
			style = MaterialTheme.typography.h6,
			modifier = Modifier
				.paddingFromBaseline(top = 40.dp, bottom = 8.dp)
				.padding(horizontal = 8.dp),
		)
		content()
	}

}


@Composable
fun HomeScreen(
	modifier: Modifier = Modifier,
) {
	Column(modifier.verticalScroll(rememberScrollState())) {
		Spacer(Modifier.height(16.dp))
		SearchBar(Modifier.padding(horizontal = 16.dp))
		HomeSection(title = R.string.align_your_body, content = {
			AlignYourBodyRow()
		})
		HomeSection(title = R.string.favorite_collections, content = {
			FavoriteCollectionGrid()
		})
		Spacer(Modifier.height(16.dp))
	}
}

@Composable
fun MyButtonNavigation(
	modifier: Modifier = Modifier,
) {
	BottomNavigation(
		backgroundColor = Color(0xFFECE9E6),
		modifier = modifier
	) {

		BottomNavigationItem(
			selected = true, onClick = { /*TODO*/ },
			icon = {
				Icon(Icons.Default.Home, contentDescription = null)
			},
			label = { Text(text = "Home") }
		)
		BottomNavigationItem(
			selected = false, onClick = { /*TODO*/ },
			icon = {
				Icon(Icons.Default.Person, contentDescription = null)
			},
			label = { Text(text = "Profile") }
		)
		BottomNavigationItem(
			selected = false, onClick = { /*TODO*/ },
			icon = {
				Icon(Icons.Default.Settings, contentDescription = null)
			},
			label = { Text(text = "Settings") }
		)
	}
}


@Composable
fun SearchBar(
	modifier: Modifier = Modifier
){
	TextField(
		value = "",
		onValueChange = {},
		placeholder = { Text("Search") },
		leadingIcon = {Icon(Icons.Default.Search, contentDescription = "Search")},
		colors = TextFieldDefaults.textFieldColors(
			backgroundColor = MaterialTheme.colors.surface,
		),
		modifier = modifier
			.fillMaxWidth()
			.heightIn(min = 56.dp)
		)
}

@Composable
fun AlignYourBodyElement(
	@DrawableRes drawable : Int,
	@StringRes text : Int,
	modifier: Modifier = Modifier
) {
	Column(
		horizontalAlignment = Alignment.CenterHorizontally,
		modifier = modifier
	) {
		Image(
			painterResource(id = drawable),
			contentDescription = null,
			contentScale = ContentScale.Crop,
			modifier = Modifier
				.size(88.dp)
				.clip(CircleShape)
		)
		Text(
			stringResource(id = text),
			style = MaterialTheme.typography.subtitle1,
			modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp)
		)
		
	}
}



@Composable
fun FavoriteCollectionCard(
	@DrawableRes drawable : Int,
	@StringRes text : Int,
	modifier: Modifier = Modifier
) {
	Surface(
		shape = MaterialTheme.shapes.small,
		modifier = modifier
	) {
		Row(
			verticalAlignment = Alignment.CenterVertically,
			modifier = Modifier.width(192.dp)
		) {
			Image(
				painterResource(id = drawable),
				contentDescription = null,
				contentScale = ContentScale.Crop,
				modifier = Modifier
					.size(56.dp)
			)
			Text(
				stringResource(id = text),
				style = MaterialTheme.typography.subtitle1,
				modifier = Modifier.padding(horizontal = 16.dp)
			)
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
	){
		items(alignYourBodyData){item ->
			AlignYourBodyElement(item.drawable, item.text)
		}
	}
}


@Composable
fun FavoriteCollectionGrid(
	modifier: Modifier = Modifier
) {
	LazyHorizontalGrid(
		rows = GridCells.Fixed(2),
		contentPadding = PaddingValues(horizontal = 16.dp),
		horizontalArrangement = Arrangement.spacedBy(8.dp),
		verticalArrangement = Arrangement.spacedBy(8.dp),
		modifier = modifier
			.height(120.dp)
	) {
		items(favoriteCollectionsData){item ->
			FavoriteCollectionCard(item.drawable, item.text)
		}
	}

}



private val alignYourBodyData = listOf(
	R.drawable.ab1_inversions to R.string.ab1_inversions,
	R.drawable.ab2_quick_yoga to R.string.ab2_quick_yoga,
	R.drawable.ab3_stretching to R.string.ab3_stretching,
	R.drawable.ab4_tabata to R.string.ab4_tabata,
	R.drawable.ab5_hiit to R.string.ab5_hiit,
	R.drawable.ab6_pre_natal_yoga to R.string.ab6_pre_natal_yoga
).map { DrawableStringPair(it.first, it.second) }

private val favoriteCollectionsData = listOf(
	R.drawable.fc1_short_mantras to R.string.fc1_short_mantras,
	R.drawable.fc2_nature_meditations to R.string.fc2_nature_meditations,
	R.drawable.fc3_stress_and_anxiety to R.string.fc3_stress_and_anxiety,
	R.drawable.fc4_self_massage to R.string.fc4_self_massage,
	R.drawable.fc5_overwhelmed to R.string.fc5_overwhelmed,
	R.drawable.fc6_nightly_wind_down to R.string.fc6_nightly_wind_down
).map { DrawableStringPair(it.first, it.second) }

private data class DrawableStringPair(
	@DrawableRes val drawable: Int,
	@StringRes val text: Int
)


@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
	Tasky2Theme {
		MyApp(Modifier.fillMaxSize())
	}
}

