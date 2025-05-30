package ScheduleRooms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomMeeting {

    Map<Rooms, List<Meeting>> meetingMap;

    RoomMeeting() {
        this.meetingMap = new HashMap<>();
    }

    public void addMeeting(Rooms room, Meeting meeting) {
        if (!meetingMap.containsKey(room)) {
            meetingMap.put(room, new ArrayList<>());
        }
        meetingMap.get(room).add(meeting);
    }


    public Rooms scheduleMeeting(Meeting meeting) {
        for (Map.Entry<Rooms, List<Meeting>> entry : meetingMap.entrySet()) {
            Rooms room = entry.getKey();
            List<Meeting> meetings = entry.getValue();

            meetings.sort((o1, o2) -> {
                int startTime1 = o1.getTimeRange().getStartTime();
                int startTime2 = o2.getTimeRange().getStartTime();
                return Integer.compare(startTime1, startTime2);
            });

            for (int i = 0; i < meetings.size(); ++i) {
                Meeting currentMeeting = meetings.get(i);

                if (i == 0 && currentMeeting.getTimeRange().getStartTime() > meeting.getTimeRange().getEndTime()) {
                    meetings.add(i, meeting);
                    System.out.println(room);
                    return room;
                }

                //collision
                if (currentMeeting.getTimeRange().getEndTime() >= meeting.getTimeRange().getStartTime()) {
                    continue;

                }


                if (i == meetings.size() - 1) {
                    meetings.add(i, meeting);
                    System.out.println(room);
                    return room;
                }

                //check if next meeting room starting point is greater than current meeting end time
                Meeting nextMeeting = meetings.get(i + 1);
                if (nextMeeting.getTimeRange().startTime > meeting.getTimeRange().getEndTime()) {
                    meetings.add(i + 1, meeting);
                    System.out.println(room);
                    return room;
                }


            }

        }
        System.out.println("No rooms");
        return null; // No available room found
    }


    public Rooms scheduleMeetingOptimized(Meeting meeting) {
        for (Map.Entry<Rooms, List<Meeting>> entry : meetingMap.entrySet()) {
            Rooms room = entry.getKey();
            List<Meeting> meetings = entry.getValue();

            meetings.sort((o1, o2) -> {
                int startTime1 = o1.getTimeRange().getStartTime();
                int startTime2 = o2.getTimeRange().getStartTime();
                return Integer.compare(startTime1, startTime2);
            });


            int currMeetingIndex = binarySearch(meetings,meeting,0,meetings.size()-1);

            if(currMeetingIndex == -1) {
                if(meetings.get(0).getTimeRange().startTime > meeting.getTimeRange().getEndTime()){
                    meetings.add(0,meeting);
                    System.out.println(room);
                    return room;
                }
                   continue;

            }
            Meeting currMeeting = meetings.get(currMeetingIndex);
            if(currMeeting.getTimeRange().getEndTime() >= meeting.getTimeRange().getStartTime()){
                        continue;

            }

            if(currMeetingIndex == meetings.size()-1){
                meetings.add(meeting);
                System.out.println(room);
                return room;
            }

            Meeting nextMeeting = meetings.get(currMeetingIndex + 1);
            if(nextMeeting.getTimeRange().getStartTime() > meeting.getTimeRange().getEndTime()){
                meetings.add(currMeetingIndex + 1,meeting);
                System.out.println(room);
                return room;
            }

        }
        System.out.println("No rooms");
        return null;

    }


    public int binarySearch(List<Meeting> meetingList, Meeting target, int l, int r) {
        if (l > r) {
            return r;
        }

        int mid = (l + r) / 2;

        // Use the meeting's end time compared to the target's start time.
        if (meetingList.get(mid).getTimeRange().getEndTime() <= target.getTimeRange().getStartTime()) {
            return binarySearch(meetingList, target, mid + 1, r);
        } else {
            return binarySearch(meetingList, target, l, mid - 1);
        }
    }

}


//TC: O(R*MlogM)
//Space Complexity: O(M)








