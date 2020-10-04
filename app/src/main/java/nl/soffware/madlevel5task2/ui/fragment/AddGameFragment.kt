package nl.soffware.madlevel5task2.ui.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_add_game.*
import nl.soffware.madlevel5task2.R
import nl.soffware.madlevel5task2.model.Platform
import nl.soffware.madlevel5task2.ui.viewmodel.GameViewModel
import java.text.SimpleDateFormat
import java.util.*


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddGameFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private var selectedPlatform: Platform = Platform.PC
    private val viewModel: GameViewModel by viewModels()
    private val calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupPlatformSpinner()
        setupDatePicker()
        setupSaveButton()
    }

    private fun setupPlatformSpinner() {
        et_platform.onItemSelectedListener = this
        val platformNames = Platform.values().map(Platform::title)
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            platformNames
        )
        et_platform.adapter = adapter
    }

    private fun setupSaveButton() {
        fab_save.setOnClickListener {
            val title = et_name.text.toString()
            val releaseDate = calendar.time
            viewModel.addGame(title, selectedPlatform, releaseDate)
            findNavController().popBackStack()
        }
    }

    private fun setupDatePicker() {
        val dateFormatter = SimpleDateFormat(getString(R.string.date_format), Locale.ENGLISH)
        val day = calendar[Calendar.DAY_OF_MONTH]
        val month = calendar[Calendar.MONTH]
        val year = calendar[Calendar.YEAR]
        // date picker dialog
        val picker = DatePickerDialog(
            requireContext(),
            { _, enteredYear, monthOfYear, dayOfMonth ->
                calendar.set(enteredYear, monthOfYear, dayOfMonth)
                et_release_date.setText(dateFormatter.format(calendar.time))
                et_release_date.clearFocus()
            },
            year,
            month,
            day
        )

        et_release_date.inputType = InputType.TYPE_NULL
        et_release_date.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                picker.show()
            } else {
                picker.hide()
            }
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
        selectedPlatform = Platform.values()[pos]
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        selectedPlatform = Platform.PC
    }
}