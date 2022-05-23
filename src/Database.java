import java.text.DecimalFormat;
import java.time.*;
import java.util.*;

public class Database {
    public static ArrayList<Record> GetAllRecords() {
        Record out[] = {
                new Record(163.f, 65.7f, 36.8f, LocalDateTime.of(2022, 1, 13, 11, 19)),
                new Record(164.f, 66.2f, 36.9f, LocalDateTime.of(2022, 1, 17, 17, 25)),
                new Record(164.f, 66.6f, 36.5f, LocalDateTime.of(2022, 1, 23, 20, 56)),
                new Record(163.f, 65.3f, 36.9f, LocalDateTime.of(2022, 1, 25, 8, 23)),
                new Record(163.f, 65.2f, 36.3f, LocalDateTime.of(2022, 2, 3, 18, 34)),
                new Record(165.f, 64.7f, 36.2f, LocalDateTime.of(2022, 2, 15, 9, 36)),
                new Record(165.f, 64.9f, 36.2f, LocalDateTime.of(2022, 2, 19, 12, 45)),
                new Record(163.f, 65.0f, 36.3f, LocalDateTime.of(2022, 2, 23, 15, 53)),
                new Record(162.f, 65.5f, 36.9f, LocalDateTime.of(2022, 3, 1, 13, 13)),
                new Record(162.f, 65.8f, 36.7f, LocalDateTime.of(2022, 3, 7, 14, 50)),
                new Record(163.f, 67.7f, 36.6f, LocalDateTime.of(2022, 3, 13, 18, 3)),
                new Record(161.f, 69.2f, 36.9f, LocalDateTime.of(2022, 3, 23, 13, 56)),
        };

        return new ArrayList<Record>(Arrays.asList(out));
    }

    public static ArrayList<Record> GenerateRandomRecords(int count) {

        var random = new Random();

        ArrayList<Record> out = new ArrayList<Record>();

        for (int i = 0; i < count; i++) {
            out.add(
                new Record(Randheight(random), Randweight(random), RandBodyTemperature(random), RandDateTime(random)));
        }

        Collections.sort(out, new DateComparator());

        return out;
    }

    private static float Randheight(Random random) {
        return (float) random.nextDouble(160.f, 170.f);
    }

    private static float Randweight(Random random) {
        return (float) random.nextDouble(60.f, 70.f);
    }

    private static float RandBodyTemperature(Random random) {
        return (float) random.nextDouble(35.5f, 37.f);
    }

    private static LocalDateTime RandDateTime(Random random) {
        final LocalDateTime minDate = LocalDateTime.of(2022, 1, 1, 0, 0);
        final LocalDateTime maxDate = LocalDateTime.now();

        final long minEpochSecond = minDate.atZone(ZoneId.systemDefault()).toEpochSecond();
        final long maxEpochSecond = maxDate.atZone(ZoneId.systemDefault()).toEpochSecond();

        long epochSecond = random.nextLong(minEpochSecond, maxEpochSecond);
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(epochSecond), ZoneId.systemDefault());
    }

    private static class DateComparator implements Comparator<Record> {

        // Function to compare
        public int compare(Record c1, Record c2) {
            return c1.date.compareTo(c2.date);
        }
    }
}
