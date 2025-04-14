package uz.botirmuzaffariy.randomnumbergenerator.utils

import android.content.SharedPreferences

lateinit var sharedPrefs: SharedPreferences

const val defaultMin = 0
const val defaultMax = 100

fun getMinFromPrefs(): Int = sharedPrefs.getInt("min", defaultMin)

fun setMinToPrefs(value: Int) {
    sharedPrefs.edit().putInt("min", value).apply()
}

fun getMaxFromPrefs(): Int = sharedPrefs.getInt("max", defaultMax)

fun setMaxToPrefs(value: Int) {
    sharedPrefs.edit().putInt("max", value).apply()
}

fun getNumberFromPrefs(): String = sharedPrefs.getString("number", "") ?: ""

fun setNumberToPrefs(value: String) {
    sharedPrefs.edit().putString("number", value).apply()
}