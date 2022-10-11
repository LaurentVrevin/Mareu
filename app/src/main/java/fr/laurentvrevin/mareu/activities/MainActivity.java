package fr.laurentvrevin.mareu.activities;

import static fr.laurentvrevin.mareu.service.DummyEmployeesGenerator.DUMMY_EMPLOYEES;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import fr.laurentvrevin.mareu.DI.DI;
import fr.laurentvrevin.mareu.R;
import fr.laurentvrevin.mareu.adapter.MeetingsRecyclerViewAdapter;
import fr.laurentvrevin.mareu.fragment.EmployeesListDialogFragment;
import fr.laurentvrevin.mareu.fragment.RoomFilterDialogFragment;
import fr.laurentvrevin.mareu.model.Employees;
import fr.laurentvrevin.mareu.model.Meetings;
import fr.laurentvrevin.mareu.model.Rooms;
import fr.laurentvrevin.mareu.service.MareuApiService;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton addMeetingButton;
    private RecyclerView meetingRecyclerView;
    private ArrayList<Meetings> mMeetingsArrayList = new ArrayList<>();
    private MareuApiService mMareuApiService = DI.getMeetingsApiService();
    private RoomFilterDialogFragment mRoomFilterDF;
    private List<Rooms> mRoomsList;

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        meetingRecyclerView.setLayoutManager(layoutManager);
        MeetingsRecyclerViewAdapter meetingsRecyclerViewAdapter = new MeetingsRecyclerViewAdapter(mMeetingsArrayList);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(meetingRecyclerView.getContext(), layoutManager.getOrientation());
        meetingRecyclerView.addItemDecoration(dividerItemDecoration);
        meetingRecyclerView.setAdapter(meetingsRecyclerViewAdapter);
    }

    private void initData() {
        mMeetingsArrayList = new ArrayList<>(mMareuApiService.getMeetings());
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    private void initList() {
        mMeetingsArrayList = (ArrayList<Meetings>) mMareuApiService.getMeetings();
        meetingRecyclerView.setAdapter(new MeetingsRecyclerViewAdapter(mMeetingsArrayList));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addMeetingButton = findViewById(R.id.activity_list_Meeting_Add_Fab);
        meetingRecyclerView = findViewById(R.id.activity_list_Meeting);

        addMeetingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = addMeetingButton.getContext();
                Intent intent = new Intent(context, MeetingRoomBookingActivity.class);
                context.startActivity(intent);
            }
        });
        initData();
        initRecyclerView();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.filter_date:
                dateDialog();

                return true;
            case R.id.filter_room:
                roomDialog();
                return true;
            case R.id.reset:
                resetFilter();
                return true;
            default:
            return super.onOptionsItemSelected(item);
        }
    }
    private void dateDialog() {
        Calendar calendar = Calendar.getInstance();
        int selectYear = calendar.get(Calendar.YEAR);
        int selectMonth = calendar.get(Calendar.MONTH);
        int selectDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                mMeetingsArrayList.clear();
                mMeetingsArrayList.addAll(mMareuApiService.getMeetingByDay(Calendar.getInstance()));
                meetingRecyclerView.getAdapter().notifyDataSetChanged();
            }
        };
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, dateSetListener, selectYear, selectMonth, selectDayOfMonth);
        datePickerDialog.show();
    }

    private void roomDialog() {

        mRoomFilterDF = RoomFilterDialogFragment.createDialogFragment(mRoomsList);
        mRoomFilterDF.show(getSupportFragmentManager(), "MyRoomsFragment");
    }

    private void resetFilter() {
        mMeetingsArrayList.clear();
        mMeetingsArrayList.addAll(mMareuApiService.getMeetings());
        //penser à mettre un if sinon plante si la liste est vide
        meetingRecyclerView.getAdapter().notifyDataSetChanged();
    }

    //on crée le menu et on l'attache à la main activity
    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}