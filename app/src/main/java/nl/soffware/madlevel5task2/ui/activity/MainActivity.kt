package nl.soffware.madlevel5task2.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.activity_main.*
import nl.soffware.madlevel5task2.R
import nl.soffware.madlevel5task2.R.id.AddGameFragment
import nl.soffware.madlevel5task2.ui.viewmodel.GameViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))

        navController = findNavController(R.id.nav_host_fragment)
        backButtonToggler()
        actionBarTitleUpdater()
    }

    private fun actionBarTitleUpdater() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            actionBar?.title = when(destination.id){
                in arrayOf(AddGameFragment) -> getString(R.string.add_game_title)
                else -> getString(R.string.app_name)
            }

        }
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            menu!!.findItem(R.id.action_delete_all).isVisible = destination.id !in arrayOf(AddGameFragment)
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val gameViewModel : GameViewModel by viewModels()
        return when (item.itemId) {
            R.id.action_delete_all -> {
                gameViewModel.deleteAll()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun backButtonToggler(){
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id in arrayOf(AddGameFragment)) {
                supportActionBar!!.setDisplayHomeAsUpEnabled(true);
                supportActionBar!!.setDisplayShowHomeEnabled(true);
            } else {
                supportActionBar!!.setDisplayHomeAsUpEnabled(false);
                supportActionBar!!.setDisplayShowHomeEnabled(false);
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}