package inft2501.task7.managers

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.io.*

/**
 * Just contains basic code snippets relevant for reading from/to different files
 */
class FileManager(private val activity: AppCompatActivity) {

	private val filename: String = "filmer.txt"
	private val newFileName: String = "skrevet_til_fil.txt"

	private var dir: File = activity.filesDir
	private var file: File = File(dir, filename)
	private var newFile: File = File(dir, newFileName)

	private var externalDir: File? = activity.getExternalFilesDir(null)
	private var externalFile = File(externalDir, newFileName)


	fun write(moviesList: MutableList<MutableList<String>>) {
		var result = ""

		for (movie in moviesList) {
			var iterator: Iterator<String> = movie.iterator()
			var i = 0
			while (iterator.hasNext()) {
				if (i == 0) {
					result += "Tittel: ${iterator.next()}; "
				} else if (i == 1) {
					result += "Regissør: ${iterator.next()}; "
				} else if (i == 2) {
					result += "Skuespillere: ${iterator.next()}, "
				} else {
					result += "${iterator.next()}"
					result += if (iterator.hasNext()) {
						", "
					} else {
						";"
					}
				}
				i++
			}
			result += "\n"
		}

		PrintWriter(externalFile).use { writer ->
			writer.println(result)
			writer.flush()
			writer.close()
		}

	}

	fun readLine(): String? {
		BufferedReader(FileReader(file)).use { reader ->
			return reader.readLine()
		}
	}

	/**
	 * Open file: *res/raw/id.txt*
	 *
	 * @param fileId R.raw.filename
	 */
	fun readMoviesFromFile(fileId: Int): MutableList<MutableList<String>> {
		val moviesList: MutableList<MutableList<String>> = ArrayList()
		try {
			val inputStream: InputStream = activity.resources.openRawResource(fileId)
			val reader = BufferedReader(InputStreamReader(inputStream)).use { reader ->
				var line = reader.readLine()
				while (line != null) {
					moviesList.add(reformatMovies(line))
					line = reader.readLine()
				}

			}
		} catch (e: IOException) {
			e.printStackTrace()
		}
		return moviesList
	}

	fun reformatMovies(movies: String): MutableList<String> {
		val result: MutableList<String> = ArrayList()
		result.add(movies.substringAfter("Tittel: ").substringBefore(";"))
		result.add(movies.substringAfter("Regissør: ").substringBefore(";"))
		var actors = movies.substringAfter("Skuespillere: ").substringBefore(";").split(",")
		for (actor in actors) {
			result.add(actor.trim())
		}

		return result
	}
}
