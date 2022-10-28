package fr.laurentvrevin.mareu.events;

import fr.laurentvrevin.mareu.model.Meeting;

public class DeleteMeetingEvent {

    public Meeting meetings;

    public DeleteMeetingEvent(Meeting meeting) {
        this.meetings = meeting;
    }
}
