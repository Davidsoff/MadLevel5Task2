package nl.soffware.madlevel5task2.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import nl.soffware.madlevel5task2.model.Game

@Dao
interface GameDao {
    @Insert
    suspend fun insertGame(game: Game)

    @Query("Select * FROM game_table ORDER BY release_date ASC")
    fun getGames(): LiveData<List<Game>>

    @Delete
    suspend fun removeGame(game: Game)

    @Query("DELETE FROM game_table")
    suspend fun removeAllGames()
}
