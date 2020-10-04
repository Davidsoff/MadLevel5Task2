package nl.soffware.madlevel5task2.ui.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_game_card.view.*
import nl.soffware.madlevel5task2.R
import nl.soffware.madlevel5task2.model.Game
import java.text.SimpleDateFormat
import java.util.*

class GamesAdapter(private val games: List<Game>) : RecyclerView.Adapter<GamesAdapter.ViewHolder>()  {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val dateFormatter = SimpleDateFormat(itemView.context.getString(R.string.date_format), Locale.ENGLISH)
        fun databind(game: Game) {
            itemView.tv_platform.text = game.platform.toString()
            itemView.tv_title.text = game.title
            itemView.tv_release.text = dateFormatter.format(game.releaseDate)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_game_card, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return games.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(games[position])
    }
}