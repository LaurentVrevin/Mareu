package fr.laurentvrevin.mareu.activities;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import fr.laurentvrevin.mareu.DI.DI;
import fr.laurentvrevin.mareu.R;
import fr.laurentvrevin.mareu.adapter.MeetingsRecyclerViewAdapter;
import fr.laurentvrevin.mareu.events.DeleteMeetingEvent;
import fr.laurentvrevin.mareu.fragment.RoomFilterDialogFragment;
import fr.laurentvrevin.mareu.model.Meeting;
import fr.laurentvrevin.mareu.model.Room;
import fr.laurentvrevin.mareu.service.MareuApiService;

public class MainActivity extends AppCompatActivity implements RoomFilterDialogFragment.RoomListener {

    private final MareuApiService mMareuApiService = DI.getNewInstanceApiService();
    private final ArrayList<Meeting> mMeetingsArrayList= new ArrayList<>();
    private FloatingActionButton addMeetingButton;
    private RecyclerView meetingRecyclerView;
    private RoomFilterDialogFragment mRoomFilterDF;
    private List<Room> mRoomsList;
    private final MeetingsRecyclerViewAdapter meetingsRecyclerViewAdapter = new MeetingsRecyclerViewAdapter(mMeetingsArrayList);

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        meetingRecyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(meetingRecyclerView.getContext(), layoutManager.getOrientation());
        meetingRecyclerView.addItemDecoration(dividerItemDecoration);
        meetingRecyclerView.setAdapter(meetingsRecyclerViewAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onResume() {
        super.onResume();
        resetFilter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addMeetingButton = findViewById(R.id.activity_list_Meeting_Add_Fab);
        meetingRecyclerView = findViewById(R.id.activity_list_Meeting);
        addMeetingButton.setOnClickListener(v -> {
            Context context = addMeetingButton.getContext();
            Intent intent = new Intent(context, AddMeetingActivity.class);
            context.startActivity(intent);
        });
        initRecyclerView();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
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
        mMeetingsArrayList.clear();
        mMeetingsArrayList.addAll(mMareuApiService.getMeetings());
        meetingRecyclerView.getAdapter().notifyDataSetChanged();
    }

    //on crée le menu et on l'attache à la main activity
    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public void onRoomSelected(Room rooms) {
        meetingsRecyclerViewAdapter.updateMeetingList(mMareuApiService.getMeetingsByRoom(rooms));
    }

    @Subscribe
    public void onDeleteMeeting(DeleteMeetingEvent event) {
        mMareuApiService.deleteMeeting(event.meetings);
        onResume();
    }
}