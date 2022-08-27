package fr.laurentvrevin.mareu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import fr.laurentvrevin.mareu.activities.MeetingRoomBooking;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton addMeetingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addMeetingButton = findViewById(R.id.activity_list_Meeting_Add_Fab);

        addMeetingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = addMeetingButton.getContext();
                Intent intent = new Intent(context, MeetingRoomBooking.class);
                context.startActivity(intent);

            }
        });
    }
}