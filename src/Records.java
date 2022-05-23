import java.util.*;

public class Records {
    Record data[];

    public static void sortbyWeight() {
        var list = Database.GetAllRecords();
        Collections.sort(list, new WeightComparator());
        // Collections.sort( list , (r1,r2) -> (CompareWeight(r1,r2)));
    }

    private static int CompareWeight(Record c1, Record c2) {
        if (c1.weight == c2.weight)
            return 0;
        else if (c1.weight > c2.weight)
            return 1;
        else
            return -1;
    }
}

class WeightComparator implements Comparator<Record> {

    // Function to compare
    public int compare(Record c1, Record c2) {
        if (c1.weight == c2.weight)
            return 0;
        else if (c1.weight > c2.weight)
            return 1;
        else
            return -1;
    }
}
