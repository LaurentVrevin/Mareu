package fr.laurentvrevin.mareu.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.timepicker.MaterialTimePicker;

import java.util.Locale;

import fr.laurentvrevin.mareu.R;

public class MeetingRoomBooking extends AppCompatActivity {
    private Button datetimePicker;
    private TextView dateSelected, timeSelected;
    int thour, tminute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_room_booking);

        datetimePicker = findViewById(R.id.DatePickerButton);
        dateSelected = findViewById(R.id.tvDateSelected);
        timeSelected = findViewById(R.id.tvTimeSelected);



        long today = MaterialDatePicker.todayInUtcMilliseconds();
        //contrainte du date picker, il doit démarrer à la date du jour
        // et ne doit pas permettre de réserver une date antérieur à celle-ci.
        CalendarConstraints.Builder constraintDateBuilder = new CalendarConstraints.Builder();
        constraintDateBuilder.setStart(today);
        constraintDateBuilder.setValidator(DateValidatorPointForward.now());

        //Material Date Picker
        MaterialDatePicker.Builder dateBuilder = MaterialDatePicker.Builder.datePicker();
        //Material Time Picker
        MaterialTimePicker.Builder timeBuilder = new MaterialTimePicker.Builder();

        dateBuilder.setTitleText("SELECTIONNE UNE DATE");
        //charge pour date sélectionnée, la date du jour
        dateBuilder.setSelection(today);
        //Je lui rappelle qu'il a pour contrainte d'être à aujourd'hui
        // et de ne pas pouvoir sélectionner de date antérieur à celle du jour
        dateBuilder.setCalendarConstraints(constraintDateBuilder.build());
        final MaterialDatePicker materialDatePicker = dateBuilder.build();

        //Material Time Picker
        MaterialTimePicker materialTimePicker = timeBuilder.build();
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int selectedHour, int selectedminute) {
                thour = selectedHour;
                tminute = selectedminute;
                timeSelected.setText(String.format(Locale.getDefault(),"%02d:%02d", thour, tminute));
            }
        };

        //Quand je clique sur le bouton datetimePicker ouvre le fragment via la variable materialDatePicker
        datetimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDatePicker.show(getSupportFragmentManager(), "DATE_PICKER");
            }
        });

        //Quand je valide la date choisie, ouvre le timePicker via materialTimePicker
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, thour, tminute, true);
        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                //récupérer jour + mois + année
                //ouvrir time picker
                dateSelected.setText("Date : "+ materialDatePicker.getHeaderText());

                timePickerDialog.setTitle("Sélectionne l'heure de début");
                timePickerDialog.show();
                TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        thour = hourOfDay;
                        tminute = minute;
                        timeSelected.setText(String.format(Locale.getDefault(),"%02d:%02d", thour, tminute));
                    }

                };

            }

        });



    }
}