package fr.laurentvrevin.mareu.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import fr.laurentvrevin.mareu.DI.DI;
import fr.laurentvrevin.mareu.R;
import fr.laurentvrevin.mareu.adapter.MeetingsRecyclerViewAdapter;
import fr.laurentvrevin.mareu.model.Meetings;
import fr.laurentvrevin.mareu.service.MareuApiService;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton addMeetingButton;
    private RecyclerView meetingRecyclerView;
    private ArrayList<Meetings> mMeetingsArrayList;
    private MareuApiService mMareuApiService = DI.getMeetingsApiService();



    private void initRecyclerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        meetingRecyclerView.setLayoutManager(layoutManager);
        MeetingsRecyclerViewAdapter meetingsRecyclerViewAdapter = new MeetingsRecyclerViewAdapter(mMeetingsArrayList);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(meetingRecyclerView.getContext(),layoutManager.getOrientation());
        meetingRecyclerView.addItemDecoration(dividerItemDecoration);
        meetingRecyclerView.setAdapter(meetingsRecyclerViewAdapter);
    }

    private void initData(){
        mMeetingsArrayList = new ArrayList<>(mMareuApiService.getMeetings());
    }

    private void initUI(){

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


}