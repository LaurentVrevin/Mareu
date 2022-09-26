package fr.laurentvrevin.mareu.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import fr.laurentvrevin.mareu.DI.DI;
import fr.laurentvrevin.mareu.R;
import fr.laurentvrevin.mareu.adapter.MeetingsRecyclerViewAdapter;
import fr.laurentvrevin.mareu.model.Meetings;
import fr.laurentvrevin.mareu.service.MareuApiService;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton addMeetingButton;
    private RecyclerView meetingRecyclerView;
    private ArrayList<Meetings> mMeetingsArrayList;
    private MareuApiService mMareuApiService = DI.getEmployeesApiService();


    private void initRecyclerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        meetingRecyclerView.setLayoutManager(layoutManager);
        MeetingsRecyclerViewAdapter meetingsRecyclerViewAdapter = new MeetingsRecyclerViewAdapter(mMeetingsArrayList);
        meetingRecyclerView.setAdapter(meetingsRecyclerViewAdapter);

    }

    private void initData(){
        mMeetingsArrayList = (ArrayList<Meetings>) mMareuApiService.getMeetings();
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