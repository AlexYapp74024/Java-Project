import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Records {
    private ArrayList<Record> fullList = RecordDatastore.Retrieve();
    private ArrayList<Record> list = CloneFullList();

    private ArrayList<Record> CloneFullList() {
        ArrayList<Record> out = new ArrayList<>();
        out.addAll(fullList);
        return out;
    }

    public void SetByDateRange(LocalDateTime startTime, LocalDateTime endTime) {
        var out = fullList;
        list.clear();
        for (var record : out) {
            if (record.dateTime.isAfter(startTime) && record.dateTime.isBefore(endTime))
                list.add(record);
        }
    }

    public LocalDateTime getMinDateTime() {
        return fullList.stream().min((r1, r2) -> (int) (ChronoUnit.SECONDS.between(r2.dateTime, r1.dateTime)))
                .get().dateTime;
    }

    public LocalDateTime getMaxDateTime() {
        return fullList.stream().max((r1, r2) -> (int) (ChronoUnit.SECONDS.between(r2.dateTime, r1.dateTime)))
                .get().dateTime;
    }

    public ArrayList<Float> GetWeightList() {
        ArrayList<Float> out = new ArrayList<>();
        for (var r : list) {
            out.add(r.weight);
        }
        return out;
    }

    public ArrayList<Float> GetHeightList() {
        ArrayList<Float> out = new ArrayList<>();
        for (var r : list) {
            out.add(r.height);
        }
        return out;
    }

    public ArrayList<Float> GetBodyTempList() {
        ArrayList<Float> out = new ArrayList<>();
        for (var r : list) {
            out.add(r.bodyTemp);
        }
        return out;
    }

    public ArrayList<LocalDateTime> GetTimeList() {
        ArrayList<LocalDateTime> out = new ArrayList<>();
        for (var r : list) {
            out.add(r.dateTime);
        }
        return out;
    }

    public ArrayList<BMI> GetBMIList() {
        ArrayList<BMI> out = new ArrayList<>();
        for (var r : list) {
            out.add(r.Bmi());
        }
        return out;
    }

    public int size() {
        return list.size();
    }

    public void sortbyWeightAsc() {
        Collections.sort(list, new WeightComparatorAsc());
    }

    public void sortbyWeightDesc() {
        Collections.sort(list, new WeightComparatorDesc());
    }

    public void sortbyDateAsc() {
        Collections.sort(list, new DateComparatorAsc());
    }

    public void sortbyDateDesc() {
        Collections.sort(list, new DateComparatorDesc());
    }

    public void sortbyBMIAsc() {
        Collections.sort(list, new BMIComparatorAsc());
    }

    public void sortbyBMIDesc() {
        Collections.sort(list, new BMIComparatorDesc());
    }

}

class WeightComparatorAsc implements Comparator<Record> {
    @Override
    public int compare(Record c1, Record c2) {
        if (c1.weight == c2.weight)
            return 0;
        else if (c1.weight > c2.weight)
            return 1;
        else
            return -1;
    }
}

class WeightComparatorDesc implements Comparator<Record> {
    @Override
    public int compare(Record c1, Record c2) {
        if (c1.weight == c2.weight)
            return 0;
        else if (c1.weight > c2.weight)
            return -1;
        else
            return 1;
    }
}

class DateComparatorAsc implements Comparator<Record> {
    @Override
    public int compare(Record c1, Record c2) {
        int result = c1.dateTime.compareTo(c2.dateTime);
        return result;
    }

}

class DateComparatorDesc implements Comparator<Record> {
    @Override
    public int compare(Record c1, Record c2) {
        int result = c1.dateTime.compareTo(c2.dateTime);
        result = ((-1) * result);
        return result;
    }

}

class BMIComparatorAsc implements Comparator<Record> {
    @Override
    public int compare(Record c1, Record c2) {
        if (c1.Bmi().value == c2.Bmi().value)
            return 0;
        else if (c1.Bmi().value > c2.Bmi().value)
            return 1;
        else
            return -1;
    }
}

class BMIComparatorDesc implements Comparator<Record> {
    @Override
    public int compare(Record c1, Record c2) {
        if (c1.Bmi().value == c2.Bmi().value)
            return 0;
        else if (c1.Bmi().value > c2.Bmi().value)
            return -1;
        else
            return 1;
    }
}
