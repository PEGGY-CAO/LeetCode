import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class AvailableTime {

    public static List<List<Integer>> findAvailableTimeInterval(int[][][] schedules) {
        List<List<Integer>> allSchedules = new ArrayList<>();
        for (int i = 0; i < schedules.length; i++) {
            int[][] meetings = schedules[i];
            for (int j = 0; j < meetings.length; j++) {
                allSchedules.add(Arrays.stream(meetings[j]).boxed().collect(Collectors.toList()));
            }
        }
        allSchedules.sort((x, y) -> Integer.compare(x.get(0), y.get(0)));

        List<List<Integer>> result = new ArrayList<>();
        int earliestStartTime = 0;
        int currentMeeting = 0;
        while (earliestStartTime <= 2400 && currentMeeting < allSchedules.size()) {
            List<Integer> curMeetingInterval = allSchedules.get(currentMeeting);
            int curStartTime = curMeetingInterval.get(0);
            int curEndTime = curMeetingInterval.get(1);
            if (earliestStartTime < curStartTime) {
                List<Integer> newTimeInterval = new ArrayList<>();
                newTimeInterval.add(earliestStartTime);
                newTimeInterval.add(curStartTime);
                result.add(newTimeInterval);
                earliestStartTime = curEndTime;
            } else {
                earliestStartTime = earliestStartTime > curEndTime ? earliestStartTime : curEndTime;
            }
            currentMeeting++;
        }

        if (earliestStartTime < 2400) {
            result.add(Arrays.asList(earliestStartTime, 2400));
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] p1schedules = {
                {800, 900},
                {1340, 1600},
                {915, 1020}
        };
        int[][] p2schedules = {
                {915, 950},
                {1020, 1150},
                {820, 850}
        };
        int[][] p3schedules = {
                {1300, 1320},
                {1800, 1900}
        };
        int[][][] allMeetingSchedules = {p1schedules, p2schedules, p3schedules};
        List<List<Integer>> availableSlots = findAvailableTimeInterval(allMeetingSchedules);
        for (List<Integer> timeslot : availableSlots) {
            System.out.println("Available slot: " + timeslot.get(0) + " "+ timeslot.get(1));
        }
    }
}
