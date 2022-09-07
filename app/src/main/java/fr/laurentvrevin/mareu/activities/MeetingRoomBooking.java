package fr.laurentvrevin.mareu.activities;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import fr.laurentvrevin.mareu.R;
import fr.laurentvrevin.mareu.fragment.EmployeesListDialogFragment;
import fr.laurentvrevin.mareu.model.Rooms;
import fr.laurentvrevin.mareu.service.DummyRoomsGenerator;

public class MeetingRoomBooking extends AppCompatActivity {
    private Button datetimePickerButton, inviteEmployeeButton;
    private TextView dateSelected, timeSelected, roomSelected;
    int thour, tminute, resultChipGroup;
    private Spinner mRoomToSelectSpinner;
    private Chip chips_choice_group, chips_choice_01, chips_choice_02, chips_choice_03;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_room_booking);

        datetimePickerButton = findViewById(R.id.DatePickerButton);
        dateSelected = findViewById(R.id.tvDateSelected);
        timeSelected = findViewById(R.id.tvTimeSelected);
        mRoomToSelectSpinner = findViewById(R.id.RoomToSelectSpinner);
        roomSelected = findViewById(R.id.tvRoomSelected);
        inviteEmployeeButton = findViewById(R.id.invitationEmployeesButton);




        //On ouvre le dialog fragment en cliquant sur le button inviteEmployeeButton
        inviteEmployeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EmployeesListDialogFragment dialogFragment = new EmployeesListDialogFragment();
                dialogFragment.show(getFragmentManager(), "Mon Fragment");

            }
        });





        //ON GERE LE SPINNER DES SALLES DE REUNION
        //rooms = les salles étant dans getRooms
        Rooms[] rooms = DummyRoomsGenerator.getRooms();
        //On crée l'adapter de type Rooms dans un nouvel adapter
        ArrayAdapter<Rooms> adapter = new ArrayAdapter<Rooms>
                (this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, rooms);
        //On charge l'adapter dans le spinner
        this.mRoomToSelectSpinner.setAdapter(adapter);
        //On définit ce qu'il se passera quand on le déroule, selon ce que l'on fait.

        this.mRoomToSelectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //On récupère la vue de l'item sélectionné
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onItemSelectedHandler(parent, view, position, id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

            //gestionnaire de l'item sélectionné, on récupère l'item selon sa position, servira pour plus tard avec rooms
            private void onItemSelectedHandler(AdapterView<?> parent, View view, int position, long id) {
                Adapter adapter = parent.getAdapter();
                Rooms rooms = (Rooms) adapter.getItem(position);
                //on met dans la textview via la variable roomSelected la salle  choisie
                roomSelected.setText(rooms.getName());
            }
        });


        //ON GERE LE DATE PICKER
        long today = MaterialDatePicker.todayInUtcMilliseconds();
        //contrainte du date picker, il doit démarrer à la date du jour
        // et ne doit pas permettre de réserver une date antérieur à celle-ci.
        CalendarConstraints.Builder constraintDateBuilder = new CalendarConstraints.Builder();
        constraintDateBuilder.setStart(today);
        constraintDateBuilder.setValidator(DateValidatorPointForward.now());

        //Material Date Picker
        MaterialDatePicker.Builder<Long> dateBuilder = MaterialDatePicker.Builder.datePicker();
        //Material Time Picker
        //MaterialTimePicker.Builder timeBuilder = new MaterialTimePicker.Builder();

        dateBuilder.setTitleText("SELECTIONNE UNE DATE");
        //charge pour date sélectionnée, la date du jour
        dateBuilder.setSelection(today);
        //Je lui rappelle qu'il a pour contrainte d'être à aujourd'hui
        // et de ne pas pouvoir sélectionner de date antérieur à celle du jour
        dateBuilder.setCalendarConstraints(constraintDateBuilder.build());
        final MaterialDatePicker<Long> materialDatePicker = dateBuilder.build();

        //ON GERE LE TIME PICKER
        //on commence le time Picker à l'heure du jour
        Calendar calendar = Calendar.getInstance();
        thour = calendar.get(Calendar.HOUR_OF_DAY);
        tminute = calendar.get(Calendar.MINUTE);

        /**final MaterialTimePicker materialTimePicker = new MaterialTimePicker.Builder()
         .setTimeFormat(TimeFormat.CLOCK_12H)
         .setHour(thour)
         .setMinute(tminute)
         .build();
         TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        thour = hourOfDay;
        tminute = minute;
        timeSelected.setText(String.format(Locale.getDefault(), "%02d:%02d",thour, tminute));
        }
        };*/

        TimePickerDialog.OnTimeSetListener onTimeSetListener = (view, hourOfDay, selectedminute) -> {
            calendar.set(Calendar.HOUR, hourOfDay);
            calendar.set(Calendar.MINUTE, selectedminute);
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
            dateSelected.setText("Date : " + format1.format(calendar.getTime()));
            thour = hourOfDay;
            tminute = selectedminute;
            timeSelected.setText(String.format(Locale.getDefault(), "%02d:%02d", thour, tminute));
        };

        //Quand je clique sur le bouton datetimePicker ouvre le fragment via la variable materialDatePicker
        datetimePickerButton.setOnClickListener(new View.OnClickListener() {
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
                calendar.setTimeInMillis((Long) selection);

                //materialTimePicker.show(getSupportFragmentManager(), "fragment_tag");
                timePickerDialog.setTitle("Sélectionne l'heure de début");
                timePickerDialog.show();
            }
        });
    }
}