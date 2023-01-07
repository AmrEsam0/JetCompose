package com.example.tasky2

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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

	Surface(modifier, color = MaterialTheme.colors.background) {
		SearchBar()
	}
}



@Composable
fun SearchBar(
	modifier: Modifier = Modifier
){
	TextField(
		value = "",
		onValueChange = {},
		label = { Text("Search") },
		modifier = modifier
			.fillMaxWidth()
			.padding(8.dp)
	)
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
	Tasky2Theme {
		MyApp(Modifier.fillMaxSize())
	}
}

