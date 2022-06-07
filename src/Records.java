import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Records {
    public static ArrayList<Record> fullList = RecordDatastore.Retrieve();
    private static ArrayList<Record> list = CloneFullList();

    private static ArrayList<Record> CloneFullList() {
        ArrayList<Record> out = new ArrayList<>();
        out.addAll(fullList);
        return out;
    }

    // return false if r already exists
    public static boolean Add(Record r) {
        return true;
    }

    // return true if r exists
    public static boolean Delete(Record r) {
        return true;
    }

    // return true if r exists
    public static boolean Update(Record from, Record to) {
        return true;
    }

    public static void SetByDateRange(LocalDateTime startTime, LocalDateTime endTime) {
        var out = fullList;
        list.clear();
        for (var record : out) {
            if (record.dateTime.isAfter(startTime) && record.dateTime.isBefore(endTime))
                list.add(record);
        }
    }

    public static LocalDateTime getMinDateTime() {
        return fullList.stream().min((r1, r2) -> (int) (ChronoUnit.SECONDS.between(r2.dateTime, r1.dateTime)))
                .get().dateTime;
    }

    public static LocalDateTime getMaxDateTime() {
        return fullList.stream().max((r1, r2) -> (int) (ChronoUnit.SECONDS.between(r2.dateTime, r1.dateTime)))
                .get().dateTime;
    }

    public static ArrayList<Float> GetWeightList() {
        ArrayList<Float> out = new ArrayList<>();
        for (var r : list) {
            out.add(r.weight);
        }
        return out;
    }

    public static ArrayList<Float> GetHeightList() {
        ArrayList<Float> out = new ArrayList<>();
        for (var r : list) {
            out.add(r.height);
        }
        return out;
    }

    public static ArrayList<Float> GetBodyTempList() {
        ArrayList<Float> out = new ArrayList<>();
        for (var r : list) {
            out.add(r.bodyTemp);
        }
        return out;
    }

    public static ArrayList<LocalDateTime> GetTimeList() {
        ArrayList<LocalDateTime> out = new ArrayList<>();
        for (var r : list) {
            out.add(r.dateTime);
        }
        return out;
    }

    public static ArrayList<BMI> GetBMIList() {
        ArrayList<BMI> out = new ArrayList<>();
        for (var r : list) {
            out.add(r.Bmi());
        }
        return out;
    }

    public static ArrayList<Float> GetBMIValueList() {
        ArrayList<Float> out = new ArrayList<>();
        for (var r : list) {
            out.add(r.Bmi().value);
        }
        return out;
    }

    public static int size() {
        return list.size();
    }

    public static void SaveData() {
        RecordDatastore.Save(Records.fullList);
    }
}