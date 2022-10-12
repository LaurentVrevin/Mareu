package fr.laurentvrevin.mareu.activities;



import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;

import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.floatingactionbutton.FloatingActionButton;



import java.util.ArrayList;
import java.util.Calendar;

import java.util.List;

import fr.laurentvrevin.mareu.DI.DI;
import fr.laurentvrevin.mareu.R;
import fr.laurentvrevin.mareu.adapter.MeetingsRecyclerViewAdapter;

import fr.laurentvrevin.mareu.fragment.RoomFilterDialogFragment;

import fr.laurentvrevin.mareu.model.Meetings;
import fr.laurentvrevin.mareu.model.Rooms;
import fr.laurentvrevin.mareu.service.MareuApiService;

public class MainActivity extends AppCompatActivity implements RoomFilterDialogFragment.RoomListener {

    private FloatingActionButton addMeetingButton;
    private RecyclerView meetingRecyclerView;
    private ArrayList<Meetings> mMeetingsArrayList = new ArrayList<>();
    private final MareuApiService mMareuApiService = DI.getMeetingsApiService();
    private RoomFilterDialogFragment mRoomFilterDF;
    private List<Rooms> mRoomsList;
    private MeetingsRecyclerViewAdapter meetingsRecyclerViewAdapter = new MeetingsRecyclerViewAdapter(mMeetingsArrayList);

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        meetingRecyclerView.setLayoutManager(layoutManager);
        //MeetingsRecyclerViewAdapter meetingsRecyclerViewAdapter = new MeetingsRecyclerViewAdapter(mMeetingsArrayList);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(meetingRecyclerView.getContext(), layoutManager.getOrientation());
        meetingRecyclerView.addItemDecoration(dividerItemDecoration);
        meetingRecyclerView.setAdapter(meetingsRecyclerViewAdapter);
        /*meetingRecyclerView.setAdapter(new MeetingsRecyclerViewAdapter(mMeetingsArrayList));
        mMeetingsArrayList = (ArrayList<Meetings>) mMareuApiService.getMeetings();*/
    }





    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void onResume() {
        super.onResume();
        meetingsRecyclerViewAdapter.updateMeetingList(mMareuApiService.getMeetings());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addMeetingButton = findViewById(R.id.activity_list_Meeting_Add_Fab);
        meetingRecyclerView = findViewById(R.id.activity_list_Meeting);

        addMeetingButton.setOnClickListener(v -> {
            Context context = addMeetingButton.getContext();
            Intent intent = new Intent(context, MeetingRoomBookingActivity.class);
            context.startActivity(intent);
        });
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

        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
            Calendar calendar1 = Calendar.getInstance();
            calendar1.set(year, month, dayOfMonth);
            meetingsRecyclerViewAdapter.updateMeetingList(mMareuApiService.getMeetingsByDay(calendar1.getTime()));
        };
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, dateSetListener, selectYear, selectMonth, selectDayOfMonth);
        datePickerDialog.show();
    }


    private void roomDialog() {

        mRoomFilterDF = RoomFilterDialogFragment.createDialogFragment(mRoomsList, this);
        mRoomFilterDF.show(getSupportFragmentManager(), "MyRoomsFragment");
    }

    private void resetFilter() {
        meetingsRecyclerViewAdapter.updateMeetingList(mMareuApiService.getMeetings());
    }

    //on crée le menu et on l'attache à la main activity
    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onRoomSelected(Rooms rooms) {
        meetingsRecyclerViewAdapter.updateMeetingList(mMareuApiService.getMeetingsByRoom(rooms));
    }
}