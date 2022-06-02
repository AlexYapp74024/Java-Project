import java.time.LocalDateTime;
import java.util.*;

public class Records {
    private static ArrayList<Record> list = RecordDatastore.Retrieve();

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

    public static void sortbyWeightAsc() {
        Collections.sort(list, new WeightComparatorAsc());
    }

    public static void sortbyWeightDesc() {
        Collections.sort(list, new WeightComparatorDesc());
    }

    public static void sortbyDateAsc() {
        Collections.sort(list, new DateComparatorAsc());
    }

    public static void sortbyDateDesc() {
        Collections.sort(list, new DateComparatorDesc());
    }

    public static void sortbyBMIAsc() {
        Collections.sort(list, new BMIComparatorAsc());
    }

    public static void sortbyBMIDesc() {
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
