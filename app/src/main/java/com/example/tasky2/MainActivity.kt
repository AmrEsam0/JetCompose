package com.example.tasky2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.example.tasky2.ui.theme.Tasky2Theme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

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
	Surface(modifier.padding(8.dp), color = MaterialTheme.colors.background) {
		SearchBar()
		AlignYourBodyElement(
			drawable = R.drawable.ab1_inversions,
			text = R.string.ab1_inversions,
		)
		FavoriteCollectionCard(
			drawable = R.drawable.fc2_nature_meditations,
			text = R.string.fc2_nature_meditations,
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




@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
	Tasky2Theme {
		MyApp(Modifier.fillMaxSize())
	}
}

