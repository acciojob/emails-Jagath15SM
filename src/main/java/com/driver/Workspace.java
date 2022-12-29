package com.driver;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Workspace extends Gmail{
    private String emailId;
    private String password;

    private ArrayList<Meeting> calendar; // Stores all the meetings

    public Workspace(String emailId) {
        // The inboxCapacity is equal to the maximum value an integer can store.
        super(emailId, Integer.MAX_VALUE);
        this.emailId = emailId;
        this.password = "Accio@123";
        this.calendar = new ArrayList<>();
    }

    public void addMeeting(Meeting meeting){
        //add the meeting to calendar
        calendar.add(meeting);

    }

    public int findMaxMeetings(){
        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am
        ArrayList<Meeting> attending_meetings = new ArrayList<>(calendar);
        Collections.sort(attending_meetings, new Comparator<Meeting>() {
            @Override
            public int compare(Meeting obj1, Meeting obj2) {
                LocalTime s1 = obj1.getStartTime();
                LocalTime s2 = obj2.getStartTime();

                LocalTime e1 = obj1.getEndTime();
                LocalTime e2 = obj2.getEndTime();

                return e1.compareTo(e2) != 0 ? e1.compareTo(e2) : s1.compareTo(s2);
            }
        });
        LocalTime endTime = LocalTime.MIN;
        int result = 0;
        for(Meeting m : attending_meetings){
            if(endTime.compareTo(m.getStartTime()) < 0){
                result++;
                endTime = m.getEndTime();
            }
        }
        return result;
    }
}
