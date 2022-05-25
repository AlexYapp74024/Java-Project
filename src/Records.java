import java.util.*;

public class Records {
    Record data[];

    //(Ascending)Sort by weight function
    public static void sortbyWeightAsc() {
        var list = RecordDatastore.Retrieve();
        Collections.sort(list, new WeightComparatorAsc());
    }

    //(Descending)Sort by weight function
    public static void sortbyWeightDesc() {
        var list = RecordDatastore.Retrieve();
        Collections.sort(list, new WeightComparatorDesc());
    }

    //(Ascending)Sort by date function
    public static void sortbyDateAsc() {
        var list = RecordDatastore.Retrieve();
        Collections.sort(list, new DateComparatorAsc());
    }

    //(Descending)Sort by date function
    public static void sortbyDateDesc() {
        var list = RecordDatastore.Retrieve();
        Collections.sort(list, new DateComparatorDesc());
    }

    //(Ascending)Sort by BMI function
    public static void sortbyBMIAsc() {
        // **No list of BMI yet
        // var list = RecordDatastore.Retrieve();
        // Collections.sort(list, new BMIComparatorAsc());
    }

    //(Descending)Sort by BMI function
    public static void sortbyBMIDesc() {
        // **No list of BMI yet
        // var list = RecordDatastore.Retrieve();
        // Collections.sort(list, new BMIComparatorDesc());
    }

}

//(Ascending)Custom class with function to compare weight
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
//(Descending)Custom class with function to compare weight
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

//(Ascending)Custom class with function to compare date
class DateComparatorAsc implements Comparator<Record> {
    @Override
    public int compare(Record c1, Record c2) {
        int result = c1.date.compareTo(c2.date);
        return result;
    }

}

//(Descending)Custom class with function to compare date
class DateComparatorDesc implements Comparator<Record> {
    @Override
    public int compare(Record c1, Record c2) {
        int result = c1.date.compareTo(c2.date);
        result = ( (-1) * result );
        return result;
    }

}
