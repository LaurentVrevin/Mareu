package fr.laurentvrevin.mareu.events;

import fr.laurentvrevin.mareu.model.Meetings;

public class DeleteMeetingEvent {

    public Meetings meetings;

    public DeleteMeetingEvent(Meetings meeting) {
        this.meetings = meeting;
    }
}
