package com.example.tasky2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
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
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
	Tasky2Theme {
		Greeting("Android")
	}
}
@Composable
fun Greeting(name: String) {
	Surface(
		color = MaterialTheme.colors.primary,
		modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)) {
		Column(modifier = Modifier.padding(24.dp).fillMaxWidth(1f)) {
			Text(text = "Hello, ")
			Text(text = name)
		}
	}
}

@Composable
fun MyApp(names: List<String> = listOf("Dalia", "Dareen", "Lina")) {
	// A surface container using the 'background' color from the theme
		Surface(color = MaterialTheme.colors.background) {
		Column {
			for (name in names) {
				Greeting(name)
			}
		}
	}
}


