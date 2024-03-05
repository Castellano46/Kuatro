package com.example.reservakuatro.ui

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import com.example.reservakuatro.R
import java.util.Calendar

class CreateAppointmentActivity : AppCompatActivity() {
    private val selectedCalendar: Calendar = Calendar.getInstance()
    private var selectedRadioButton: RadioButton? = null


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_appointment)

        val btNext = findViewById<Button>(R.id.btn_siguiente)
        val btnConfirm = findViewById<Button>(R.id.btn_confirmar)
        val cvNext = findViewById<CardView>(R.id.cv_siguiente)
        val cvConfirm = findViewById<CardView>(R.id.cv_confirmar)

        btNext.setOnClickListener {
            Log.d("CreateAppoitment", "button next clicked")
            cvNext.visibility = View.GONE
            cvConfirm.visibility = View.VISIBLE
        }

        btnConfirm.setOnClickListener {
            Log.d("CreateAppoitment", "Button confirm clicked")
            Toast.makeText(applicationContext, "Cita realizada correctamente", Toast.LENGTH_SHORT).show()
            finish()
        }

        //val spinnerSpecialties = findViewById<Spinner>(R.id.spinner_especialidades)
        val spinnerPista = findViewById<Spinner>(R.id.spinner_pista)

        /*val optionsSpecialties = arrayOf("Especialidad 1", "Especialidad 2", "Especialidad 3")
        spinnerSpecialties.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, optionsSpecialties)*/

        val optionsPista = arrayOf("Quiero bolas", "No quiero bolas")
        spinnerPista.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, optionsPista)
    }

    @SuppressLint("SetTextI18n")
    fun onClickScheduleDate(v: View?) {
        val etScheduleDate = findViewById<EditText>(R.id.et_fecha)
        val year = selectedCalendar.get(Calendar.YEAR)
        val month = selectedCalendar.get(Calendar.MONTH)
        val dayOfMonth = selectedCalendar.get(Calendar.DAY_OF_MONTH)
        val listener = DatePickerDialog.OnDateSetListener{datePicker, y, m, d ->
            selectedCalendar.set(y, m, d)
            etScheduleDate.setText("$y / ${m + 1} / $d")
            displayRadioButtons()
        }
        DatePickerDialog(this, listener, year, month, dayOfMonth).show()
    }

    private fun displayRadioButtons() {
        val radioGruopLeft = findViewById<LinearLayout>(R.id.radio_group_izq)
        val radioGroupRight = findViewById<LinearLayout>(R.id.radio_group_der)

        radioGruopLeft.removeAllViews()
        radioGroupRight.removeAllViews()

        selectedRadioButton = null

        var goToLeft = true

        val hours = arrayOf("8:00 AM", "9:30 AM", "11:00 AM", "12:30 PM", "14:00 PM", "16:00 PM",
            "17:30 PM", "19:00 PM", "20:30 PM")
        hours.forEach {
            val radioButton = RadioButton(this)
            radioButton.id = View.generateViewId()
            radioButton.text = it

            radioButton.setOnClickListener{ view ->
                selectedRadioButton?.isChecked = false
                selectedRadioButton = view as RadioButton?
                selectedRadioButton?.isChecked = true
            }

            if (goToLeft)
                radioGruopLeft.addView(radioButton)
            else
                radioGroupRight.addView(radioButton)
            goToLeft = !goToLeft
        }
    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Seguro quiere salir?")
        builder.setMessage("Si abandonas, los datos se perderán")
        builder.setPositiveButton("Salir"){_, _ ->
            finish()
        }

        builder.setNegativeButton("Continuar"){ dialog, _ ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }
}
