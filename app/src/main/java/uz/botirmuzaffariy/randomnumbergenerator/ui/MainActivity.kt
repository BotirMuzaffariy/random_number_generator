package uz.botirmuzaffariy.randomnumbergenerator.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import uz.botirmuzaffariy.randomnumbergenerator.ui.screens.MainScreen
import uz.botirmuzaffariy.randomnumbergenerator.ui.theme.RandomNumberGeneratorTheme
import uz.botirmuzaffariy.randomnumbergenerator.utils.sharedPrefs

class MainActivity : ComponentActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        sharedPrefs = getSharedPreferences("random_number_generator", MODE_PRIVATE)

        setContent {
            RandomNumberGeneratorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun MainPreview() {
    RandomNumberGeneratorTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            MainScreen(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}