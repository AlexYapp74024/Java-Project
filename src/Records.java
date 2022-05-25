import java.util.*;

public class Records {
    ArrayList<Record> list = RecordDatastore.Retrieve();

    //(Ascending)Sort by BMI function
    public void sortbyWeightAsc() {
        Collections.sort(list, new WeightComparatorAsc());
    }

    //(Descending)Sort by BMI function
    public void sortbyWeightDesc() {
        Collections.sort(list, new WeightComparatorDesc());
    }

    //(Ascending)Sort by date function
    public void sortbyDateAsc() {
        Collections.sort(list, new DateComparatorAsc());
    }

    //(Descending)Sort by date function
    public void sortbyDateDesc() {
        Collections.sort(list, new DateComparatorDesc());
    }

    //(Ascending)Sort by BMI function
    public void sortbyBMIAsc() {        
        Collections.sort(list, new BMIComparatorAsc());
    }

    //(Descending)Sort by BMI function
    public void sortbyBMIDesc() {      
        Collections.sort(list, new BMIComparatorDesc());
    }

}

//(Ascending)Custom class with function to compare BMI
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
//(Descending)Custom class with function to compare BMI
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

//(Ascending)Custom class with function to compare BMI
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

//(Descending)Custom class with function to compare BMI
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
