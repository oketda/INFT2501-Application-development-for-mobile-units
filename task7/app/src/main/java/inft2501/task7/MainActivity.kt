package inft2501.task7

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import inft2501.task7.databinding.MinLayoutBinding
import inft2501.task7.managers.FileManager
import inft2501.task7.managers.MyPreferenceManager
import inft2501.task7.service.Database
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

	private lateinit var db: Database

	companion object {
		lateinit var minLayout: MinLayoutBinding
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		minLayout = MinLayoutBinding.inflate(layoutInflater)
		setContentView(minLayout.root)

		db = Database(this)
		db.clear()

		MyPreferenceManager(this).updateBackgroundColor()

		val moviesList: MutableList<MutableList<String>> = FileManager(this).readMoviesFromFile(R.raw.filmer)
		insertFromFileToDb(moviesList)
		writeToFile(moviesList)
	}

	private fun insertFromFileToDb(moviesList: MutableList<MutableList<String>>) {

		var movieTitle = ""
		var directorName = ""
		var actorNames: MutableList<String> = ArrayList()

		for (movie in moviesList) {
			for ((i, element) in movie.withIndex()) {
				when (i) {
					0 -> {
						movieTitle = element
					}
					1 -> {
						directorName = element
					}
					else -> {
						actorNames.add(element)
					}
				}
			}
			db.insert(movieTitle, directorName, actorNames)
			actorNames.clear()
		}
	}

	private fun writeToFile(moviesList: MutableList<MutableList<String>>) {
		FileManager(this).write(moviesList)
	}

	private fun showResults(list: ArrayList<String>) {
		val res = StringBuffer("")
		for (s in list) res.append("$s\n")
		minLayout.result.text = res
	}

	override fun onCreateOptionsMenu(menu: Menu): Boolean {
		menuInflater.inflate(R.menu.settings, menu)
		menu.add(0, 1, 0, "Alle filmer")
		menu.add(0, 2, 0, "Alle Regissører")
		menu.add(0, 3, 0, "Alle Skuespillere")
		menu.add(0, 4, 0, "Alle filmer av James Cameron")
		menu.add(0, 5, 0, "Skuespillere i Titanic")
		return super.onCreateOptionsMenu(menu)
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		when (item.itemId) {
			R.id.settings -> startActivity(Intent("inft2501.task7.SettingsActivity"))
			1             -> showResults(db.allMovies)
			2             -> showResults(db.allDirectors)
			3             -> showResults(db.allActors)
			4             -> showResults(db.getMoviesByDirector("James Cameron"))
			5             -> showResults(db.getActorsByMovie("Titanic"))
			else          -> return false
		}
		return super.onOptionsItemSelected(item)
	}
}
