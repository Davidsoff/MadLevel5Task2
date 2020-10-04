package nl.soffware.madlevel5task2.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import nl.soffware.madlevel5task2.R
import nl.soffware.madlevel5task2.model.Game
import nl.soffware.madlevel5task2.model.Platform
import nl.soffware.madlevel5task2.repository.GameRepository
import java.util.*

class GameViewModel(application: Application) : AndroidViewModel(application) {
    private val gameRepository = GameRepository(application.applicationContext)
    private val mainScope = CoroutineScope(Dispatchers.Main)
    private val context = application.applicationContext

    val games: LiveData<List<Game>> = gameRepository.getAllGames()
    val error = MutableLiveData<String>()
    val success = MutableLiveData<Boolean>()

    fun addGame(title: String, platform: Platform, releaseDate: Date){
        val newGame = Game(title, platform, releaseDate)
        if(isGameValid(newGame)) {
            mainScope.launch {
                withContext(Dispatchers.IO) {
                    gameRepository.addGame(newGame)
                }
                success.value = true
            }
        }
    }

    private fun isGameValid(game: Game): Boolean {
        return when {
            game.title.isBlank() -> {
                error.value = context.getString(R.string.title_empty_error)
                false
            }
            else -> true
        }
    }

    fun deleteAll() {
        mainScope.launch {
            withContext(Dispatchers.IO){
                gameRepository.removeAllGames()
            }
        }
    }

    fun deleteGame(gameToDelete: Game) {
        mainScope.launch {
            withContext(Dispatchers.IO){
                gameRepository.removeGame(gameToDelete)
            }
        }
    }
}
