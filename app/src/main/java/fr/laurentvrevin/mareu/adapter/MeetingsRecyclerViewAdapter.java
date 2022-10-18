package fr.laurentvrevin.mareu.adapter;

import android.annotation.SuppressLint;
import android.media.metrics.Event;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import fr.laurentvrevin.mareu.R;
import fr.laurentvrevin.mareu.Utils;

import fr.laurentvrevin.mareu.events.DeleteMeetingEvent;
import fr.laurentvrevin.mareu.model.Meetings;
import fr.laurentvrevin.mareu.service.MareuApiService;


public class MeetingsRecyclerViewAdapter extends RecyclerView.Adapter<MeetingsRecyclerViewAdapter.ViewHolder> {
    private final ArrayList<Meetings> mMeetings;
    private MareuApiService mareuApiService;

    private void deleteMeetingList(Meetings meetings){
        EventBus.getDefault().post(new DeleteMeetingEvent(meetings));
    }

    public void updateMeetingList(List<Meetings> newMeetingsList){
        mMeetings.clear();
        mMeetings.addAll(newMeetingsList);
        notifyDataSetChanged();
        Log.d("UPDATE", "updateMeetingList: nouvelle donnee");
    }

    public MeetingsRecyclerViewAdapter(ArrayList<Meetings> meetings) {
        this.mMeetings = meetings;//REVIEW new ArrayList<>(meetings);
    }

    @NonNull
    @Override
    //le layout sur lequel je souhaite me baser est item_list_meeting.xml
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_meeting, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Meetings meeting = mMeetings.get(position);
        SimpleDateFormat timeMeeting = new SimpleDateFormat("HH:mm");
        holder.mMeetingName.setText(meeting.getMeetingname() + " - ");
        holder.mStarTime.setText(timeMeeting.format(meeting.getDateMeeting().getTime()) + " - ");
        holder.mRoomName.setText(meeting.getRoomname());
        holder.mUserMail.setText(Utils.listEmployeesToString(meeting.getEmployeesMails()));
        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mMeetings.remove((position));
                EventBus.getDefault().post(new DeleteMeetingEvent(meeting));
                //deleteMeetingList(meeting);
                Log.d("GARBAGE", "onClick: delete from garbage");
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mMeetingName, mStarTime, mRoomName, mUserMail;
        public final ImageButton mDeleteButton;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mMeetingName = itemView.findViewById(R.id.item_list_meeting_meetingname);
            mStarTime = itemView.findViewById(R.id.item_list_meeting_startime);
            mRoomName = itemView.findViewById(R.id.item_list_meeting_roomname);
            mUserMail = itemView.findViewById(R.id.item_list_meeting_useremail);
            mDeleteButton = itemView.findViewById(R.id.button_delete);
        }
    }
}
