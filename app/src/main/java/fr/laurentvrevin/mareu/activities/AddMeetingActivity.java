package fr.laurentvrevin.mareu.activities;

import static fr.laurentvrevin.mareu.service.DummyEmployeesGenerator.DUMMY_EMPLOYEES;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import fr.laurentvrevin.mareu.DI.DI;
import fr.laurentvrevin.mareu.R;
import fr.laurentvrevin.mareu.UtilsList;
import fr.laurentvrevin.mareu.fragment.EmployeesListDialogFragment;
import fr.laurentvrevin.mareu.model.Employee;
import fr.laurentvrevin.mareu.model.Meeting;
import fr.laurentvrevin.mareu.model.Room;
import fr.laurentvrevin.mareu.service.DummyRoomGenerator;
import fr.laurentvrevin.mareu.service.MareuApiService;


public class AddMeetingActivity extends AppCompatActivity implements EmployeesListDialogFragment.Listener {
    private final ArrayList<Meeting> createMeetingList = new ArrayList<>();
    private final MareuApiService mMareuApiService = DI.getMeetingsApiService();
    TextView txt_dateSelected, mButtonDateTimePicker, list_invited;
    TextInputLayout txt_Meeting_Object;
    String meetingObject, roomSelected, listInvited, colorSelected;
    int yearSelected, monthSelected, daySelected;
    int chip;
    private Calendar mStartdate, mEndTime;
    private Button mButtonInvitation, mButtonSaveMeeting;
    private Chip chip1h, chip2h, chip3h;
    private ChipGroup chipgroup;
    private Spinner mRoomToSelectSpinner;
    private ArrayList<Employee> mEmployeesToCheck;
    private ArrayList<Employee> mEmployeesSelected = new ArrayList<>();
    private EmployeesListDialogFragment mDialogFragment;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_meeting_add);

        mButtonDateTimePicker = findViewById(R.id.tv_date_time_toselect);
        txt_dateSelected = findViewById(R.id.tv_date_time_toselect);
        chip1h = findViewById(R.id.chip1);
        chip2h = findViewById(R.id.chip2);
        chip3h = findViewById(R.id.chip3);
        chipgroup = findViewById(R.id.chipgroup);
        mRoomToSelectSpinner = findViewById(R.id.spinner_room_toselect);
        mButtonInvitation = findViewById(R.id.button_invitation_employees);
        list_invited = findViewById(R.id.tv_list_invited);
        mButtonSaveMeeting = findViewById(R.id.button_save_meeting);
        txt_Meeting_Object = findViewById(R.id.txt_Meeting_Object);

        //On ouvre le dialog fragment en cliquant sur le button "mbutton_invitation"
        mButtonInvitation.setOnClickListener(v -> {
            mDialogFragment = EmployeesListDialogFragment.createDialogFragment(DUMMY_EMPLOYEES, mEmployeesSelected, AddMeetingActivity.this);
            mDialogFragment.show(getSupportFragmentManager(), "MyRecyclerDialogFragment");
            //passer la liste employeeToCheck ici
            mEmployeesToCheck = new ArrayList<>(DUMMY_EMPLOYEES);
        });

        //ON GERE LE SPINNER DES SALLES DE REUNION
        //rooms = les salles étant dans getRooms
        Room[] rooms = DummyRoomGenerator.getRooms();
        //On crée l'adapter de type Rooms dans un nouvel adapter
        ArrayAdapter<Room> adapter = new ArrayAdapter<Room>
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
                Room rooms = (Room) adapter.getItem(position);
                //on met dans la textview via la variable roomSelected la salle  choisie
                roomSelected = rooms.getName();
                colorSelected = rooms.getAvatarColor();
            }
        });

        //ON GERE LE TIME PICKER
        //on commence le time Picker à l'heure du jour

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 0);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this);
        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        this.yearSelected = calendar.get(Calendar.YEAR);
        this.monthSelected = calendar.get(Calendar.MONTH);
        this.daySelected = calendar.get(Calendar.DAY_OF_MONTH);
        int thour = calendar.get(Calendar.HOUR_OF_DAY);
        int tminute = calendar.get(Calendar.MINUTE);

        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy"); //je lui dis quel format de date je souhaite
        SimpleDateFormat format2 = new SimpleDateFormat("HH:mm");
        chip = 60;

        //on récupère l'heure et les minutes sélectionnées dans la vue pour être mises dans thour et tminutes
        TimePickerDialog.OnTimeSetListener onTimeSetListener = (view, hourSelected, minutesSelected) -> {

            mStartdate.set(Calendar.HOUR_OF_DAY, hourSelected); //on passe l'heure sélectionnée
            mStartdate.set(Calendar.MINUTE, minutesSelected); //on passe les minutes sélectionnées
            txt_dateSelected.setText("Date : " + format1.format(mStartdate.getTime()) + " à : " + format2.format(mStartdate.getTime())); //j'affiche la date sélectionnée
            mEndTime.set(Calendar.HOUR_OF_DAY, hourSelected);
            mEndTime.set(Calendar.MINUTE, minutesSelected);

        };
        //CHIPGROUP
        chip1h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chip = 60;
            }
        });
        chip2h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chip = 120;
            }
        });
        chip3h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chip = 180;
            }
        });

        //Quand je clique sur le bouton datetimePicker ouvre le fragment via la variable materialDatePicker
        mButtonDateTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();

            }
        });
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, thour, tminute, true);
        DatePickerDialog.OnDateSetListener onDateSetListener = (view, year, month, dayOfMonth) -> {

        };
        datePickerDialog.setButton(DatePickerDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mStartdate = Calendar.getInstance();
                mEndTime = Calendar.getInstance();
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
        SimpleDateFormat formatToast = new SimpleDateFormat("HH:mm");

        if (meetingObject.isEmpty()) {
            txt_Meeting_Object.setError("Merci de saisir un objet de réunion");
            return;
        }
        if (mStartdate == null) {
            Toast.makeText(this, "Vous devez choisir un date et une heure de début", Toast.LENGTH_SHORT).show();
            return;
        }
        if (listInvited == "") {
            Toast.makeText(this, "La liste des invités est vide", Toast.LENGTH_SHORT).show();
            return;
        }
        //On ajoute la durée choisie via le chip à mEndTime et on vérifie dans le toast ¨PENSER A CLEAN
        mEndTime.add(Calendar.MINUTE, chip);
        Toast.makeText(this, "La durée est de : " + chip + " minutes, votre réunion se termine à " + formatToast.format(mEndTime.getTime()), Toast.LENGTH_LONG).show();
        mMareuApiService.createMeeting(new Meeting(colorSelected, meetingObject, roomSelected, mStartdate, mEndTime, mEmployeesSelected));
        finish();
    }

    @Override
    public void onEmployeesSelected(ArrayList<Employee> employees) {
        mEmployeesSelected = employees;
        //je charge la textview avec la liste des employées sélectionnés
        list_invited.setText("Voici les salarié(e)s invité(e)s : " + UtilsList.listEmployeesToString(employees));
        mDialogFragment.dismiss();
    }
}