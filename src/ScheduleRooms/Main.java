package ScheduleRooms;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        RoomMeeting roomMeeting = new RoomMeeting();
        roomMeeting.addMeeting(Rooms.one,new Meeting(new TimeRange(3,6)));
        roomMeeting.addMeeting(Rooms.two,new Meeting(new TimeRange(8,10)));
        roomMeeting.addMeeting(Rooms.two,new Meeting(new TimeRange(12,15)));

        roomMeeting.scheduleMeetingOptimized(new Meeting(new TimeRange(1,2)));
        roomMeeting.scheduleMeetingOptimized(new Meeting(new TimeRange(1,2)));
        roomMeeting.scheduleMeetingOptimized(new Meeting(new TimeRange(1,2)));
        roomMeeting.scheduleMeetingOptimized(new Meeting(new TimeRange(5,13)));
        roomMeeting.scheduleMeetingOptimized(new Meeting(new TimeRange(12,15)));

        }
    }



