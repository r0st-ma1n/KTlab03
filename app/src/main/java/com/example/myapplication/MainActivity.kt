
package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                var clickCount by rememberSaveable { mutableIntStateOf(0) } // Use rememberSaveable
                val context = LocalContext.current
                val lifecycleOwner = LocalLifecycleOwner.current
                val buttonColor = Color(0xFFB0E2FF) // Light Blue Color

                Box(modifier = Modifier.fillMaxSize()) {
                    // Background Image
                    Image(
                        painter = painterResource(id = R.drawable.gradient),
                        contentDescription = "Фоновое изображение",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier.matchParentSize()
                    )

                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        containerColor = Color.Transparent
                    ) { innerPadding ->
                        Column(
                            modifier = Modifier
                                .padding(innerPadding)
                                .fillMaxSize()
                                .padding(top = 50.dp), // Push content down from the top
                            verticalArrangement = Arrangement.Top, // Align items to the top
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Greeting(name = "Android", color = Color.White)

                            Spacer(modifier = Modifier.height(20.dp)) // Add space between text and button

                            Button(
                                onClick = {
                                    clickCount += 2

                                    lifecycleOwner.lifecycleScope.launch {
                                        Toast.makeText(
                                            context,
                                            "Количество кликов: $clickCount",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                },
                                modifier = Modifier
                                    .width(200.dp) // Increased width
                                    .height(60.dp), // Increased height
                                colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                                shape = RoundedCornerShape(8.dp) // Optional: Round button corners
                            ) {
                                Text(
                                    "Показать Toast и посчитать",
                                    color = Color.White, // White text color
                                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, color: Color = Color.Black) {
    Text(
        text = "Привет $name!",
        modifier = modifier,
        color = color,
        style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold) // Increased font size and bold
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android", color = Color.White)
    }
}
