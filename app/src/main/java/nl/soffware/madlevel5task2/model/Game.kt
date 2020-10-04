package nl.soffware.madlevel5task2.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "game_table")
class Game (
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "platform")
    var platform: Platform,
    @ColumnInfo(name = "release_date")
    var releaseDate: Date,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
){
}
