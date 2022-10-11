package fr.laurentvrevin.mareu.activities;

import static fr.laurentvrevin.mareu.service.DummyEmployeesGenerator.DUMMY_EMPLOYEES;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import fr.laurentvrevin.mareu.DI.DI;
import fr.laurentvrevin.mareu.R;
import fr.laurentvrevin.mareu.Utils;
import fr.laurentvrevin.mareu.fragment.EmployeesListDialogFragment;
import fr.laurentvrevin.mareu.model.Employees;
import fr.laurentvrevin.mareu.model.Meetings;
import fr.laurentvrevin.mareu.model.Rooms;
import fr.laurentvrevin.mareu.service.DummyRoomsGenerator;
import fr.laurentvrevin.mareu.service.MareuApiService;


public class MeetingRoomBookingActivity extends AppCompatActivity implements EmployeesListDialogFragment.Listener {
    private Button mButtonInvitation, mButtonSaveMeeting;
    TextView txt_dateSelected, mButtonDateTimePicker,  txt_timeSeleted, txt_roomSeleted, list_invited;
    TextInputLayout txt_Meeting_Object;
    //private Employees employees;
    private Spinner mRoomToSelectSpinner;

    String meetingObject, roomSelected, listInvited;
    private ArrayList<Employees> mEmployeesToCheck;
    private ArrayList<Employees> mEmployeesSelected = new ArrayList<>();
    private ArrayList<Meetings> createMeetingList = new ArrayList<>();
    private EmployeesListDialogFragment mDialogFragment;
    private MareuApiService mMareuApiService = DI.getMeetingsApiService();
    private Calendar mStartdate;
    private Calendar mEndDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_meeting_room_booking);




        mButtonDateTimePicker = findViewById(R.id.tv_datetime_selected);
        txt_dateSelected = findViewById(R.id.tv_datetime_selected);
       // txt_timeSeleted = findViewById(R.id.tv_time_selected);
        mRoomToSelectSpinner = findViewById(R.id.spinner_room_toselect);
        //txt_roomSeleted = findViewById(R.id.tv_room_selected);
        mButtonInvitation = findViewById(R.id.button_invitation_employees);
        list_invited = findViewById(R.id.tv_list_invited);
        mButtonSaveMeeting = findViewById(R.id.button_save_meeting);
        txt_Meeting_Object = findViewById(R.id.txt_Meeting_Object);

        //On ouvre le dialog fragment en cliquant sur le button "mbutton_invitation"
        mButtonInvitation.setOnClickListener(v -> {
            mDialogFragment = EmployeesListDialogFragment.createDialogFragment((ArrayList<Employees>) DUMMY_EMPLOYEES, mEmployeesSelected, MeetingRoomBookingActivity.this);
            mDialogFragment.show(getSupportFragmentManager(), "MyFragment");
            //passer la liste employeeToCheck ici
            mEmployeesToCheck = new ArrayList<>(DUMMY_EMPLOYEES);
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
                //txt_roomSeleted.setText(rooms.getName());
                roomSelected = rooms.getName();
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
        int thour = calendar.get(Calendar.HOUR_OF_DAY);
        int tminute = calendar.get(Calendar.MINUTE);
        //txt_dateSelected.setText(timeSelected);


        //on récupère l'heure et les minutes sélectionnées dans la vue pour être mises dans thour et tminutes
        TimePickerDialog.OnTimeSetListener onTimeSetListener = (view, hourSelected, minutesSelected) -> {

            mStartdate.set(Calendar.HOUR_OF_DAY, hourSelected); //on passe l'heure sélectionnée
            mStartdate.set(Calendar.MINUTE, minutesSelected); //on passe les minutes sélectionnées
            SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy"); //je lui dis quel format de date je souhaite
            SimpleDateFormat format2 = new SimpleDateFormat("HH:mm");
            txt_dateSelected.setText("Date : " + format1.format(mStartdate.getTime()) + " à : " + format2.format(mStartdate.getTime())); //j'affiche la date sélectionnée

        };

        //Quand je clique sur le bouton datetimePicker ouvre le fragment via la variable materialDatePicker
        mButtonDateTimePicker.setOnClickListener(new View.OnClickListener() {
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
                mStartdate = Calendar.getInstance();
                mStartdate.setTimeInMillis((Long) selection);
                //timeBuilder.setTimeFormat()
                timePickerDialog.setTitle("Sélectionne l'heure de début");
                timePickerDialog.show();
            }
        });


        mButtonSaveMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == mButtonSaveMeeting) {
                    onSubmit();
                }
            }
        });
    }

    private void onSubmit() {

        meetingObject = txt_Meeting_Object.getEditText().getText().toString();
        listInvited = list_invited.getText().toString();

        //
        if (meetingObject.isEmpty()) {
            txt_Meeting_Object.setError("Merci de saisir un objet de réunion");
            return;
        }
        if (mStartdate == null){
            Toast.makeText(this, "Vous devez choisir un date et une heure de début", Toast.LENGTH_SHORT).show();
        return;
        }
        if (listInvited ==""){
            Toast.makeText(this, "La liste des invités est vide", Toast.LENGTH_SHORT).show();
        return;
        }

        mMareuApiService.createMeeting(new Meetings(meetingObject, roomSelected, mStartdate, mEmployeesSelected));
        //Toast.makeText(this, "L'objet de la réunion n'est pas vide", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onEmployeesSelected(ArrayList<Employees> employees) {

        mEmployeesSelected = employees;
        //je charge la textview avec la liste des employées sélectionnés
        list_invited.setText("Voici les salarié(e)s invité(e)s : " + Utils.listEmployeesToString(employees));
        mDialogFragment.dismiss();
    }


}