package uz.botirmuzaffariy.randomnumbergenerator.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.botirmuzaffariy.randomnumbergenerator.utils.defaultMax
import uz.botirmuzaffariy.randomnumbergenerator.utils.defaultMin
import uz.botirmuzaffariy.randomnumbergenerator.utils.getMaxFromPrefs
import uz.botirmuzaffariy.randomnumbergenerator.utils.getMinFromPrefs
import uz.botirmuzaffariy.randomnumbergenerator.utils.getNumberFromPrefs
import uz.botirmuzaffariy.randomnumbergenerator.utils.setMaxToPrefs
import uz.botirmuzaffariy.randomnumbergenerator.utils.setMinToPrefs
import uz.botirmuzaffariy.randomnumbergenerator.utils.setNumberToPrefs
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var min by remember { mutableIntStateOf(getMinFromPrefs()) }
    var max by remember { mutableIntStateOf(getMaxFromPrefs()) }

    var number by remember { mutableStateOf(getNumberFromPrefs()) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(0.5f),
                value = TextFieldValue(min.toString(), selection = TextRange(min.toString().length)),
                onValueChange = {
                    min = it.text.toIntOrNull() ?: defaultMin
                    setMinToPrefs(min)
                },
                label = {
                    Text("Min")
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true
            )

            Spacer(Modifier.width(12.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = TextFieldValue(max.toString(), selection = TextRange(max.toString().length)),
                onValueChange = {
                    max = it.text.toIntOrNull() ?: defaultMax
                    setMaxToPrefs(max)
                },
                label = {
                    Text("Max")
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f)
                .wrapContentHeight(align = Alignment.CenterVertically),
            text = number,
            textAlign = TextAlign.Center,
            fontSize = 48.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(0.5f),
                onClick = {
                    number = getProperRandomNumber(min, max).toString()
                    setNumberToPrefs(number)
                }
            ) {
                Text("Generate in\nLatin", textAlign = TextAlign.Center)
            }

            Spacer(modifier = Modifier.width(6.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    number = toArabicDigits(getProperRandomNumber(min, max).toString())
                    setNumberToPrefs(number)
                }
            ) {
                Text("Generate in\nArabic", textAlign = TextAlign.Center)
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(0.5f),
                onClick = {
                    number = toArabicDigits(number)
                    setNumberToPrefs(number)
                }
            ) {
                Text("Parse to\nArabic", textAlign = TextAlign.Center)
            }

            Spacer(modifier = Modifier.width(6.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    number = toLatinDigits(number)
                    setNumberToPrefs(number)
                }
            ) {
                Text("Parse to\nLatin", textAlign = TextAlign.Center)
            }
        }
    }
}

private fun getProperRandomNumber(min: Int, max: Int): Int {
    return Random.nextInt(min(min, max), max(min, max) + 1)
}

private fun toArabicDigits(number: String): String {
    val arabicNumerals = mapOf(
        '0' to '٠', '1' to '١', '2' to '٢', '3' to '٣', '4' to '٤',
        '5' to '٥', '6' to '٦', '7' to '٧', '8' to '٨', '9' to '٩'
    )

    return number.map { arabicNumerals[it] ?: it }.joinToString("")
}

private fun toLatinDigits(number: String): String {
    val latinNumerals = mapOf(
        '٠' to '0', '١' to '1', '٢' to '2', '٣' to '3', '٤' to '4',
        '٥' to '5', '٦' to '6', '٧' to '7', '٨' to '8', '٩' to '9'
    )

    return number.map { latinNumerals[it] ?: it }.joinToString("")
}

@Preview(showSystemUi = true)
@Composable
private fun MainScreenPreview() {
    MainScreen(modifier = Modifier)
}