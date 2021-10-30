package inft2501.task7.managers

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import inft2501.task7.MainActivity
import inft2501.task7.R

class MyPreferenceManager(private val activity: AppCompatActivity) {

	private val resources = activity.resources
	private val preferences = PreferenceManager.getDefaultSharedPreferences(activity)
	private val editor: SharedPreferences.Editor = preferences.edit()

	fun example() {
		val preferences: SharedPreferences = activity.getSharedPreferences("prefs", 0)
		val editor: SharedPreferences.Editor = preferences.edit()
		editor.putString("playerName", "GameMaster3000")
		editor.putBoolean("darkMode", true)
		editor.putString("backgroundColor", "#ffffff")
		editor.apply()
	}

	fun putString(key: String, value: String) {
		editor.putString(key, value)
		editor.apply()
	}

	fun getString(key: String, defaultValue: String): String {
		return preferences.getString(key, defaultValue) ?: defaultValue
	}

	fun updateBackgroundColor() {
		val darkModeValues = resources.getStringArray(R.array.background_color_values)
		val value = getString(
				resources.getString(R.string.background_color),
				resources.getString(R.string.background_color_default_value)
		)
		when (value) {
			darkModeValues[0] -> MainActivity.minLayout.result.setBackgroundResource(R.color.white)
			darkModeValues[1] -> MainActivity.minLayout.result.setBackgroundResource(R.color.blue)
			darkModeValues[2] -> MainActivity.minLayout.result.setBackgroundResource(R.color.yellow)
			darkModeValues[3] -> MainActivity.minLayout.result.setBackgroundResource(R.color.red)
		}
	}

	fun registerListener(activity: SharedPreferences.OnSharedPreferenceChangeListener) {
		preferences.registerOnSharedPreferenceChangeListener(activity)
	}

	fun unregisterListener(activity: SharedPreferences.OnSharedPreferenceChangeListener) {
		preferences.unregisterOnSharedPreferenceChangeListener(activity)
	}
}
