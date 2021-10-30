package inft2501.task7.service

import android.content.Context
import inft2501.task7.managers.DatabaseManager

class Database(context: Context) : DatabaseManager(context) {

	val allMovies: ArrayList<String>
		get() = performQuery(TABLE_MOVIE, arrayOf(MOVIE_TITLE))

	val allDirectors: ArrayList<String>
		get() = performQuery(TABLE_DIRECTOR, arrayOf(DIRECTOR_NAME), null)

	val allActors: ArrayList<String>
		get() = performQuery(TABLE_ACTOR, arrayOf(ACTOR_NAME), null)


	fun getMoviesByDirector(director: String): ArrayList<String> {
		var directorIdList: ArrayList<String> = getDirectorById(director)
		var directorId = directorIdList[0]

		val select = arrayOf("$TABLE_MOVIE.$MOVIE_TITLE")
		val from = arrayOf(TABLE_MOVIE)
		val where = "$TABLE_MOVIE.$DIRECTOR_ID='$directorId'"

		return performRawQueryWithoutJoin(select, from, where)
	}

	fun getDirectorById(director: String): ArrayList<String> {
		val select = arrayOf("$TABLE_DIRECTOR.$ID")
		val from = arrayOf(TABLE_DIRECTOR)
		val where = "$TABLE_DIRECTOR.$DIRECTOR_NAME='$director'"

		return performRawQueryWithoutJoin(select, from, where)
	}

	fun getActorsByMovie(movie: String): ArrayList<String> {
		val select = arrayOf("$TABLE_ACTOR.$ACTOR_NAME")
		val from = arrayOf(TABLE_MOVIE, TABLE_ACTOR, TABLE_MOVIE_ACTOR)
		val join = JOIN_MOVIE_ACTOR
		val where = "$TABLE_MOVIE.$MOVIE_TITLE='$movie'"

		return performRawQuery(select, from, join, where)
	}
}
