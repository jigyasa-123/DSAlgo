package ScheduleRooms;

public class Meeting {
    TimeRange timeRange;

    public TimeRange getTimeRange() {
        return timeRange;
    }

    public Meeting(TimeRange timeRange) {
        this.timeRange = timeRange;
    }
}
