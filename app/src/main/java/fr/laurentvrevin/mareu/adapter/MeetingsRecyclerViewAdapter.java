package fr.laurentvrevin.mareu.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import fr.laurentvrevin.mareu.R;
import fr.laurentvrevin.mareu.UtilsList;

import fr.laurentvrevin.mareu.events.DeleteMeetingEvent;
import fr.laurentvrevin.mareu.fragment.RoomFilterDialogFragment;
import fr.laurentvrevin.mareu.model.Meeting;
import fr.laurentvrevin.mareu.model.Rooms;
import fr.laurentvrevin.mareu.service.MareuApiService;


public class MeetingsRecyclerViewAdapter extends RecyclerView.Adapter<MeetingsRecyclerViewAdapter.ViewHolder> {
    private final ArrayList<Meeting> mMeetings;
    private MareuApiService mareuApiService;

    private void deleteMeetingList(Meeting meetings){
        EventBus.getDefault().post(new DeleteMeetingEvent(meetings));
    }

    public void updateMeetingList(List<Meeting> newMeetingsList){
        mMeetings.clear();
        mMeetings.addAll(newMeetingsList);
        notifyDataSetChanged();
        Log.d("UPDATE", "updateMeetingList: nouvelle donnee");
    }

    public MeetingsRecyclerViewAdapter(ArrayList<Meeting> meetings) {
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
        Meeting meeting = mMeetings.get(position);
        SimpleDateFormat timeMeeting = new SimpleDateFormat("HH:mm");
        holder.avatarColor.setColorFilter(Color.parseColor(meeting.getRoomColor()));
        holder.mMeetingName.setText(meeting.getMeetingname() + "- ");
        holder.mStarTime.setText(timeMeeting.format(meeting.getDateMeeting().getTime()) + "- ");
        holder.mRoomName.setText(meeting.getRoomname());
        holder.mUserMail.setText(UtilsList.listEmployeesToString(meeting.getEmployeesMails()));
        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteMeetingEvent(meeting));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mMeetingName, mStarTime, mRoomName, mUserMail;
        public final ImageView avatarColor;
        public final ImageButton mDeleteButton;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            avatarColor= itemView.findViewById(R.id.item_list_meeting_avatar);
            mMeetingName = itemView.findViewById(R.id.item_list_meeting_meetingname);
            mStarTime = itemView.findViewById(R.id.item_list_meeting_startime);
            mRoomName = itemView.findViewById(R.id.item_list_meeting_roomname);
            mUserMail = itemView.findViewById(R.id.item_list_meeting_useremail);
            mDeleteButton = itemView.findViewById(R.id.button_delete);
        }
    }
}
