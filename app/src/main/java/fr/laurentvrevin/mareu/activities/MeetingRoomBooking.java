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

import fr.laurentvrevin.mareu.R;

public class MeetingRoomBooking extends AppCompatActivity {
    private Button datePicker;
    private TextView dateTimeSelected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_room_booking);

        datePicker = findViewById(R.id.DatePickerButton);
        dateTimeSelected = findViewById(R.id.tvDateTimeSelected);


        long today = MaterialDatePicker.todayInUtcMilliseconds();

        //contrainte du date picker, il doit démarrer à la date du jour
        // et ne doit pas permettre de réserver une date antérieur à celle-ci.
        CalendarConstraints.Builder constraintBuilder = new CalendarConstraints.Builder();
        constraintBuilder.setStart(today);
        constraintBuilder.setValidator(DateValidatorPointForward.now());

        //Material Date Picker
        MaterialDatePicker.Builder dateBuilder = MaterialDatePicker.Builder.datePicker();
        MaterialTimePicker.Builder timeBuilder = new MaterialTimePicker.Builder();
        dateBuilder.setTitleText("SELECT A DATE");
        dateBuilder.setSelection(today);
        dateBuilder.setCalendarConstraints(constraintBuilder.build());
        final MaterialDatePicker materialDatePicker = dateBuilder.build();
        final MaterialTimePicker materialTimePicker = timeBuilder.build();

        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDatePicker.show(getSupportFragmentManager(), "DATE_PICKER");

            }
        });

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                //récupérer jour + mois + année
                //ouvrir time picker
                materialTimePicker.show(getSupportFragmentManager(), "TIME_PICKER");
                dateTimeSelected.setText("Date : "+ materialDatePicker.getHeaderText());

            }
        });


    }
}