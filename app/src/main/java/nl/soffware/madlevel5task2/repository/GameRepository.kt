package nl.soffware.madlevel5task2.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import nl.soffware.madlevel5task2.dao.GameDao
import nl.soffware.madlevel5task2.database.GameRoomDatabase
import nl.soffware.madlevel5task2.model.Game

class GameRepository(context: Context) {

    private val gameDao: GameDao?

    init {
        val database = GameRoomDatabase.getDatabase(context)
        gameDao = database!!.gameDao()
    }

    fun getAllGames(): LiveData<List<Game>> {
        return gameDao?.getGames() ?: MutableLiveData(emptyList())
    }

    suspend fun addGame(game: Game) {
        gameDao?.insertGame(game)
    }

    suspend fun removeGame(game:Game) {
        gameDao?.removeGame(game)
    }

    suspend fun removeAllGames() {
        gameDao?.removeAllGames()
    }

}
